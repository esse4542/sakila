<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <h2>고객 상세정보</h2>
     <table class="table">
         <tbody>
			<tr>
                   <td>ID :</td>
                   <td>${map.ID}</td>
            </tr>
            <tr>
                   <td>name :</td>
                   <td>${map.name}</td>
            </tr>
            <tr>
                   <td>address :</td>
                   <td>${map.address}</td>
            </tr>
            <tr>
                   <td>zipCode :</td>
                   <td>${map.zipCode}</td>
            </tr>
            <tr>
                   <td>phone :</td>
                   <td>${map.phone}</td>
            </tr>
            <tr>
                   <td>city :</td>
                   <td>${map.city}</td>
            </tr>
            <tr>
                   <td>country :</td>
                   <td>${map.country}</td>
            </tr>
            <tr>
                   <td>notes :</td>
                    <td>${map.notes}</td>
            </tr>
            <tr>
                   <td>SID :</td>
                   <td>${map.SID}</td>
            </tr>
        </tbody>
    </table>
    	<div>
		    <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList?">글목록</a>
    	</div>
	
</div>
</body>
</html>