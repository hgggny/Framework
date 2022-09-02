package com.kh.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;


//	 메소드의 리턴 타입이 void 일 경우 Mapping URL을 유추해서 View를 찾는다.
//	@GetMapping("/board/list")
//	public void list() {
//	}
	
	@GetMapping("/board/list")
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
	
	@GetMapping("/board/view")
	public ModelAndView view(ModelAndView model, @RequestParam int no) {
		Board board = null;
		
		board = service.findBoardByNo(no);
		
		model.addObject("board", board);
		model.setViewName("board/view");
		
		return model;
	}
	
	@GetMapping("/board/delete")
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

	
	
	
	
	
	
	
}
