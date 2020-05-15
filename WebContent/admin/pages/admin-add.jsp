<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="<s:url value="/admin/adminAdd.action"/>" method="post" >

		用户：<input type="text" name="admin.username" required="required"/><br>
		密码：<input type="text" name="admin.password" required="required"/>
		
		<input type="submit" value="添加"/>
		
	</form><s:actionerror/>
	
</body>
</html>
