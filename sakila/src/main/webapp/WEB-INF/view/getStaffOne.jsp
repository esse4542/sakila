<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>STAFF VIEW</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h2>StaffOne</h2>
    
     <table class="table">
         <tbody>
             <tr>
                <td>Staff ID</td>
                <td>${staffMap.ID}</td>
			</tr>
            <tr>
                <td>Staff name</td>
                <td>${staffMap.name}</td>
            </tr>
            <tr>
                <td>address</td>
                <td>${staffMap.address}</td>
            </tr>
             <tr>
                <td>Phone</td>
                <td>${staffMap.phone}</td>
            </tr>
            <tr>
                <td>zip code</td>
                <td>${staffMap.zipCode}</td>
            </tr>
            <tr>
                <td>City</td>
                <td>${staffMap.city}</td>
            </tr>
             <tr>
                <td>Country</td>
                <td>${staffMap.country}</td>
            </tr>
             <tr>
                <td>SID</td>
                <td>${staffMap.SID}</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getStaffList">목록</a>
    
</div>
</body>
</html>