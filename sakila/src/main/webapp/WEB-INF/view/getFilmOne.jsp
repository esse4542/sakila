<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1>getFilmOne</h1>
     <table class="table">
         <tbody>
			<tr>
                   <td>filmId :</td>
                   <td>${filmId}</td>
            </tr>
            <tr>
                   <td>title :</td>
                   <td>${filmMap.title}</td>
            </tr>
            <tr>
                   <td>description :</td>
                   <td>${filmMap.description}</td>
            </tr>
            <tr>
                   <td>releaseYear :</td>
                   <td>${filmMap.releaseYear}</td>
            </tr>
            <tr>
                   <td>language :</td>
                   <td>${filmMap.language}</td>
            </tr>
            <tr>
                   <td>originalLaguageId :</td>
                   <td>${filmMap.originalLaguageId}</td>
            </tr>
            <tr>
                   <td>rentalDuration :</td>
                   <td>${filmMap.rentalDuration}</td>
            </tr>
            <tr>
                   <td>rentalRate :</td>
                   <td>${filmMap.rentalRate}</td>
            </tr>
            <tr>
                   <td>length :</td>
                   <td>${filmMap.length}</td>
            </tr>
            <tr>
                   <td>replacementCost :</td>
                   <td>${filmMap.replacementCost}</td>
            </tr>
            <tr>
                   <td>rating :</td>
                   <td>${filmMap.rating}</td>
            </tr>
            
            <tr>
                   <td>specialFeatures :</td>
                   <td>${filmMap.specialFeatures}</td>
            </tr>
            <tr>
                   <td>
                   	<a href="${pageContext.request.contextPath}/admin/getFilmActorListByFilm?filmId=${filmId}"><button type="button">출연자 배우 수정</button></a>
                   	actors :</td>
                   <td>${filmMap.actors}</td>
            </tr>
            <tr>
                   <td>lastUpdate :</td>
                   <td>${filmMap.lastUpdate}</td>
            </tr>
            <tr>
                   <td>stock of store1 :</td>
                   <td>${store1Stock}</td>
            </tr>
            <tr>
                   <td>stock of store2 :</td>
                   <td>${store2Stock}</td>
            </tr>
            
        </tbody>
    </table>
    	<div>
		    <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage}&searchWord=${searchWord}&category=${category}&rating=${rating}&rentalRate=${rentalRate}&searchActor=${searchActor}">글목록</a>
		    <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addFilm">addFilm</a>		
    	</div>
	
</div>
</body>
</html>