<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>실시간 테이블</title>
		<style>
			table,tr, th,td{
      		border: 1px solid black;
      		border-collapse: collapse;
      		padding: 5px 10px;
      		text-align: center;
      	}
      	input[type='text']{
      		background-color: lightgray;
      		color: #808080;
      	}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<h3>실시간 테이블</h3>
		<table>
			<thead>
				<tr>
					<th>NO</th>
					<th>Leader</th>
					<th>Member1</th>
					<th>Member2</th>
					<th>Member3</th>
					<th>Member4</th>
					<th>Member5</th>
				</tr>
			</thead>
			<tbody id="list">
				<!-- server에서 받아온 리스트 그리기 -->
			</tbody>
		</table>
	</body>
	<script>
		listCall();//초기 리스트 불러오기
		
		$("table").focusin(function(e){
			//e.target은 이벤트가 발생한 대상 요소를 의미
			console.log(e.target);
			e.target.style.backgroundColor="white";
			e.target.style.Color="black";
		});
		
		$("table").focusout(function(e){
			//e.target은 이벤트가 발생한 대상 요소를 의미
			console.log(e);
			e.target.style.backgroundColor="lightgray";
			e.target.style.Color="#808080";
			//저장 요청
			if(e.target.defaultValue != e.target.value){//값이 변경 되었을 경우만 수정 요청을 보낸다.
				console.log("수정요청");
				save(e.target);
			}
			
		});
		
		function save(elem){
			//num이 2인 leader 컬럼을 '홍길동' 으로 바꾸려면?
			//update set teams leader='홍길동' where num=2
			//바꿔줄 이름, 팀번호, 어떤 컬럼을 바꿀것인지?
			var params={};
			params.num=elem.classList[0].substring(4);
			params.col=elem.classList[1];
			params.val=elem.value;

			$.ajax({
				type:'get',
				url:'update',
				dataType:'JSON',
				data:params,
				success:function(d){
					console.log(d);
					if(d.sucess<1){
						alert('수정에 실패 했습니다');
					}
				},
				error:function(e){
					console.log(e);
				}
			});
		}
		
		function listCall(){
			$.ajax({
				tpye:'get',
				url:'listCall',
				dataType:'json',
				success:function(d){
					console.log(d);
					drawList(d.list);
				},
				error:function(e){
					console.log(e);
				}
			});
		}
		
		function drawList(list){
			var content ="";
			for(var i=0; i<list.length; i++){
				content +="<tr>";
				content +="<td>"+list[i].num+"</td>";
				content +="<td><input class='num_"+list[i].num+" leader' type='text' value='"+list[i].leader+"'/></td>";
				content +="<td><input class='num_"+list[i].num+" member1' type='text' value='"+list[i].member1+"'/></td>";
				content +="<td><input class='num_"+list[i].num+" member2' type='text' value='"+list[i].member2+"'/></td>";
				content +="<td><input class='num_"+list[i].num+" member3' type='text' value='"+list[i].member3+"'/></td>";
				content +="<td><input class='num_"+list[i].num+" member4' type='text' value='"+list[i].member4+"'/></td>";
				content +="<td><input class='num_"+list[i].num+" member5' type='text' value='"+list[i].member5+"'/></td>";
				content +="</tr>";
			}
			$("#list").empty();
			$("#list").append(content);
		}
		
		
	</script>
</html>