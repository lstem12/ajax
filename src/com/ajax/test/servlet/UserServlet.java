package com.ajax.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajax.test.service.UserService;
import com.ajax.test.service.impl.UserServiceImpl;
import com.google.gson.Gson;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/")+1;
		String cmd = request.getRequestURI().substring(idx);
		PrintWriter pw = response.getWriter();
		if("checkid".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			Map<String,String> rMap = userService.checkId(uiId);
			pw.println(gson.toJson(rMap));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idx = request.getRequestURI().lastIndexOf("/")+1;
		String cmd = request.getRequestURI().substring(idx);

		if ("login".equals(cmd)) {
			BufferedReader br = request.getReader();
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			Map<String, String> pMap = gson.fromJson(sb.toString(), Map.class);
			Map<String, String> rMap = userService.doLogin(pMap);
			if ("ok".equals(rMap.get("result"))) {
				HttpSession session = request.getSession();
				session.setAttribute("id", pMap.get("id"));

			}
			response.getWriter().append(gson.toJson(rMap));
		}else if("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.invalidate();
			Map<String,String> rMap = new HashMap<>();
			rMap.put("msg", "로그아웃 되었습니다.");
			response.getWriter().append(gson.toJson(rMap));
		}else if("join".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			if(uiId==null || uiId.trim().length()<4 || uiId.trim().length()>20) {
				throw new ServletException("올바르지 않은 아이디!");
			}
			String uiPassword = request.getParameter("ui_password");
			if(uiPassword==null || uiPassword.trim().length()<8 || uiPassword.trim().length()>15) {
				throw new ServletException("올바르지 않은 비밀번호!");
			}
			String uiName = request.getParameter("ui_name");
			if(uiName==null || uiName.trim().length()<2 || uiName.trim().length()>10) {
				throw new ServletException("올바르지 않은 이름!");
			}
			int uiAge = Integer.parseInt(request.getParameter("ui_age"));
			if(uiAge==0 || uiAge>130) {
				throw new ServletException("올바르지 않은 나이!");
			}
			String uiBirth = request.getParameter("ui_birth");
			if(uiBirth == null) {
				throw new ServletException("올바르지 않은 생년월일!");
			}
			uiBirth = uiBirth.replace("-", "");
			String uiPhone = request.getParameter("ui_phone");
			String uiEmail = request.getParameter("ui_email");
			String uiNickName = request.getParameter("ui_nickname");
			if(uiNickName == null || uiNickName.trim().length()<4 || uiNickName.trim().length()>30) {
				throw new ServletException("올바르지 않은 닉네임!");
			}
			Map<String,Object> user = new HashMap<>();
			user.put("ui_id", uiId);
			user.put("ui_password", uiPassword);
			user.put("ui_name", uiName);
			user.put("ui_age", uiAge);
			user.put("ui_birth", uiBirth);
			user.put("ui_phone", uiPhone);
			user.put("ui_email", uiEmail);
			user.put("ui_nickname", uiNickName);
			Map<String, Object> rMap = userService.joinUserInfo(user);
			request.setAttribute("rMap", rMap);
			RequestDispatcher rd = request.getRequestDispatcher("/views/common/msg");
			rd.forward(request, response);
		}
	}

}
