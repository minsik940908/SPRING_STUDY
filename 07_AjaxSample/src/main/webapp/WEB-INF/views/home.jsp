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
		<h3>다음 내용을 url을 통해서 요청 해 봅시다.</h3>
		<li onclick="ajaxCall('list')">/list : ArrayList 형태를 반환</li>
		<li onclick="ajaxCall('map')">/map : HashMap 형태를 반환</li>
		<li onclick="ajaxCall('object')">/object : DTO 형태를 반환</li>
		
		<h3>@RestController를 활용 해 보자</h3>
		<li onclick="ajaxCall('./rest/list')">/list : ArrayList 형태를 반환</li>
		<li onclick="ajaxCall('./rest/map')">/map : HashMap 형태를 반환</li>
		<li onclick="ajaxCall('./rest/object')">/object : DTO 형태를 반환</li>
	</body>
	<script>
		function ajaxCall(reqUrl){
			$.ajax({
				url:reqUrl,
				type:'GET',
				dataType:'JSON',
				success:function(d){
					console.log(d);
				},
				error:function(e){
					console.log(e);
				}
			});
		}
	</script>
</html>