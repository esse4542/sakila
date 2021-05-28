<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getFilmActorListByFilm</title>
</head>
<body>
	<h2>영화(filmId : ${filmId}) 출연자(actor) 수정</h2>
	<form action="${pageContext.request.contextPath}/admin/modifyFilmActor" method="post">
		<input type="hidden" name="filmId" value="${filmId}" readonly="readonly">
		
		<c:forEach var="m" items="${filmActorList}">
			<c:if test="${m.filmId == null}">
				<input type="checkbox" name="actorId" value="${m.actorId}">
			</c:if>
			<c:if test="${m.filmId != null}">
				<input type="checkbox" name="actorId" checked="checked" value="${m.actorId}">
			</c:if>
				<span style="color:red">${m.name.substring(0, 1)}</span>${m.name.substring(1)}&nbsp; <!-- 첫번째 문자 -->
		</c:forEach>
		<div>
			<button type="submit">출연배우 수정</button>
		</div>
	</form>
</body>
</html>