<%@ include file="/WEB-INF/view/include.jsp" %>
<!DOCTYPE html ">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>post</title>
</head>
<body>
	<h1>post</h1>
	
	
	<div id="errMsg">${errMsg}</div>
	
	<div>
	    <a href="../post/getPosts">post list</a>
	</div>
	
	<div>
	<table border=1>
		<tr>
			<td>postId</td>
			<td>${post.postId}</td> 
		</tr>
		
		<tr>
			<td>userId</td>
			<td>${userId}<br/>
					<a href="../user/getUser.json?userId=${post.userId}">${post.userId}</a>
    				<br/>
    				<a href="../user/getUser?userId=${post.userId}">html</a>
    				<br/>
    				<a href="../user/getUser.xml?userId=${post.userId}">xml</a>
			</td> 
		</tr>
		<tr>
			<td>title</td>
			<td>${post.title}</td> 
		</tr>
		<tr>
			<td>content</td>
			<td><pre>${post.content}</pre></td> 
		</tr>
		
		<tr>
			<td>isDeleted</td>
			<td>${post.isDeleted}</td> 
		</tr>
		
		<tr>
			<td>createTime</td>
			<td>${post.createTime}, <fmt:formatDate value="${ mytld:convertDateFromIntSeconds(post.createTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
			</td>    			
		</tr>
		<tr>
			<td>updateTime</td>
			<td>${post.updateTime}, <fmt:formatDate value="${ mytld:convertDateFromIntSeconds(post.updateTime) }" pattern="yyyyMMdd-HHmmss" type="both"/>
			</td>    			
		</tr>
		
		<tr>
			<td>action</td>
			<td>
				<a href="../post/delete.json?postId=${post.postId}">delete</a>
   			</td>
		</tr>
		
		
		<tr>
		</tr>
    </table>
	</div>
	
	
	<div>
	    <h2>update post</h2>
		<form action="<c:url value='../post/update.json'/>" method="POST" target="_blank">
			<input type='hidden' name='postId' value="${post.postId}"/>	
	      <table>
	        <tr><td>title:</td><td><input type='text' name='title' value=''/></td></tr>
	        <tr><td>content:</td><td><textarea name="content" cols="100" rows="5"></textarea></td></tr>
	        <tr><td colspan='2'></td></tr>
	        <tr><td colspan='2'><input type="submit" value="update post"/></td></tr>
	      </table>
	    </form>
	</div>
	
	
	
</body>
</html>