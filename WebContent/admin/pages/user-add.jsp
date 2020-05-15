<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="<s:url value="/admin/userAdd.action"/>" method="post" >

		用户：<input type="text" name="user.username" required="required"/><br>
		密码：<input type="text" name="user.password" required="required"/><br>
		电话：<input type="text" name="user.phone"/>
		
		<input type="submit" value="添加"/>
		
	</form><s:actionerror/>
	
</body>
</html>
