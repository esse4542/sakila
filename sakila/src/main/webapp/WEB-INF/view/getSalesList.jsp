<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        $('#IDForm').submit();   
     });  
});
</script>
</head>
<body>
<div class="container">
	<h2>SalesList</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a></li>
	</ul>
	
	<!-- 베스트셀러 목록 -->
	<h2>BestSellerList</h2>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Film Title</th>
                <th>Count</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${bestSellerList}">
                <tr>
                	<td>${b.title}</td>
                	<td>${b.cnt}</td>
            </c:forEach>
        </tbody>
    </table>
    
    <h2>월별 매출 리스트</h2>
    <!-- 매장별 조회 -->
    <form id="IDForm" action="${pageContext.request.contextPath}/admin/getSalesList" method="get">
   		Store:
		<select name="storeId">
			<option value="0">선택</option>
				<c:if test="${storeId == 1}">
		 			<option value="1" selected="selected">1호점</option>
		 		</c:if>
		 		<c:if test="${storeId != 1}">
		 			<option value="1">1호점</option>
		 		</c:if>
		 		<c:if test="${storeId == 2}">
		 			<option value="2" selected="selected">2호점</option>
		 		</c:if>
		 		<c:if test="${storeId != 2}">
		 			<option value="2">2호점</option>
		 		</c:if>
    	</select>
    	<button id="btn" type="button">조회</button>
	</form>
	
	
	<!-- 월별 매출액 리스트 -->
	
	<table class="table table-striped">
		<thead>
	  		<tr>
	  			<th>storeId</th>
				<th>store</th>
				<th>manager</th>
				<th>YEAR</th>
				<th>MONTH</th>
				<th>total Sales</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${monthlySalesList}">
				<tr>
					<td>${m.storeId}</td>
					<td>${m.store}</td>
					<td>${m.manager}</td>
					<td>${m.YEAR}</td>
					<td>${m.MONTH}</td>
					<td>${m.totalSales}</td>
				</tr>
			</c:forEach>
		</tbody>
   	</table>
	
</div>
</body>
</html>