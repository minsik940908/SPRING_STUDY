<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			table, tr, td{
				border: 1px solid black;
				border-collapse: collapse;
				text-align: center;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<table>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>글 내용</td>
				<td>조회수</td>
				<td>작성 날짜</td>
			</tr>
			<tr>
				<td>${dto.idx}</td>
				<td>${dto.user_name}</td>
				<td>${dto.subject}</td>
				<td>${dto.content}</td>
				<td>${dto.bhit}</td>
				<td>${dto.reg_date}</td>
			</tr>
		</table>
		<a href="./back">뒤로가기</a>
		<button onclick="location.href='./updateForm?idx=${dto.idx}'">수정</button>
	</body>
	<script></script>
</html>