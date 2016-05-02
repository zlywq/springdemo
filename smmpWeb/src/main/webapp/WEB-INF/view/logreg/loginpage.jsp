<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Login</h1>

	<div id="login-error">${error}</div>

	<!--  
	<form action="../j_spring_security_check" method="post">
		<p>
			<label for="j_username">Username</label> <input id="j_username"
				name="j_username" type="text" />
		</p>
		<p>
			<label for="j_password">Password</label> <input id="j_password"
				name="j_password" type="password" />
		</p>
		<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
		<input type="submit" value="Login" />
	</form>
	-->
	
	<form name="f" action="<c:url value='../login'/>" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password'></td></tr>
        <tr><td><input type="checkbox" name="remember-me"></td><td>Don't ask for my password for two weeks</td></tr>

		<tr><td>needJson:</td><td><input type='text' name='needJson' value='true'/></td></tr>

        <tr><td colspan='2'><input name="submit" type="submit"></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>
      
      <!--  
      <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
      -->
    </form>
    
    <br/>
    <!--
    <p>看来login不支持通过.json的方式返回json数据</p>
    <form name="f" action="<c:url value='/login.json'/>" method="POST">
      <table>
        <tr><td>User:</td><td><input type='text' name='username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Password:</td><td><input type='password' name='password'></td></tr>
        <tr><td><input type="checkbox" name="remember-me"></td><td>Don't ask for my password for two weeks</td></tr>

        <tr><td colspan='2'><input name="submit" type="submit" value='login.json'></td></tr>
        <tr><td colspan='2'><input name="reset" type="reset"></td></tr>
      </table>
    </form>
    -->
    
    <a href="../logreg/reg">注册</a>
    
    

</body>
</html>