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
	<div id="center-content">
		
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!-- 메인 해더 -->
	

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2"></td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree">
		      				<label>서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->
		
	</div>

</body>

<script type="text/javascript">
	$("#btnIdCheck").on("click", function(){
		console.log("중복체크 버튼 클릭");
		var uId = $("#txtId").val();
		console.log(uId);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/user/idCheck",		
			type : "post",
			//contentType : "application/json",
			//객체형식으로 바로 써도 됨
			
			data : {userId: uId},
			dataType : "json",
			
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				$("#tdMsg").val(1);
				if(result == false) {
					$("#tdMsg").text("사용할 수 없는 아이디입니다.");
				} else {
					$("#tdMsg").text("사용할 수 있는 아이디입니다.");
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	$("#joinForm").on("submit", function(){
		console.log("막음");
		var uId = $("#txtId").val();
		var upass = $("#txtPassword").val();
		var uname = $("#txtUserName").val();
		var agree = $("#chkAgree").is(":checked");
		console.log(uId);
		console.log(agree);
		if(uId == '') {
			alert("아이디를 입력해 주세요");
			return false;
		}
		if($("#tdMsg").val() != 1) {
			alert("중복체크를 해주세요");
			return false;
		}
		if(upass == '') {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if(uname == '') {
			alert("이름를 입력해 주세요");
			return false;
		}
		if(agree == false) {
			alert("약관에 동의해주세요");
			return false;
		}
		
		return true;
		
	});

</script>


</html>