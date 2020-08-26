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
			div{
				margin: 5px;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<c:if test="${sessionScope.id != null}">
			<div>
				안녕하세요 ${sessionScope.id}님
				<button onclick="location.href='logout'">로그아웃</button>
			</div>
			<button onclick="location.href='writeForm'">글쓰기</button>
		</c:if>
		<table>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>조회수</td>
				<td>작성 날짜</td>
				<td>삭제</td>
			</tr>
			<c:forEach items="${list }" var="list">
				<tr>
					<td>${list.idx}</td>
					<td>${list.user_name}</td>
					<td><a href="./detail?idx=${list.idx}">${list.subject}</a></td>
					<td>${list.bhit}</td>
					<td>${list.reg_date}</td>
					<td><a href="./delete?idx=${list.idx}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<script>
		var msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	</script>
</html>