<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="../categoryAdd.action" method="post" >

		名称：<input type="text" name="category.name" required="required"/>
		
		<input type="submit" value="添加"/>
		
	</form><s:actionerror/>
	
</body>
</html>
