package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookUpdate {

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
				
				String query ="";
				query += " update book ";
				query += " set pubs = ? ";
				query += " where book_id = ? ";
				
				
				//System.out.println(query);
				
				pstmt = conn.prepareStatement(query); //쿼리로 만들기
				pstmt.setString(1,"중앙북스(books)");
				pstmt.setInt(2, 13);
				
				
				int count = pstmt.executeUpdate();
				
				
				//4.결과처리
				System.out.println(count + "건이 수정되었습니다.");
			
				
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
