<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	console.log('ready...')
	$('#btn').click(function() {
		console.log('click...')
		$('#loginForm').submit();
	});
});
</script>
<title>Insert title here</title>
</head>
<body>
	<h2>Home</h2>
	<!-- 로그인 안되어 있을때 -->
	<a href="${pageContext.request.contextPath}/admin/getStaffList">직원목록</a>
	<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<c:if test="${loginStaff == null}">
		<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
			<div>
				email :
			</div>
			<div><input type="text" id="email" name="email" value="Mike.Hillyer@sakilastaff.com"></div>
			<div>
				password :
			</div>
			<div><input type="password" id="password" name="password"></div>
			<div>
				<button id="btn" type="button">로그인</button>
			</div>
		</form>
	
	</c:if>
	
	
	<!-- 로그인 되어있을 때 -->
	<c:if test="${loginStaff != null}">
		<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
		<a href="${pageContext.request.contextPath}/admin/logout">로그아웃</a> <!-- 로그인 한 사람만 로그아웃 할 수 있음 -->
	</c:if>
</body>
</html>