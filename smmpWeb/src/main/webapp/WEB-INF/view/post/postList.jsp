<%@ include file="/WEB-INF/view/include.jsp" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post List</title>
</head>
<body>
	<h1>Post List</h1>
	
	<div id="errMsg">${errMsg}</div>
	
	<div>
	<table border=1>
			<tr>
    			<td>postId</td> 
    			<td>user</td>
    			<td>title</td> 
    			<td>content</td>    			
    			<td>isDeleted</td>
    			
    			<td>createTime</td>    			
    			<td>updateTime</td>
    			<td>action</td>
    		</tr>
    		    
		<c:forEach items="${postList}" var="post">  
    		<tr>
    			<td><a href="../post/getById.json?postId=${post.postId}">${post.postId}</a>
    				<br/>
    				<a href="../post/getById.html?postId=${post.postId}">html</a>
    				<br/>
    				<a href="../post/getById.xml?postId=${post.postId}">xml</a>
    			</td>
    			<td><a href="../user/getUser.json?userId=${post.userId}">${post.userId}</a>
    				<br/>
    				<a href="../user/getUser?userId=${post.userId}">html</a>
    				<br/>
    				<a href="../user/getUser.xml?userId=${post.userId}">xml</a>
    			</td>
    			<td>${post.title}</td>
    			<td><pre>${post.content}</pre></td>
    			<td>${post.isDeleted}</td>
    			
    			<td><fmt:formatDate value="${ mytld:convertDateFromIntSeconds(songHuo.createTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
    			</td>
    			<td><fmt:formatDate value="${ mytld:convertDateFromIntSeconds(songHuo.updateTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
    			</td>
    			
    			<td>
					<a href="../post/delete.json?postId=${post.postId}">delete</a>
					<br/>
    			</td>
    			
    		</tr>
    	</c:forEach>
    </table>
	</div>
	
	
	<div>
	    <h2>insert post</h2>
		<form action="<c:url value='../post/insert.json'/>" method="POST" target="_blank">
	      <table>
	        <tr><td>title:</td><td><input type='text' name='title' value=''/></td></tr>
	        <tr><td>content:</td><td><textarea name="content" cols="100" rows="5"></textarea></td></tr>
	        <tr><td colspan='2'></td></tr>
	        <tr><td colspan='2'><input type="submit" value="insert post"/></td></tr>
	      </table>
	    </form>
	</div>
	
	
</body>
</html>