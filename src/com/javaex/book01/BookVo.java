package com.javaex.book01;

public class BookVo extends AuthorVo{
	
	//필드	
	public int bookId;
	public String title;
	public String pubs;
	public String pubsDate;
	public int authorId; 

	
	//생성자
	public BookVo() {}

	public BookVo(String title, String pubs, String pubsDate) {
		this.title = title;
		this.pubs = pubs;
		this.pubsDate = pubsDate;
	}
	
	public BookVo(String title, String pubs, String pubsDate, int authorId) {
		this.title = title;
		this.pubs = pubs;
		this.pubsDate = pubsDate;
		this.authorId = authorId;
	}
	

	public BookVo(int bookId, String title, String pubs, String pubsDate, int authorId) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubsDate = pubsDate;
		this.authorId = authorId;
	}
	
	//상속관련 생성자 authorId는 중복되니 BookVo클래스에 있는것을 사용
	public BookVo(int bookId, String title, String pubs, String pubsDate,int authorId, String authorName, String authorDesc) {
		super.authorName = authorName;
		super.authorDesc = authorDesc;
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubsDate = pubsDate;
		this.authorId = authorId;
	}
	
	public BookVo(int bookId, String title, String pubs, String pubsDate, String authorName) {
		super.authorName = authorName;
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubsDate = pubsDate;
	}


	//메소드 getter / setter
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubsDate() {
		return pubsDate;
	}

	public void setPubsDate(String pubsDate) {
		this.pubsDate = pubsDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	
	//일반메소드
	
	
	
	
}
