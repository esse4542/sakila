<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>countryList</title>
</head>
<body>
	<h2>countryList</h2>
	<table border="1">
		<thead>
			<tr>
				<th>countryId</th>
				<th>country</th>
				<th>lastUpdate</th>
			</tr>
		</thead>
	
		<tbody>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.countryId}</td>
					<td>${c.country}</td>
					<td>${c.lastUpdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div>
	    <!-- 이전 -->
        <c:if test="${currentPage > 1}">
           <a href="${pageContext.request.contextPath}/admin/countryList?currentPage=${currentPage-1}">
              <button type="button">이전</button>
           </a>
        </c:if>

		<!-- 다음 -->
		<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/admin/countryList?currentPage=${currentPage+1}"><button type="button">다음</button></a>
		</c:if>
	</div>
</body>
</html>