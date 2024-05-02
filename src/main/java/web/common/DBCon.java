package web.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon { //DBConnection
	
	private static final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://localhost:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "0118";
	
	static {
		try {
			Class.forName(DRIVER_NAME); //드라이버 연결시도 mariadb드라이버를 쓰겠다는걸 인증
			System.out.println("내가 나오면 드라이버 연결은 된거야~");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
		
		public static Connection getCon() {  //Connection을 사용하여 DB연결
			try {
				return DriverManager.getConnection(URL,USER,PASSWORD);
			}catch(SQLException e){
				e.printStackTrace();
			}
			return null;
		}
		public static void main(String[] args) {
			Connection con = getCon();
			System.out.println(con+"내가 나왔네~");
		}
		
	}
			

