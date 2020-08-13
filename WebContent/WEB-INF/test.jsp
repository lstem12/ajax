<%@page import="com.ajax.test.common.Conn"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection con = null;
	CallableStatement cs = null;
	try{
		con = Conn.open();
		String sql = "{call increase_salary(?, ?)}";
		cs = con.prepareCall(sql);
		cs.setString(1, "dragon");
		cs.setFloat(2, 1.05f);
		int result = cs.executeUpdate();
		out.println("dragon 의 급여를 0.05 인상했습니다.");
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		try{
			Conn.close(cs);
			Conn.close(con);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>