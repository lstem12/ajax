package com.ajax.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ajax.test.dao.UserinfoDAO;
import com.ajax.test.dao.impl.UserinfoDAOImpl;
import com.ajax.test.service.UserService;

public class UserServiceImpl implements UserService {
	private UserinfoDAO userInfoDao = new UserinfoDAOImpl();
	@Override
	public Map<String, Object> doLogin(Map<String, String> user) {
		Map<String, Object> rMap = new HashMap<>();
		rMap.put("result", "fail");
		rMap.put("msg", "아이디를 확인해주세요");
		String uiId = user.get("ui_id");
		Map<String,Object> tmpUser = userInfoDao.selectUserInfoByUiId(uiId);
		if(tmpUser != null) {
			String tmpUiPassword = tmpUser.get("ui_password").toString();
			String uiPassword = user.get("ui_password");
			rMap.put("result", "fail");
			rMap.put("msg", "비밀번호를 확인해주세요");
			if(tmpUiPassword.equals(uiPassword)) {
				rMap.put("result", "ok");
				rMap.put("msg", "로그인 완료");
				rMap.put("user", tmpUser);
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
		rMap.put("url", "/views/user/join");
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
