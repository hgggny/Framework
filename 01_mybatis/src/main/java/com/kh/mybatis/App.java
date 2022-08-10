package com.kh.mybatis;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.SqlSessionTemplate;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        
//        SqlSession session = SqlSessionTemplate.getSession();
//        
//        System.out.println(session);
//        
//        // namespace.id
//        int result = session.selectOne("memberMapper.selectCount");
//        
//        System.out.println("result : " + result);
    }
}
