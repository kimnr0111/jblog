<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">


</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li id="basic" class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id }/admin/basic">기본설정</a></li>
				<li id="cate" class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id }/admin/cate">카테고리</a></li>
				<li id="write" class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id }/admin/write">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			
						<!-- 리스트 영역 -->
					</tbody>
					
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="cname" type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="cdesc" type="text" name="desc"></td>
		      			<td><input id="cid" type="hidden" name="id" value="${sessionScope.authUser.id}"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	//카테고리출력
	$(document).ready(function(){
		console.log("시작");
		$.ajax({
			
			url : "${pageContext.request.contextPath }/cate/list",		
			type : "post",
			dataType : "json",
			success : function(cateList){
				/*성공시 처리해야될 코드 작성*/
				console.log(cateList);
				for(var i=0;i<cateList.length;i++) {
					render(cateList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	//카테고리추가
	$("#btnAddCate").on("click", function(){
		console.log("카테고리추가")
		
		var cname = $("#cname").val();
		var cdesc = $("#cdesc").val();
		var cid = $("#cid").val();
		
		console.log("cname:" + cname + "," + "cdesc" + cdesc);
		
		var cateVo = {
				id: cid,
				cateName: cname,
				description: cdesc
		};
		
		console.log(cateVo);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/cate/cateInsert",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(cateVo),

			dataType : "json",
			success : function(vo){
				/*성공시 처리해야될 코드 작성*/
				console.log("성공");
				$("#cname").val("");
				$("#cdesc").val("");
				
				render(vo);
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	//카테고리삭제
	$("#cateList").on("click", "a", function(){
		event.preventDefault(); //a태그 기능 막기
		console.log("삭제버튼클릭");
		
		var $this = $(this); //현재 클릭한 엘리먼트
		
		var no = $this.data("delno");
		
		console.log(no);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/cate/cateDelete",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify({cateNo: no}),

			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				console.log("성공");
				if(count == 1) {
					$("#t-" + no).remove();
				} else {
					console.log("삭제실패");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	function render(cateVo) {
		var str = "";
		str += "<tr id='t-" + cateVo.cateNo + "'>";
		str += "	<td>" + cateVo.cateNo + "</td>";
		str += "	<td>" + cateVo.cateName + "</td>";
		str += "	<td>" + cateVo.count + "</td>";
		str += "	<td>" + cateVo.description + "</td>";
		str += "	<td class='text-center'>";
		str += "		<a href='' data-delno=" + cateVo.cateNo + "><img class='btnCateDel' src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a>";
		str += "    </td>";
		str += "</tr>";
		str += "";
		
		$("#cateList").append(str);
	}
	

</script>


</html>