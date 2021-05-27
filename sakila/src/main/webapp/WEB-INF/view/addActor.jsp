<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script>
    $(document).ready(function() { 
        $('#btn').click(function() {
            if ($('#firstname').val() == '') {  
                alert('firstName을 입력하지 않았습니다! firstName을 입력하세요!');
           } else if ($('#lastname').val() == '') {
            	alert('lastName을 입력하지 않았습니다! lastName을 입력하세요!');
            } else {
                $('#addForm').submit(); 
            }
        });
    });
</script>
</head>
<body>
	<div class="container">
        <h1>AddActor</h1>
        <form id="addForm" action="${pageContext.request.contextPath}/admin/addActor" method="post">
            <div class="form-group">
            	firstName:
                <input class="form-control" name="firstName" id="firstName" type="text">
            </div>
             <div class="form-group">
              	lastName:
                <input class="form-control" name="lastName" id="lastName" type="text">
            </div>
            <div>
                <input class="btn btn-default" id="btn" type="button" value="입력">
				<input class="btn btn-default" type="reset"value="초기화"> 
				<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getActorList">글목록</a>
            </div>
        </form>
    </div>
</body>
</html>