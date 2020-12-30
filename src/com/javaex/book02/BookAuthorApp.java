package com.javaex.book02;

import java.util.List;
import java.util.Scanner;

public class BookAuthorApp {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in); //문자열 검색
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		//작가 6명 등록
		//AuthorDao. AuthorVo 이용해서 등록
		//(수정, 삭제 리스트)
		
		//등록
		AuthorVo authorVo1 = new AuthorVo("이문열","경북 영양");
		authorDao.authorInsert(authorVo1);
		
		AuthorVo authorVo2 = new AuthorVo("박경리","경상남도 통영");
		authorDao.authorInsert(authorVo2);
		
		AuthorVo authorVo3 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(authorVo3);
		
		AuthorVo authorVo4 = new AuthorVo("기안84","기안동에서 산 84년생");
		authorDao.authorInsert(authorVo4);
		
		AuthorVo authorVo5 = new AuthorVo("강풀","온라인 만화가 1세대");
		authorDao.authorInsert(authorVo5);
		
		AuthorVo authorVo6 = new AuthorVo("김영하","알쓸신잡");
		authorDao.authorInsert(authorVo6);
		
		AuthorVo authorVo7 = new AuthorVo("삭제용","삭제용");
		authorDao.authorInsert(authorVo7);
		
		//리스트 넣어주기
		authorList = authorDao.getAuthorList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		
		//수정
		
		AuthorVo authorVo00 = new AuthorVo(1,"이문열(수정)","경북 영양(수정)");
		authorDao.authorUpdate(authorVo00);
		
		
		//수정 확인 하기
		//리스트 넣어주기
		authorList = authorDao.getAuthorList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력(수정확인)======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		//삭제(7. 삭제용)
		authorDao.authorDelete(7);
		
		//삭제 확인 하기
		//리스트 넣어주기
		authorList = authorDao.getAuthorList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력(삭제확인)======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + "," + vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		

		//책 8권 등록
		//BookDao. BookVo 이용해서 등록
		//(수정, 삭제 리스트)
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookList;
		
		
		//등록
		BookVo bookVo1 = new BookVo("우리들의 일그러진 영웅"," 다림"," 1998-02-22",1);
		bookDao.bookInsert(bookVo1);
		
		BookVo bookVo2 = new BookVo("삼국지","민음사","2002-03-21",1);
		bookDao.bookInsert(bookVo2);
		
		BookVo bookVo3 = new BookVo("토지","마로니에북스","2012-08-15",2);
		bookDao.bookInsert(bookVo3);
		
		BookVo bookVo4 = new BookVo("유시민의 글쓰기 특강","생각의 길"," 2015-04-01",3);
		bookDao.bookInsert(bookVo4);
		
		BookVo bookVo5 = new BookVo("패션왕","중앙북스","2012-02-22",4);
		bookDao.bookInsert(bookVo5);
		
		BookVo bookVo6 = new BookVo("순정만화","재미주의","2011-08-03",5);
		bookDao.bookInsert(bookVo6);
		
		BookVo bookVo7 = new BookVo("오직두사람","문학동네","2017-05-04",6);
		bookDao.bookInsert(bookVo7);
		
		BookVo bookVo8 = new BookVo("26년","재미주의","2012-02-04",5);
		bookDao.bookInsert(bookVo8);
		
		
		//리스트 넣어주기
		bookList = bookDao.getBookList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력======");
		for(int i=0; i<bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getTitle() + "," + vo.getPubs() + "," + vo.getPubsDate() + "," + vo.getAuthorId());
		}
		
		
		//수정
		
		BookVo bookVo00 = new BookVo(5 ,"패션왕(수정)","중앙북스(수정)","1991-08-02" , 4);
		bookDao.bookUpdate(bookVo00);
		
		
		//수정 확인 하기
		//리스트 넣어주기
		bookList = bookDao.getBookList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력(수정확인)======");
		for(int i=0; i<bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getTitle() + "," + vo.getPubs() + "," + vo.getPubsDate() + "," + vo.getAuthorId());
		}
		
		//삭제(우리들의 일그러진 영웅 삭제)
		bookDao.bookDelete(1);
		
		//삭제 확인
		//리스트 넣어주기
		bookList = bookDao.getBookList();
		
		//리스트 전체출력
		System.out.println("=====리스트 전체출력(삭제확인)======");
		for(int i=0; i<bookList.size(); i++) {
			BookVo vo = bookList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getTitle() + "," + vo.getPubs() + "," + vo.getPubsDate() + "," + vo.getAuthorId());
		}
		
		
		//책 전체를 출력 
		//(책 --> 책정보 + 작가정보)
		//BookVo --> 책정보 + 작가정보 (AuthorVo 를 상속받아야할듯)
		
		List<BookVo> allList;
		allList = bookDao.getAllList();
		
		//리스트 전체출력
		System.out.println("=====책*작가리스트 전체출력======");
		for(int i=0; i<allList.size(); i++) {
			BookVo vo = allList.get(i);
			System.out.println(vo.getBookId() + "," + vo.getTitle() + "," + vo.getPubs() + "," + vo.getPubsDate() + "," + vo.getAuthorId() + ","
								+ vo.getAuthorName() + "," + vo.getAuthorDesc());
		}
		
		//문자열 검색하기
		
		List<BookVo> searchList;
		
		System.out.println("=====문자열 검색======");
		System.out.print("검색 : ");
		String search = sc.nextLine(); //%search%
		
		searchList = bookDao.searchList(search);
		

		System.out.println("=====문자열 검색결과======");
		for(int i=0; i<searchList.size(); i++) {
			System.out.println(searchList.get(i).getBookId() + "," + searchList.get(i).getTitle() + "," + searchList.get(i).getPubs() + "," + searchList.get(i).getPubsDate() + "," + searchList.get(i).getAuthorName());
		}
		
		sc.close();
		

	}

}
