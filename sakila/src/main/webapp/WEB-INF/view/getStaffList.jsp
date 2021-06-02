<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>getBoardList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h2>StaffList</h2>
    <ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a></li>
	</ul>
    <br>
    
 	<!-- 검색어 -->
 	<div align="right">
	    <form action="/admin/getStaffList" method="get">
	        <label for="searchWord">검색어 :</label> 
	        <input name="searchWord" type="text" placeholder="name"> 
	        <button type="submit">검색</button>
	    </form>    
	</div>
    
    <!-- 리스트 -->
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>address</th>
                <th>city</th>
                <th>country</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${staffList}">
                <tr>
                	<td>${s.ID}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/getStaffOne?ID=${s.ID}">${s.name}</a></td>
                    <td>${s.address}</td>
                    <td>${s.city}</td>
                    <td>${s.country}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 페이징 -->
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getStaffList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getStaffList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
    
</div>
</body>
</html>