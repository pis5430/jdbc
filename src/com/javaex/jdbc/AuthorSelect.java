package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSelect {

	public static void main(String[] args) {
		
			//0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs =null; //select문에 사용됨
			
			try {	
				//1. JDBC 드라이버 (oracle)로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. connection얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
					
				
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
				//rs.getInt(1); // 숫자로도 표기 가능 
				while(rs.next()) {
					int authorId = rs.getInt("id"); // 별명을 지어줫을 경우 별명으로만 인식가능
					String authorName = rs.getNString("author_name");
					String authorDesc = rs.getNString("author_desc");
					
					System.out.println(authorId + "," + authorName + "," + authorDesc);
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

	}

}
