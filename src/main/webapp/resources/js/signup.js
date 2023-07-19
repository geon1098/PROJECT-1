function checkAll(){

		

	if(signform.user_id.value == ""){
	alert("아이디를 입력해주세요!");
	signform.user_id.focus();
	return false;
	};
	
	if(signform.user_id.value == "관리자"){
    alert("잘못된 접근입니다.");
    signform.user_id.focus();
    return false;
    };

	if(signform.pwd.value == ""){
	alert("비밀번호를 입력해주세요!");
	signform.pwd.focus();
	return false;
	};
	
	if(signform.user_name.value == ""){
	alert("이름을 입력해주세요!");
	signform.user_name.focus();
	return false;
	};
	
	if(signform.nickname.value == ""){
	alert("닉네임을 입력해주세요!");
	signform.nickname.focus();
	return false;
	};
	
	
	if(signform.user_tel.value == ""){
	alert("전화번호를 입력해주세요!");
	signform.user_tel.focus();
	return false;
	}
	
	if(signform.user_tel.value.indexOf('-') == -1){
	alert("양식에 맞게 입력해주세요!");
	signform.user_tel.focus(); 
	return false;
	}

    if(signform.email.value == ""){
	alert("이메일을 입력해 주세요!");
	signform.email.focus();
	return false;
	}
	
	if(signform.email.value.indexOf('@') == -1){ // 존재한다면 -1이 아닌 숫자가 반환됨
	alert("양식에 맞게 입력해주세요!");
	signform.email.focus(); 
	return false;
	}
	
	if(signform.email.value.indexOf('.com') == -1){
	alert("이메일 형식이 아닙니다.");
	signform.email.focus(); 
	return false;
	}
}
	
     	 
      	 


