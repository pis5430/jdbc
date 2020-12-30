package com.javaex.book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	//12/30 오전 수업내용으로 수정하기 (코드 간결화)
	//필드
	
	//0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs =null; //select문에 사용됨
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	//생성자(디폴트 생성자 생략)
	
	//메소드 getter setter
	
	//일반메소드
	
	public void getConnection() {
		
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
		    Class.forName(driver);

		    // 2. Connection 얻어오기
		    conn = DriverManager.getConnection(url, id, pw);
		    System.out.println();
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	//자원정리
	public void close() {
		 // 5. 자원정리
	    try {     
	    	if(rs != null){
			 	rs.close();
			 }
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	
	
	
	
	
	
	//책 저장(등록)하기

	public int bookInsert(BookVo bookVo) {
				
				getConnection();
				
				int count = 0;

				try {

				    // 3. SQL문 준비 / 바인딩 / 실행
					String query = "";
					query += " insert into book ";
					query += " values(seq_book_id.nextval, ? , ? ,? , ?) ";
					
					System.out.println(query);
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1,bookVo.getTitle()); // BookVo 안에 title
					pstmt.setString(2,bookVo.getPubs()); // BookVo 안에 pubs
					pstmt.setString(3,bookVo.getPubsDate()); // BookVo 안에 pubs_date
					pstmt.setInt(4,bookVo.getAuthorId()); // BookVo 안에 authorId
					
					count = pstmt.executeUpdate();
					
				    
				    // 4.결과처리
					
					System.out.println("[dao]" + count + "건 저장");
					
				} catch (SQLException e) {
				    System.out.println("error:" + e);
				} 
				
				close();

		return count;
	}
	
	//책 리스트 가져오기
	
	public List<BookVo> getBookList(){
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
				getConnection();
				
				try {	

					//3. sql문 준비 / 바인딩 실행
					String query = "";
					
					query += " select book_id, ";
					query += "        title, ";
					query += "        pubs, ";
					query += "        to_char(pubs_date,'YYYY-MM-DD') pubs_date, ";
					query += "        author_id ";
					query += " from book ";
					//-- 물음표 변환 영역 없음
					
					System.out.println(query);
					
					pstmt = conn.prepareStatement(query);
					//-- 물음표 영역 없음
					rs = pstmt.executeQuery();

					
					//4.결과처리
					//rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
					//rs.getInt(1); // 숫자로도 표기 가능 
					
					while (rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookTitle = rs.getString("title");
						String bookPubs = rs.getString("pubs");
						String bookPubsDate = rs.getString("pubs_date");
						int authorId = rs.getInt("author_id");
						
						BookVo vo = new BookVo(bookId ,bookTitle,bookPubs,bookPubsDate,authorId);
						bookList.add(vo);
					}
							
					}catch(SQLException e) {
						System.out.println("error:" + e );
					}
				close();
		
		return bookList;
	}
	

	//책 수정하기
	
	public int bookUpdate(BookVo bookVo) {

				getConnection();
		
				int count = 0;
				
				try {	
					
					String query ="";
					query += " update book ";
					query += " set title = ? ,";
					query += "     pubs = ? ,";
					query += "     pubs_date = ? ,";
					query += "     author_id = ? ";
					query += " where book_id = ? ";
					System.out.println(query);
					
					
					pstmt = conn.prepareStatement(query); //쿼리로 만들기
					pstmt.setString(1,bookVo.getTitle());
					pstmt.setString(2,bookVo.getPubs());
					pstmt.setString(3,bookVo.getPubsDate());
					pstmt.setInt(4,bookVo.getAuthorId());
					pstmt.setInt(5,bookVo.getBookId());
					
					
					count = pstmt.executeUpdate();
					
					
					//4.결과처리
					System.out.println("[dao]"+count + "건이 수정되었습니다.");
				

					}catch(SQLException e) {
						System.out.println("error:" + e );
					}
				
					close();
					
					return count;
		     	}
			

	//책 삭제하기
	
	public int bookDelete(int bookId) {

				getConnection();
				
				int count = 0;
				
				try {	

					//3. sql문 준비 / 바인딩 / 실행
					String query ="";
					query += " delete from book ";
					query += " where book_id = ? ";
					
					//테스트 
					System.out.println(query);
					
					
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, bookId);
					
					
					count = pstmt.executeUpdate();
					
					//4.결과처리
					
					System.out.println("[dao]" + count + "건이 삭제되었습니다.");
						
				}catch(SQLException e) {
					System.out.println("error:" + e );
				}
				
				close();
		
		return count;
	}
	
	//책 전체 출력하기
	//AuthorVo 를 BookVo가 상속받음 (작가 관련 정보 사용가능)
	
	public List<BookVo> getAllList(){
		
		List<BookVo> allList = new ArrayList<BookVo>();

				getConnection();
				
				try {	

					//3. sql문 준비 / 바인딩 실행
					String query = "";
					
					query += " select b.book_id, ";
					query += "        b.title, ";
					query += "        b.pubs, ";
					query += "        to_char(b.pubs_date,'YYYY-MM-DD') pubs_date, ";
					query += "        b.author_id, ";
					query += "        a.author_name, ";
					query += "        a.author_desc ";
					query += " from book b , author a ";
					query += " where b.author_id = a.author_id ";
					//-- 물음표 변환 영역 없음
					
					System.out.println(query);
					
					pstmt = conn.prepareStatement(query);
					//-- 물음표 영역 없음
					rs = pstmt.executeQuery();

					
					//4.결과처리
					//rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
					//rs.getInt(1); // 숫자로도 표기 가능 
					
					while (rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookTitle = rs.getString("title");
						String bookPubs = rs.getString("pubs");
						String bookPubsDate = rs.getString("pubs_date");
						int authorId = rs.getInt("author_id");
						String authorName = rs.getString("author_name");
						String authorDesc = rs.getString("author_desc");
						
						BookVo vo = new BookVo(bookId ,bookTitle,bookPubs,bookPubsDate,authorId,authorName,authorDesc);
						allList.add(vo);
					}

					}catch(SQLException e) {
						System.out.println("error:" + e );
					}
				
				close();
		
		
		return allList;
	}
	
	//검색기능
	
	public List<BookVo> searchList(String search) {
		
		List<BookVo> searchList = new ArrayList<BookVo>();
		
				getConnection();
				
				try {	

					//3. sql문 준비 / 바인딩 실행
					String query = "";
					
					query += " select b.book_id, ";
					query += "        b.title, ";
					query += "        b.pubs, ";
					query += "        to_char(b.pubs_date,'YYYY-MM-DD') pubs_date, ";
					query += "        a.author_name ";
					query += " from book b , author a ";
					query += " where b.author_id = a.author_id ";
					query += " and (b.title like ? ";
					query += " or b.pubs like ? ";
					query += " or a.author_name like ?) ";
					//주의 
					//여기에 %를 넣으면 문자열로 인식
					//and 뒤에 ()로 묶어주지 않으면 다른 결과값이 나옴
					System.out.println(query);
					
					//-- 검색 문자 입력값 (search)
					
					String str = "%" + search + "%";
					
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, str);
					pstmt.setString(2, str);
					pstmt.setString(3, str);
					

					rs = pstmt.executeQuery();

					
					//4.결과처리
					//rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
					//rs.getInt(1); // 숫자로도 표기 가능 
					
					while (rs.next()) {
						int bookId = rs.getInt("book_id");
						String bookTitle = rs.getString("title");
						String bookPubs = rs.getString("pubs");
						String bookPubsDate = rs.getString("pubs_date");
						String authorName = rs.getString("author_name");

						
						BookVo vo = new BookVo(bookId ,bookTitle,bookPubs,bookPubsDate,authorName);
						searchList.add(vo);
					}

					}catch(SQLException e) {
						System.out.println("error:" + e );
					}
				
				close();
		
		
		return searchList;
	}
	
	
	
	

}
