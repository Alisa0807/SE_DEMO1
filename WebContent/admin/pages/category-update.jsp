<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<form action="categoryUpdate.action" method="post" >
	
		<input type="hidden" name="page" value="${param.page}"/>

		<input type="hidden" name="category.id" value="${category.id}"/>
		
		名称：<input type="text" name="category.name" value="${category.name}" required="required"/>
		
		<input type="submit" value="修改"/><s:actionerror/>
		
	</form>
	
</body>
</html>
