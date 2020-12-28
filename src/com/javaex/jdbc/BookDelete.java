package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {

	public static void main(String[] args) {
		
		    //0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet rs =null;
			
			try {	
				//1. JDBC 드라이버 (oracle)로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. connection얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
					
				
				//3. sql문 준비 / 바인딩 / 실행
				
				String query ="";
				query += " delete from book ";
				query += " where book_id = ? ";
				
				//테스트 
				System.out.println(query);
				
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, 18);
				
				
				int count = pstmt.executeUpdate();
				
				//4.결과처리
				
				System.out.println(count + "건이 삭제되었습니다.");
				
	
				
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
