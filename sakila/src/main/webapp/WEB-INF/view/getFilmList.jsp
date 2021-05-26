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
<script>
	$(document).ready(function() {
		 $('#btn').click(function() {
			 console.log('btn click...');
	         $('#filmForm').submit();   
	      });  
	});
</script>
</head>
<body>
<div class="container">
	<h2>FilmList</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/home">home</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a></li>
	</ul>
	
	<div>
	<!-- 검색 내용 폼 -->
	<!-- 
		1. 카테고리별 o
		2. 가격별 o
		3. 제목 검색 o
		
		4. 등급별 4~5번은 각자
		5. 페이징 o
		
		6. 배우검색
		7. 타이틀 클릭 시 상세보기
	 -->
		 <form id="filmForm" action="${pageContext.request.contextPath}/admin/getFilmList" method="get">
		 	Category: 
		 	<select name="categoryName">
		 		<option value="">카테고리 선택</option>
			 	<c:forEach var="name" items="${categoryNameList}">
			 	
			 		<!-- 카테고리 이름이 동일 할때 -->
			 		<c:if test="${name == categoryName}">
			 			<option value="${name}" selected="selected">${name}</option>
			 		</c:if>
			 		
			 		 <!-- 카테고리 이름이 맞지 않을때 -->
			 		<c:if test="${name != categoryName}">
			 			<option value="${name}">${name}</option>
			 		</c:if>
			 	</c:forEach>
		 	</select>
		 	
		 	
		 	Price: 
	 		<select name="price">
		 		<option value="">가격 선택</option>
		 		<c:if test="${price == 0.99}">
		 			<option value="0.99" selected="selected">0.99</option>
		 		</c:if>
		 		<c:if test="${price != 0.99}">
		 			<option value="0.99">0.99</option>
		 		</c:if>
		 		
		 		<c:if test="${price == 2.99}">
		 			<option value="2.99" selected="selected">2.99</option>
		 		</c:if>
		 		<c:if test="${price != 2.99}">
		 			<option value="2.99">2.99</option>
		 		</c:if>
		 		
		 		<c:if test="${price == 4.99}">
		 			<option value="4.99" selected="selected">4.99</option>
		 		</c:if>
		 		<c:if test="${price != 4.99}">
		 			<option value="4.99">4.99</option>
		 		</c:if>
		 	</select>
		 	
		 	
		 	Rating : 
		 	<select name="rating">
		 		<option value="">등급별 선택</option>
		 		<c:if test="${rating == 'PG'}">
		 			<option value="PG" selected="selected">PG</option>
		 		</c:if>
		 		<c:if test="${rating != 'PG'}">
		 			<option value="PG">PG</option>
		 		</c:if>
		 		
		 		<c:if test="${rating == 'G'}">
		 			<option value="G" selected="selected">G</option>
		 		</c:if>
		 		<c:if test="${rating != 'G'}">
		 			<option value="G">G</option>
		 		</c:if>
		 		
		 		<c:if test="${rating == 'NC-17'}">
		 			<option value="NC-17" selected="selected">NC-17</option>
		 		</c:if>
		 		<c:if test="${rating != 'NC-17'}">
		 			<option value="NC-17">NC-17</option>
		 		</c:if>
		 		
		 		<c:if test="${rating == 'PG-13'}">
		 			<option value="PG-13" selected="selected">PG-13</option>
		 		</c:if>
		 		<c:if test="${rating != 'PG-13'}">
		 			<option value="PG-13">PG-13</option>
		 		</c:if>
		 		
		 		<c:if test="${rating == 'R'}">
		 			<option value="R" selected="selected">R</option>
		 		</c:if>
		 		<c:if test="${rating != 'R'}">
		 			<option value="R">R</option>
		 		</c:if>
		 	</select>
		 	
		 	<!--  
			등급별 선택 이 방법으로 했을 때 jsp에 나오지 않음.
		 	Roting :
		 	<select name = "rating">
				<option value = "">등급별 선택</option>
				<c:forEach var ="r" items="${rating}">
						<c:if test="${r == rating}">
							<option value="${r}" selected="selected">${r}</option>
						</c:if>
						
						<c:if test="${r != rating}">
							<option value="${r}">${r}</option>	
						</c:if>
					
				</c:forEach>
			</select>
		 	-->
		
	
			
		 	Title :
		 	<input type="text" name="searchWord" value="${searchWord}">
		 	
		 	Actors :
		 	<input type="text" name="searchActors" value="${searchActors}">
		 	
	  		<button id="btn" type="button">검색</button>
		</form>
	 </div>
			
	 
	

	 <table class="table table-striped">
        <thead>
            <tr>
                <th>FID</th>
                <th>title</th>
                <th>category</th>
                <th>price</th>
                <th>rating</th>
                
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${filmList}">
                <tr>
                	<td>${m.FID}</td>
                	<td><a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${m.FID}">${m.title}</a></td>
                	<td>${m.category}</td>
                	<td>${m.price}</td>
                	<td>${m.rating}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    

	<!-- 페이징 -->
    <ul class="pager">
		<c:if test="${currentPage > 1}">
		    <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&searchWord=${searchWord}&categoryName=${categoryName}&price=${price}&rating=${rating}&searchActors=${searchActors}">이전</a></li>
		</c:if>
		<c:if test="${currentPage < lastPage}">
		    <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&searchWord=${searchWord}&categoryName=${categoryName}&price=${price}&rating=${rating}&searchActors=${searchActors}">다음</a></li>
		</c:if>
   </ul>
</div>	 
</body>
</html>