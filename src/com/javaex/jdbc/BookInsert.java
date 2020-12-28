package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
			
			//0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet rs =null; //select문에 사용됨
			
			try {	
				//1. JDBC 드라이버 (oracle)로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. connection얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
					
				
				//3. sql문 준비 / 바인딩 실행
				String query = "INSERT INTO book VALUES (seq_book_id.nextval, ? , ? , ?, ?) ";
				pstmt = conn.prepareStatement(query); //쿼리로 만들기
				
				// 예시와 같이 업로드 --> 패션왕 중앙북스만 영어명빼고 표기 (업데이트 예정)
				pstmt.setNString(1, "삭제할거");
				pstmt.setNString(2, "삭제예시");
				pstmt.setNString(3, "2012-02-04");
				pstmt.setInt(4, 6);
				
				//insert into author values (seq_author_id.nextval, '이문열', '경북 양양');
				int count = pstmt.executeUpdate();
				
				//4.결과처리
				System.out.println(count +"건이 저장되었습니다.");
			
				
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

	}

}
