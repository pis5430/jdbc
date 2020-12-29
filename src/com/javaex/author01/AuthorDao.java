package com.javaex.author01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	//생성자
	
	//디폴드 생성자 생략 (다른 생성자 없음)
	
	//메소드 g/s
	
	//메소드 일반
	
	//작가 수정하기
	public int authorUpdate(AuthorVo authorVo) {
		
			 //0. import java.sql.*;
	 		Connection conn = null;
			PreparedStatement pstmt = null;
			
			int count = 0;
			
			try {	
				//1. JDBC 드라이버 (oracle)로딩
				Class.forName(driver);
				
				//2. connection얻어오기
				conn = DriverManager.getConnection(url, id, pw);
					
				
				String query ="";
				query += " update author ";
				query += " set author_name = ? ,";
				query += "     author_desc = ? ";
				query += " where author_id = ? ";
				System.out.println(query);
				
				
				pstmt = conn.prepareStatement(query); //쿼리로 만들기
				pstmt.setString(1,authorVo.getAuthorName());
				pstmt.setString(2,authorVo.authorDesc);
				pstmt.setInt(3, authorVo.getAuthorId());
				
				
				count = pstmt.executeUpdate();
				
				
				//4.결과처리
				System.out.println("[dao]"+count + "건이 수정되었습니다.");
			
				
			}catch(ClassNotFoundException e) {
				System.out.println("error:드라이버 로딩 실패 - " + e);
			}catch(SQLException e) {
				System.out.println("error:" + e );
			}finally {
				
				//5.자원정리
				try {
					/*
					 if(rs != null){
					 	rs.close();
					 }
					 */
					if(pstmt != null) {
						pstmt.close();
						
					}
					if(conn != null) {
						conn.close();
					}
				}catch (SQLException e) {
					System.out.println("error:" + e);
				}
			
		
			}
			return count;
	}
	
	
	
	
	//작가 삭제하기
	public int authorDelete(int authorId) {
		
			//0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int count = 0;
			
			try {	
				//1. JDBC 드라이버 (oracle)로딩
				Class.forName(driver);
				
				//2. connection얻어오기
				conn = DriverManager.getConnection(url, id, pw);
					
				
				//3. sql문 준비 / 바인딩 / 실행
				
				String query ="";
				query += " delete from author ";
				query += " where author_id = ? ";
				
				//테스트 
				System.out.println(query);
				
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, authorId);
				
				
				count = pstmt.executeUpdate();
				
				//4.결과처리
				
				System.out.println("[dao]" + count + "건이 삭제되었습니다.");
				
	
				
		}catch(ClassNotFoundException e) {
			System.out.println("error:드라이버 로딩 실패 - " + e);
		}catch(SQLException e) {
			System.out.println("error:" + e );
		}finally {
			
			//5.자원정리
			try {

				if(pstmt != null) {
					pstmt.close();
					
				}
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			
		}
		
		return count;
		
		
	}
	

	
	//작가 리스트 가져오기
	public List<AuthorVo> getAuthorList(){
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null; //select문에 사용됨
		
		try {	
			//1. JDBC 드라이버 (oracle)로딩
			Class.forName(driver);
			
			//2. connection얻어오기
			conn = DriverManager.getConnection(url, id, pw);
				
			
			//3. sql문 준비 / 바인딩 실행
			String query = "";
			
			query += " select author_id id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += " from author ";
			//-- 물음표 변환 영역 없음
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			//-- 물음표 영역 없음
			rs = pstmt.executeQuery();

			
			//4.결과처리
			//rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
			//rs.getInt(1); // 숫자로도 표기 가능 
			
			while (rs.next()) {
				int authorId = rs.getInt("id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AuthorVo vo = new AuthorVo(authorId ,authorName,authorDesc);
				authorList.add(vo);
			}
					
			}catch(ClassNotFoundException e) {
				System.out.println("error:드라이버 로딩 실패 - " + e);
			}catch(SQLException e) {
				System.out.println("error:" + e );
			}finally {
				
				//5.자원정리
				try {
					
					 if(rs != null){
					 	rs.close();
					 }
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				}catch (SQLException e) {
					System.out.println("error:" + e);
				}
			}		
		return authorList;
	}
	

	//작가 저장 기능
	public int authorInsert(AuthorVo authorVo) { // AuthorVo은 묶음의 개념
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;


		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);


		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);


		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ? , ?) ";
			
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,authorVo.getAuthorName()); // AuthorVo 안에 name
			pstmt.setString(2,authorVo.getAuthorDesc()); // AuthorVo 안에 desc
			
			count = pstmt.executeUpdate();
			
		    
		    // 4.결과처리
			
			System.out.println("[dao]" + count + "건 저장");
			
			

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        /*
		    	if (rs != null) {
		            rs.close();
		        } 
		        */               
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
		
		return count;
		
	}

}
