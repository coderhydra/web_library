function login() {	
	var id= $("#uid").val();
	var pw= $("#pwd").val();
	if((id=='') || (pw=='')){
		alert('plz check ID&PW...\n ID==lucas \n PW==52');
		return false;
	}
      var serData = $('#login_form').serialize();
	$.ajax({
	     url:'/library/login',
	     method:'post',
	     cache:false,
	     data:serData,
	     dataType:'json',
	     success:function(res){
	      alert(res.ok ? "로그인 성공": "로그인 실패");
	     },
	     error:function(xhr,status,err){
	        alert('에러:'+err);
	     }
	  });
	  return false;
  }