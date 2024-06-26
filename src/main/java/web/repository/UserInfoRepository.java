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
	
	public Map<String,String> selectUserInfo(String uiNum){
		try {
			Connection con = DBCon.getCon();
			
			String sql = "SELECT * FROM user_info WHERE UI_NUM=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, uiNum);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String,String> userInfo = new HashMap<>();
				userInfo.put("uiNum", rs.getString("UI_NUM"));
				userInfo.put("uiName", rs.getString("UI_NAME"));
				userInfo.put("uiId", rs.getString("UI_ID"));
				userInfo.put("uiPwd", rs.getString("UI_PWD"));
				return userInfo;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertUserInfo(Map<String,String> userInfo) {
		
		String sql = "INSERT INTO USER_INFO(UI_NAME, UI_ID, UI_PWD)";
		sql += " VALUES(?,?,?)";
		
		Connection con = DBCon.getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userInfo.get("uiName"));
			ps.setString(2, userInfo.get("uiId"));
			ps.setString(3, userInfo.get("uiPwd"));
			return ps.executeUpdate(); // 업데이트를 하여 결과값 출력
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateUserInfo(Map<String,String> userInfo) {
		
		String sql = "UPDATE USER_INFO";
		sql += " SET UI_NAME=?,";
		sql += " UI_ID=?, ";
		sql += " UI_PWD=? ";
		sql += " WHERE UI_NUM=?";
		Connection con = DBCon.getCon();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userInfo.get("uiName"));
			ps.setString(2, userInfo.get("uiId"));
			ps.setString(3, userInfo.get("uiPwd"));
			ps.setString(4, userInfo.get("uiNum"));
			return ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteUserInfo(String uiNum) {
		
		String sql = "DELETE FROM USER_INFO WHERE UI_NUM=?";
		
		Connection con = DBCon.getCon();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, uiNum);
			return ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	// 유저 이름 검색 기능
	public List<Map<String,String>> selectUserInfoName(String uiName) {
		List<Map<String,String>> selectUiName = new ArrayList<>();
			
		try {
			String sql = "SELECT UI_NAME, UI_NUM, UI_ID, UI_PWD FROM USER_INFO WHERE UI_NAME LIKE '%" + uiName + "%'";
			Connection con = DBCon.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> userInfoName = new HashMap<>();
				userInfoName.put("uiNum", rs.getString("UI_NUM"));
				userInfoName.put("uiName", rs.getString("UI_NAME"));
				userInfoName.put("uiId", rs.getString("UI_ID"));
				userInfoName.put("uiPwd", rs.getString("UI_PWD"));
				selectUiName.add(userInfoName); 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return selectUiName;
	}
}
