<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
var isChecked = false;
function checkValue(){
	if(!isChecked){
		alert("중복확인을 눌러 주세요.");
		return false;
	}
	var uiId = document.querySelector('#ui_id');
	if(uiId == "" && uiId ==null || uiId.value.trim().length<4 || uiId.value.trim().length>20){
		alert('아이디를 확인해주세요.');
		uiId.focus();
		return false;
	}
	var uiPassword = document.querySelector('#ui_password');
	if(uiPassword == null || uiPassword.value.trim().length<8 || uiPassword.value.trim().length>15){
		alert('비밀번호를 확인해주세요.');
		uiPassword.focus();
		return false;
	}
	var uiName = document.querySelector('#ui_name');
	if(uiName == null || uiName.value.trim().length<2 || uiName.value.trim().length>10){
		alert('이름을 확인해 주세요.');
		uiPassword.focus();
		return false;
	}
	var uiAge = document.querySelector('#ui_age');
	if(uiAge == null || uiAge.value<1 || uiAge.value>130){
		alert('나이를 확인해주세요.');
		uiPassword.focus();
		return false;
	}
	var uiBirth = document.querySelector('#ui_birth');
	if(!uiBirth.value){
		alert('생일을 확인해주세요.');
		uiPassword.focus();
		return false;
	}
	var uiNickName = document.querySelector('#ui_nickname');
	if(uiNickName == null || uiNickName.value.trim().length<4 || uiNickName.value.trim().length>30){
		alert('별명을 확인해주세요.');
		uiPassword.focus();
		return false;
	}
	
}
function checkId(){
	var id = document.querySelector('#ui_id').value;
	var xhr = new XMLHttpRequest();
		xhr.open('GET','/user/checkid?ui_id=' + id);
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4){
			if(xhr.status==200){
				if(id == null || id.trim().length<4 || id.trim().length>20){
					alert("아이디를 확인해주세요.");
					return false;
				}
				var res = JSON.parse(xhr.responseText);
					alert(res.msg);
				if(res.result == 'true'){
					isChecked = true;
				}
			}
		}
	}
	xhr.send();
}
</script>
<form action="/user/join" method="post" onsubmit="return checkValue()">
아이디 : <input type="text" name="ui_id" id="ui_id" onchange="isChecked = false;"> 
<button type="button" onclick="checkId()">중복확인</button><br>
패스워드 : <input type="password" name="ui_password" id="ui_password"><br>
이름 : <input type="text" name="ui_name" id="ui_name"><br>
나이 : <input type="number" name="ui_age" id="ui_age"><br>
생일 : <input type="date" name="ui_birth" id="ui_birth"><br>
전화번호 : <input type="text" name="ui_phone" id="ui_phone"><br>
이메일 : <input type="text" name="ui_email" id="ui_email"><br>
별명 : <input type="text" name="ui_nickname" id="ui_nickname"><br>
<button>회원가입</button>
</form>
</body>
</html>