<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Home</title>
	</head> 
	<body>
	
	<P><a href="c1/info">c1-info</a> </P>




	<P><a href="logreg/info">logreg/info</a></P>
	
	<br /><a href="post/getPosts">post list</a>
	
	
	<br />
	<a href="logreg/logout">登出</a>
	<a href="logreg/logout?needJson=true">登出json</a>
	
	
	</body>
</html>
