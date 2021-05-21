<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoardfile</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').click(function() {
			console.log('btn click...');
			$('#addForm').submit();
		});
	});
</script>
</head>
<body>
	<h2>파일 추가</h2>
	<form id="addForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/admin/addBoardfile">
		<div>
			boardId : 
			<input type="text" id="boardId" name="boardId" value="${boardId}" readonly="readonly">
		</div>
		<div>
			<input type="file" id="multipartFile" name="multipartFile">
		</div>
		<div>
			<button id="btn" type="button">파일 추가</button>
		</div>
	</form>
</body>
</html>