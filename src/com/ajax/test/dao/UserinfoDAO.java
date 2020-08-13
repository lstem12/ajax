package com.ajax.test.dao;

import java.util.List;
import java.util.Map;

public interface UserinfoDAO {
	int insertUserInfo(Map<String,Object> userInfo);
	int deleteUserInfo(Map<String,Object> userInfo);
	int updateUserInfo(Map<String,Object> userInfo);
	Map<String,Object> selectUserInfo(Map<String,Object> userInfo);
	Map<String,Object> selectUserInfoByUiId(String uiId);
	List<Map<String,Object>> selectUserListInfo(Map<String,Object> userInfo);
}
