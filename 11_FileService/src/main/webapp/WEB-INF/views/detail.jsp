<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			table, th, td{
				border: 1px solid black;
				border-collapse: collapse;
				padding: 5px 10px;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<table>
			<tr><td>번호</td><td>${info.idx}</td></tr>
			<tr><td>작성자</td><td>${info.user_name}</td></tr>
			<tr><td>제목</td><td>${info.subject}</td></tr>
			<tr><td>내용</td><td>${info.content}</td></tr>
			<tr>
				<th>다운로드</th>
				<td id="attach">
					<c:forEach items="${fileList }" var="file">
						<p><a href='download/${file.orifilename}/${file.newfilename}/'>${file.orifilename}</a></p>
					</c:forEach>
				</td>
			</tr>
		</table>
		<a href="./">목록보기</a>
	</body>
	<script></script>
</html>