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
		<form id="fileUpload" action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="file" onchange="fileView(this)"/>
		</form>
	</body>
	<script>
		var filePath = "${path}";//업로드 후 반환 되는 업로드 경로
		console.log(filePath);
		
		//만약 업로드 한 파일이 있다면
		if(filePath != ''){
			var content = "<img src='/${path}' width='250'/>";
			content += "<input id='${path}' type='button' value='삭제' onclick='del(this)'/>";
			var elem = window.opener.document.getElementById('editable');//writeForm의 id = editable을 부른다.
			elem.innerHTML += content;//그리고 그 안에 content를 쓴다.
			self.close();//현재 창은 닫는다.
		}
		
		function fileView(elem){
			//console.log(elem);
			$("#fileUpload").submit();
		}
	</script>
</html>