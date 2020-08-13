package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.dao.UserinfoDAO;
import com.ajax.test.dao.impl.UserinfoDAOImpl;
import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {
	private UserinfoDAO userInfoDao = new UserinfoDAOImpl();
	@Override
	public Map<String, String> doLogin(Map<String, String> user) {
		Map<String, String> rMap = new HashMap<>();
		rMap.put("result", "fail");
		rMap.put("msg", "아이디를 확인해주세요");
		if("test".equals(user.get("id"))) {
			rMap.put("msg", "비밀번호를 확인해주세요");
			if("test".equals(user.get("pwd"))) {
				rMap.put("result", "ok");
				rMap.put("msg", "로그인 완료");
			}
		}
		return rMap;
	}

	@Override
	public Map<String, Object> joinUserInfo(Map<String, Object> user) {
		int result = userInfoDao.insertUserInfo(user);
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("result", result);
		rMap.put("msg", "가입실패");
		if(result==1) {
			rMap.put("msg", "가입성공");
			rMap.put("url", "/views/user/login");
		}
		
		return rMap;
	}

	@Override
	public Map<String, String> checkId(String uiId) {
		Map<String, String> rMap = new HashMap<>();
		rMap.put("msg", "이미 있는 아이디입니다.");
		rMap.put("result", "false");
		if(userInfoDao.selectUserInfoByUiId(uiId)==null) {
			rMap.put("msg", "가입가능한 아이디 입니다.");
			rMap.put("result", "true");
		}
		return rMap;
	}

}
//18,19
