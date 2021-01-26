/**
 * 
 */
$(function() {
	$("#regist").validate({
		rules: {
			userid: { //영대소문자, 숫자 허용 (6~12)
				required: true,
				validId: true, //규칙
				remote: {
					url: "/member/checkId",
					type: "post",
					data: {
						userid: function() {
							return $("#userid").val();
						}
					}
				}
			},
			password: {
				required: true,
				validPwd: true // 규칙
			},
			confirm_password: {
				required: true,
				validPwd: true,
				equalTo: "#password"
			},
			name: {
				required: true,
				rangelength: [2, 4]
			},
			gender: {
				required: true
			},
			email: {
				required: true,
				vaildEmail: true
			}

		},//rules end
		messages: {
			userid: {
				required: "아이디는 필수 속성입니다.",
				remote: "아이디 사용 불가"
			},
			password: {
				required: "비밀번호는 필수 속성입니다."
			},
			confirm_password: {
				required: "비밀번호는 필수 속성입니다.",
				equalTo: "비밀번호가 일치하지 않습니다"
			},
			name: {
				required: "이름은 필수 속성입니다.",
				rangelength: "이름은 2~4자리 입니다."
			},
			gender: {
				required: "성별을 선택해주세요."
			},
			email: {
				required: "이메일을 확인해주세요.",
				vaildEmail: "올바른 이메일 형식으로 작성해주세요."
			}

		},//messages end

		errorPlacement: function(error, element) { //에러메세지 잡기 <small>
			$(element).closest("form")
				.find("small[id='" + element.attr('id') + "']")
				.append(error);
		},
		success: function(label) {
			var name = label.attr('for');
			label.text(name + ' is ok!');
		}
	});
})

//	$.validator.addMethod("validId",function(value){
//	    var regId = /(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*])[A-Za-z\d!@#$%^*]{5,12}$/;
//	    return regId.test(value); //true or false return
//	},"아이디는 영대소문자, 숫자로 6~12자리로 만들수있습니다."); //false일때 메세지

$.validator.addMethod("validPwd", function(value) {
	var regPwd = /(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^*])[A-Za-z\d!@#$%^*]{8,15}$/;
	return regPwd.test(value);
}, "비밀번호는 영대소문자, 특수문자 조합으로 만들수있습니다.");

$.validator.addMethod("vaildName", function(value) {
	var regName = /[가-힣]{2,4}$/;
	return regName.test(value);
}, "이름을 올바르게 입력해주세요");

$.validator.addMethod("vaildEmail", function(value) {
	var regEmail = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
	return regEmail.test(value);
}, "이메일에 맞는 형식을 입력해주세요");
