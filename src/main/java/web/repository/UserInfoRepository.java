package web.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.common.DBCon;

public class UserInfoRepository {

	public List<Map<String,String>> selectUserInfoList(){
		List<Map<String,String>> userInfoList = new ArrayList<>();
		
		try {
			Connection con = DBCon.getCon(); // 데이터베이스 연결시도
			
			String sql = "SELECT * FROM USER_INFO";
			PreparedStatement ps = con.prepareStatement(sql); // PrepareStatement를 사용하면 sql쿼리문을 담을 수 있다.
			ResultSet rs = ps.executeQuery(); //ResultSet을 활용하여 executeQuery를 사용하면 담은 sql쿼리문을 실행할 수 있다.
			
			while(rs.next()) {
				Map<String,String> userInfo = new HashMap<>();
				userInfo.put("uiNum", rs.getString("UI_NUM"));
				userInfo.put("uiName", rs.getString("UI_NAME"));
				userInfo.put("uiId", rs.getString("UI_ID"));
				userInfo.put("uiPwd", rs.getString("UI_PWD"));
				userInfoList.add(userInfo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return userInfoList;
	}
	
	
}
