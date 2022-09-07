package com.kh.mvc.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.MultipartFileUtil;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

	@Autowired
	private ResourceLoader resourceLoader;

//	 메소드의 리턴 타입이 void 일 경우 Mapping URL을 유추해서 View를 찾는다.
//	@GetMapping("/board/list")
//	public void list() {
//	}
	
	@GetMapping("/list")
	public ModelAndView list(ModelAndView model,
			@RequestParam(value="page", defaultValue="1") int page) {
		
		List<Board> list = null;
		PageInfo pageInfo = null;
			
		pageInfo = new PageInfo(page, 10, service.getBoardCount(), 10);
		list = service.getBoardList(pageInfo);
		
		System.out.println(list);
		
		model.addObject("list", list);
		model.addObject("pageInfo", pageInfo);
		model.setViewName("board/list");
		
		return model;
	}
	
	@GetMapping("/view")
	public ModelAndView view(ModelAndView model, @RequestParam int no) {
		Board board = null;
		
		board = service.findBoardByNo(no);
		
		model.addObject("board", board);
		model.setViewName("board/view");
		
		return model;
	}
	
	@GetMapping("/delete")
	public ModelAndView delete(
			ModelAndView model, 
			@SessionAttribute("loginMember") Member loginMember,
			@RequestParam int no) {
		int result = 0;
		Board board = null;
		
		board = service.findBoardByNo(no);
		
		if(board.getWriterId().equals(loginMember.getId())) {
			result = service.delete(no);
			
			if(result > 0) {
				model.addObject("msg", "게시글이 정상적으로 삭제");
				model.addObject("location", "/board/list");
			} else {
				model.addObject("msg", "게시글 삭제 실패");
				model.addObject("location", "/board/view?no=" + no);
			}
		} else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/board/list");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}

	@GetMapping("/write")
	public String write() {
		log.info("게시글 작성 페이지 요청");
		
		return "board/write";
	}
	
	@PostMapping("/write")
	public ModelAndView write(
			ModelAndView model,
			@ModelAttribute Board board, 
			@RequestParam("upfile") MultipartFile upfile,
			@SessionAttribute("loginMember") Member loginMember){
		
		int result = 0;
		
		// 파일을 업로드하지 않으면 true, 파일을 업로드하면 false
		log.info("Upfile is Empty : {}", upfile.isEmpty());
		// 파일을 업로드하지 않으면 빈문자열, 파일을 업로드하면 "파일명"
		log.info("Upfile Name : {}", upfile.getOriginalFilename());
		
		// 1. 파일을 업로드 했는지 확인 후 파일을 저장
		if(upfile != null && !upfile.isEmpty()) {
			// 파일을 저장하는 로직 작성
			String location = null;
			String renamedFileName = null;
			
			try {
				location = resourceLoader.getResource("resources/upload/board").getFile().getPath();
				renamedFileName = MultipartFileUtil.save(upfile, location);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(renamedFileName != null) {
				board.setOriginalFileName(upfile.getOriginalFilename());
				board.setRenamedFileName(renamedFileName);
				
			}
		} 
		
		// 2. 작성한 게시글 데이터를 데이터 베이스에 저장
		board.setWriterNo(loginMember.getNo());
		result = service.save(board);
		
		if(result > 0) {
			model.addObject("msg", "게시글 등록 성공");
			model.addObject("location", "/board/view?no=" + board.getNo());
		} else {
			model.addObject("msg", "게시글 등록 실패");
			model.addObject("location", "/board/write");
		}
		
		model.setViewName("common/msg");
		
		return model;
	}
	
	@GetMapping("/fileDown")
	   public ResponseEntity<Resource> fileDown(
	         @RequestHeader("user-agent") String userAgent,
	         @RequestParam String oname, @RequestParam String rname) {

	      Resource resource = null;
	      String downFileName = null;

	      log.info("oname : {}, rname : {}", oname, rname);
	      log.info("{}", userAgent);
	      
	      try {
	    	// 클라이언트로 전송할 파일의 경로와 파일명을 가져온다.
	         resource = resourceLoader.getResource("resources/upload/board/" + rname);
	         
	         // 브라우저별 인코딩 처리
	         if(userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1) {
	               downFileName = URLEncoder.encode(oname, "UTF-8").replaceAll("\\+", "%20");
	         } else {
	            downFileName = new String(oname.getBytes("UTF-8"), "ISO-8859-1");
	         }
	         
	      // 응답 메시지 작성 (html X, file O)
	         return ResponseEntity.ok()
	        	   // application/octet-stream 모든 종류의 2진 데이터를 뜻한다.
	               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
	               // 파일 링크를 클릭했을 때 다운로드 화면이 출력되게 처리하는 부분
	               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + downFileName)
	               .body(resource);
	      } catch (UnsupportedEncodingException e) {
	         e.printStackTrace();
	      }
	      
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	   }
	
	@GetMapping("/update")
	public ModelAndView update(
			ModelAndView model, 
			@RequestParam int no,
			@SessionAttribute("loginMember") Member loginMember) {
		Board board = null;
		
		board = service.findBoardByNo(no);

		if(board.getWriterId().equals(loginMember.getId())) {
			model.addObject("board", board);
			model.setViewName("board/update");
		} else {
			model.addObject("msg", "잘못된 접근입니다.");
			model.addObject("location", "/board/list");
			model.setViewName("common/msg");
		}
		
		return model;
	}

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	   public ModelAndView update(
	         ModelAndView model,
	         @ModelAttribute Board board,
	         @RequestParam("upfile") MultipartFile upfile,
	         @SessionAttribute("loginMember") Member loginMember) {
	      int result = 0;
	      String location = null;
	      String renamedFileName = null;
	                  
	      if (service.findBoardByNo(board.getNo()).getWriterId().equals(loginMember.getId())) {
	         if (upfile != null && !upfile.isEmpty()) {
	            
	             try {
	               
	               location = resourceLoader.getResource("resources/upload/board").getFile().getPath();
	               
	               // 기존에 업로드 된 파일이 있음
	               if (board.getRenamedFileName() != null) {
	                  
	                  // 이전에 업로드 된 첨부 파일이 존재하면 삭제
	                  MultipartFileUtil.delete(location + "/" + board.getRenamedFileName());
	               }
	               
	               renamedFileName = MultipartFileUtil.save(upfile, location);
	               
	               if (renamedFileName != null) {
	                  board.setOriginalFileName(upfile.getOriginalFilename());
	                  board.setRenamedFileName(renamedFileName);
	               }
	            
	             } catch (IOException e) {
	               e.printStackTrace();
	            }
	         }
	         
	         result = service.save(board);
	         
	         if (result > 0) {
	            
	            model.addObject("msg", "수정 완");
	            model.addObject("location", "/board/view?no=" + board.getNo());
	         } else {
	            
	            model.addObject("msg", "다시 수정하쇼");
	            model.addObject("location", "/board/update?no=" + board.getNo());
	         }
	      } else {
	         model.addObject("msg", "잘못 접근");
	         model.addObject("location", "/board/list");
	      }
	            
	      model.setViewName("common/msg");
	      
	      return model;
	   }
}
