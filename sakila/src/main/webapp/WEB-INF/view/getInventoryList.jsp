<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InventoryList</title>
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
        $('#inventoryForm').submit();   
     });  
});
</script>
</head>
<body>
<div class="container">
	<h2>InventoryList</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a></li>
	</ul>
	
	<div>
		<form id="inventoryForm" action="${pageContext.request.contextPath}/admin/getInventoryList" method="get">
			Name :
		 	<input type="text" name="searchWord" value="${searchWord}">
		 	
		 	<button id="btn" type="button">검색</button>
		</form>
	</div>
	
		<table class="table table-striped">
		<thead>
			<tr>
				<th>storeId</th>
				<th>title</th>
				<th>totalInventory</th>
				<th>rental</th>
				<th>stock</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="i" items="${inventoryList}">
				<tr>
					<td>${i.storeId}</td>
					<td>${i.title}</td>
					<td>${i.totalInventory}</td>
					<td>${i.rental}</td>
					<td>${i.stock}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 페이징 -->
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
</div>	
</body>
</html>