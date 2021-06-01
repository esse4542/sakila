<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getFilmList</title>
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
        $('#customerForm').submit();   
     });  
});
</script>
<title>CustomerList</title>
</head>
<body>
<div class="container">
	<h2>CustomerList</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getCustomerList">Customer</a></li>
	</ul>
	
	<div>
		<form id="customerForm" action="${pageContext.request.contextPath}/admin/getCustomerList" method="get">
			Name :
		 	<input type="text" name="searchWord" value="${searchWord}">
		 	
		 	<button id="btn" type="button">검색</button>
		</form>
	</div>
	
		<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>address</th>
				<th>zipCode</th>
				<th>phone</th>
				<th>city</th>
				<th>country</th>
				<th>notes</th>
				<th>SID</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="c" items="${customerList}">
				<tr>
					<td>${c.ID}</td>
					<td>${c.name}</td>
					<td>${c.address}</td>
					<td>${c.zipCode}</td>
					<td>${c.phone}</td>
					<td>${c.city}</td>
					<td>${c.country}</td>
					<td>${c.notes}</td>
					<td>${c.SID}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징 -->
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getCustomerList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getCustomerList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
</div>	
</body>
</html>