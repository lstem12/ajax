package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.common.Conn;
import com.ajax.test.dao.UserinfoDAO;

public class UserinfoDAOImpl implements UserinfoDAO {
	Connection con = null;
	PreparedStatement ps = null;
	int result = 0;

	@Override
	public int insertUserInfo(Map<String, Object> userInfo) {
		try {
			con = Conn.open();
			String sql = "insert into user_info(ui_num, ui_name," + "ui_age, ui_birth, ui_id, ui_password, ui_phone,"
					+ "ui_email, ui_credat, ui_nickname)" + " values(seq_ui_num.nextval,?,?,?,?,?,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, userInfo.get("ui_name").toString());
			ps.setInt(2, (int) userInfo.get("ui_age"));
			ps.setString(3, userInfo.get("ui_birth").toString());
			ps.setString(4, userInfo.get("ui_id").toString());
			ps.setString(5, userInfo.get("ui_password").toString());
			ps.setString(6, userInfo.get("ui_phone").toString());
			ps.setString(7, userInfo.get("ui_email").toString());
			ps.setString(8, userInfo.get("ui_nickname").toString());
			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close(con, ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int deleteUserInfo(Map<String, Object> userInfo) {
		try {
			con = Conn.open();
			String sql = "delect from user_info where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) userInfo.get("ui_num"));
			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close(con, ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public int updateUserInfo(Map<String, Object> userInfo) {
		try {
			con = Conn.open();
			String sql = "update user_info set name=?, ui_age=?, ui_birth=?, ui_password=?,"
					+ " ui_phone=?, ui_email=?, ui_nickname=? where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userInfo.get("ui_name").toString());
			ps.setInt(2, (int) userInfo.get("ui_age"));
			ps.setString(3, userInfo.get("ui_birth").toString());
			ps.setString(4, userInfo.get("ui_password").toString());
			ps.setString(5, userInfo.get("ui_phone").toString());
			ps.setString(6, userInfo.get("ui_email").toString());
			ps.setString(7, userInfo.get("ui_nickname").toString());
			ps.setInt(8, (int) userInfo.get("ui_num"));
			result = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close(con, ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> userInfo) {
		ResultSet rs = null;
		try {
			con = Conn.open();
			String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, "
					+ "ui_password, ui_phone, ui_email, ui_credat, ui_nickname from user_info" 
					+ "where ui_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) userInfo.get("ui_num"));
			if (rs.next()) {
				Map<String, Object> rMap = new HashMap<>();
				rMap.put("ui_num", rs.getInt("ui_num"));
				rMap.put("ui_name", rs.getString("ui_name"));
				rMap.put("ui_age", rs.getString("ui_age"));
				rMap.put("ui_birth", rs.getString("ui_birth"));
				rMap.put("ui_id", rs.getString("ui_id"));
				rMap.put("ui_password", rs.getString("ui_password"));
				rMap.put("ui_ui_phone", rs.getString("ui_ui_phone"));
				rMap.put("ui_email", rs.getString("ui_email"));
				rMap.put("ui_credat", rs.getString("ui_credat"));
				rMap.put("ui_nickname", rs.getString("ui_nickname"));
				return rMap;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close(con, ps, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectUserListInfo(Map<String, Object> userInfo) {
		List<Map<String, Object>> userInfoList = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = Conn.open();
			String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, "
					+ "ui_password, ui_phone, ui_email, ui_credat, ui_nickname from user_info";
			ps = con.prepareStatement(sql);
			ps.setInt(1, (int) userInfo.get("ui_num"));
			if (rs.next()) {
				Map<String, Object> rMap = new HashMap<>();
				rMap.put("ui_num", rs.getInt("ui_num"));
				rMap.put("ui_name", rs.getString("ui_name"));
				rMap.put("ui_age", rs.getString("ui_age"));
				rMap.put("ui_birth", rs.getString("ui_birth"));
				rMap.put("ui_id", rs.getString("ui_id"));
				rMap.put("ui_password", rs.getString("ui_password"));
				rMap.put("ui_ui_phone", rs.getString("ui_ui_phone"));
				rMap.put("ui_email", rs.getString("ui_email"));
				rMap.put("ui_credat", rs.getString("ui_credat"));
				rMap.put("ui_nickname", rs.getString("ui_nickname"));
				userInfoList.add(rMap);
			}
			return userInfoList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close(con, ps, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<>();
		UserinfoDAO userinfoDao = new UserinfoDAOImpl();		
		map.put("ui_name", "이상화");
		map.put("ui_age", 31);
		//List<Map<String,Object>> userinfoList = userinfoDao.selectUserListInfo(map);
		
	}

}
