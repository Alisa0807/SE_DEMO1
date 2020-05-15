<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="userUpdate.action" method="post" >
	
		<input type="hidden" name="page" value="${param.page}"/>

		<input type="hidden" name="user.id" value="${user.id}"/>
		<input type="hidden" name="user.username" value="${user.username}"/>
		<input type="hidden" name="user.password" value="${user.password}"/>
		
		用户：${user.username}<br>
		电话：<input type="text" name="user.phone" value="${user.phone}" required="required"/>
		<input type="submit" value="修改"/><s:actionerror/>
		
	</form>
	
</body>
</html>
