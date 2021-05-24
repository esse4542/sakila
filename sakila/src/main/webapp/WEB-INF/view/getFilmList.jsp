<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<div class="container">
	<h2>FilmList</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
	</ul>

	 <table class="table table-striped">
        <thead>
            <tr>
                <th>FID</th>
                <th>title</th>
                <th>description</th>
                <th>category</th>
                <th>price</th>
                <th>length</th>
                <th>rating</th>
                <th>actors</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${filmList}">
                <tr>
                	<td>${f.FID}</td>
                	<td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${f.FID}">${f.title}</a></td>
                	<td>${f.description}</td>
                	<td>${f.category}</td>
                	<td>${f.price}</td>
                	<td>${f.length}</td>
                	<td>${f.rating}</td>
                	<td>${f.actors}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    	
	<!-- 검색어 -->
 	<div align="right">
	    <form action="/admin/getFilmList" method="post">
	        <label for="searchWord">검색어 :</label> 
	        <input name="searchWord" type="text" placeholder="title"> 
	        <button type="submit">검색</button>
	    </form>    
	
	
       <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
    </div>
</div>    
</body>
</html>