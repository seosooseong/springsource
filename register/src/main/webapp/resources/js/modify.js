/**
 * 비밀번호 수정
 */
$(function(){
	$("#changePwd").validate({
		rules:{
			password:{
                required: true,
                validPwd: true // 규칙
            },
            new_password:{
                required: true,
                validPwd: true,
            },
			confirm_password:{
                required: true,
                validPwd: true,
                equalTo:"#new_password"
            }
		},//rules
		
		message:{
			password:{
                required: "현재 비밀번호를 필수 요소입니다.",
                validPwd: "비밀번호는 영대소문자, 특수문자 조합으로 만들수있습니다."
            },
            new_password:{
                required: "새로운 비밀번호는 필수 요소입니다.",
                
            },
			confirm_password:{
                required: "새로운 비밀번호 확인은 필수 요소입니다.",
                equalTo:"비밀번호가 같은지 확인해주세요"
            }
		},//message
		errorPlacement: function(error,element){ //에러메세지 잡기 <small>
			$(element).closest("form")
				      .find("small[id='"+ element.attr('id')+"']")
					  .append(error);
		}
	});
})

$.validator.addMethod("validPwd",function(value){
	    var regPwd = /(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*])[A-Za-z\d!@#$%^*]{8,15}$/;
	    return regPwd.test(value);
	},"비밀번호는 영대소문자, 특수문자 조합으로 만들수있습니다.");