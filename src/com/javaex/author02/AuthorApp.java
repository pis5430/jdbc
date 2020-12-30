package com.javaex.author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		//int count = authorDao.authorInsert("이문열","경북영양"); //작가테이블에 데이터 저장
		//System.out.println(count + "건 등록되었습니다.");
		
		//등록
		AuthorVo authorVo1 = new AuthorVo("이문열","경북영양");
		authorDao.authorInsert(authorVo1); //작가테이블에 데이터 저장
		
		AuthorVo authorVo2 = new AuthorVo("삼문열","경북영양양");
		authorDao.authorInsert(authorVo2); //작가테이블에 데이터 저장
		
		AuthorVo authorVo3 = new AuthorVo("사문열","경북영양양양");
		authorDao.authorInsert(authorVo3); //작가테이블에 데이터 저장
		
		
		//리스트 
		authorList = authorDao.getAuthorList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력======");
		
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + 
								vo.getAuthorDesc());
		}
	
	
		
		/*
		//작가삭제
		authorDao.authorDelete(2);
		
		
		
		//리스트2 (다시 넣어줘야 삭제된 값이 적용됨)
		authorList = authorDao.getAuthorList();
		
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		
		//작가수정
		
		AuthorVo authorVo4 = new AuthorVo(1,"이문열","수정한 정보"); 
		//authorDao.authorUpdate(4, "유시민","17대 국회의원");
		
		//리스트3 (다시 넣어줘야 수정된 값이 적용됨)
		authorList = authorDao.getAuthorList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		;
		//System.out.println(authorList.toString());
		
		
		
		
		List<AuthorVo> authorList = authorDao.getAuthorList();
		//for() 이용해서 출력
		
		
		authorDao.authorDelete(1);
		authorDao.authorDelete(2,"김경리","제주도");
		
		*/
		
		
	}

}
