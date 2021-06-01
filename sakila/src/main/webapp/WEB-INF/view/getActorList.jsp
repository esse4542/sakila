<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function() {
	 $('#btn').click(function() {
		 console.log('btn click...');
        $('#actorForm').submit();   
     });  
});
</script>
</head>
<body>
<div class="container">
	<h2>ActorList</h2>
	
	
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">홈</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getCustomerList">Customer</a></li>
	</ul>
	
	
	<div>
		<form id="actorForm" action="${pageContext.request.contextPath}/admin/getActorList" method="get">
			
			searchWord :
			<input type="text" name="searchWord" value="${searchWord}">

			<button id="btn" type="button">검색</button>
		</form>
	</div>
	
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>actorId</th>
				<th>name</th>
				<th>filmInfo</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="a" items="${actorList}">
			<tr>
				<td>${a.actorId}</td>
				<td><a href="${pageContext.request.contextPath}/admin/addActor?actorId=${a.actorId}">${a.name}</a></td>
				<td>${a.filmInfo}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<!-- 페이징 -->
    <ul class="pager">
		<c:if test="${currentPage > 1}">
		    <li class="previous"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
		</c:if>
		<c:if test="${currentPage < lastPage}">
		    <li class="next"><a href="${pageContext.request.contextPath}/admin/getActorList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
		</c:if>
   </ul>
</div>	
</body>
</html>