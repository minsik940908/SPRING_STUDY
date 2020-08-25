<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style></style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<table>
			<tr>
				<td>ID</td>
				<td>이름</td>
				<td>나이</td>
				<td>성별</td>
				<td>이메일</td>
			</tr>
			<c:forEach items="${list}" var="info">
				<tr>
					<td>${info.id}</td>
					<td>${info.name}</td>
					<td>${info.age}</td>
					<td>${info.gender}</td>
					<td>${info.email}</td>
				</tr>
			</c:forEach>
		</table>
		
		<fieldset>
			<form action="update" method="post">
				<p>수정 ID : <input type="text" name="id"/></p>
				<p>PASS : <input type="text" name="pw"/></p>
				<p>NAME : <input type="text" name="name"/></p>
				<p>EMAIL : <input type="text" name="email"/></p>
				<button>수정요청</button>
			</form>
		</fieldset>
		
		<fieldset>
			<form action="multi" method="post">
				<p>이름이  <input type="text" name="userName"/> 이거나 <input type="text" name="userName"/> 인 회원 찾기</p>
				<button>찾기</button>
			</form>
		</fieldset>
	</body>
	<script></script>
</html>