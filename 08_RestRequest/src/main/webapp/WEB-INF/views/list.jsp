<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!-- ctrl+shift+r : 이름으로 리소스 찾기 -->
<!-- ctrl+shift+alt+l : 특정 구문이 있는 내용 찾기(퀵서치) -->
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
      <style>
      
      	table{
      		width: 100%;
      		height: 100%;
      	}
      	table, tr, th,td{
      		border: 1px solid black;
      		border-collapse: collapse;
      		padding: 5px 10px;
      		text-align: center;
      	}
      </style>
		<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
		<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
   </head>
<body>
   게시물 갯수 : 
   <select id="pagePerNum">
   		<option value="5">5</option>
   		<option value="10">10</option>
   		<option value="15">15</option>
   		<option value="20">20</option>
   </select>
   <table>
   		<thead>
	   		<tr>
	   			<th>번호</th>
	   			<th>제목</th>
	   			<th>작성일</th>
	   			<th>작성자</th>
	   			<th>조회수</th>
	   		</tr>
   		</thead>
   		<tbody id="boardList">  		
   		</tbody>
   		<tr>
   			<td id="paging" colspan="5">
   				<div class="container">
   					<nav arial-label="Page navigation" style="text-align:center">
   						<ul class="pagination" id="pagination"></ul>
   					</nav>
   				</div>
   		</tr>
   </table>
</body>
<script>

	listCall(1);
	
	$("#pagePerNum").change(function(){
		$('#pagination').twbsPagination('destroy');
		listCall(1);
	});

	function listCall(page){
		var ppn = $("#pagePerNum").val();
		var url="list/"+ppn+"/"+page;
		console.log("url : " + url);
		$.ajax({
			url:url,
			type:'get',
			dataType:'JSON',
			success:function(data){
				console.log(data);
				listPrint(data.list);//게시물 그리기
				//플러그인 사용
				$("#pagination").twbsPagination({
					startPage: data.currpage, //시작 페이지
					totalPages: data.range, //만들수 있는 총 페이지 수
					visiblePages: 5,//보여줄 페이지 수
					onPageClick: function(event, page){
						listCall(page);
						console.log(page);
					}
				});
				//pagingPrint(data);//페이징 처리(복잡한 방식)
			},
			error:function(e){
				console.log(e)
			}
		});
	}
	
	function pagingPrint(data){
		//총 만들수 있는 페이지까지 찍는 일
		var start = 1;//페이징 그룹 시작
		var end = 5;//페이징 그룹 끝
		var content = "";
		
		//페이징은 5 단위로 보여준다.
		//현재 페이지가 5를 넘어가면 이전 버튼이 생성
		
		// <이전  6 7 8 9 10 다음>
		
		if(data.currpage > 5){
			//data.currpage를 가지고 페이징 그룹의 start와 end를 구해야 한다.
			
			//5단위로 나눠서 0보다 크면 소숫점을 올림한다. 그리고 5칸식 넘어가야 하므로 5를 곱한다. => end값
			end = Math.ceil(data.currpage/5)*5;
			start = end -4;
			if(end > data.range){
				end = data.range
			}
			console.log(data.currpage/5);
			content += "<a href='#' onclick='listCall("+(start-1)+")'> 이전</a> ";
		}
		for(var i = start; i<=end; i++){
			if(i == data.currpage){
				content += "<b style='color:red'>"+i+"</b> ";
			}else{
				content += "<a href='#' onclick='listCall("+i+")'> "+i+"</a> ";
			}
		}
		
		//다음 버튼은 언제 만드나? end가 마지막 페이지(41)보다 작으면 생긴다
		if(end < data.range){
			content += "<a href='#' onclick='listCall("+(end+1)+")'> 다음</a> ";
		}
		
		
		$("#paging").empty();
		$("#paging").append(content);
	}
	function listPrint(list){
		console.log(list);
		var content = "";
		
		list.forEach(function(item){
			content += "<tr>";
			content += "<td>"+item.idx+"</td>";
			content += "<td>"+item.subject+"</td>";
			var date = new Date(item.reg_date);
			console.log(date.toLocaleDateString("ko-KR"));
			content += "<td>"+date.toLocaleDateString("ko-KR")+"</td>";//miliseconds 단위로 표기가 된다.
			content += "<td>"+item.user_name+"</td>";
			content += "<td>"+item.bhit+"</td>";
			content += "</tr>";
		});
		$("#boardList").empty();
		$("#boardList").append(content);
		content="";
	}

</script>
</html>