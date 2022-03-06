<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="../css/login.css" rel="stylesheet">
    <script src="../js/jquery-3.6.0.min.js"></script>
</head>
<body width="100%" height="100%">
<form id="login_form" onsubmit="return login();" class="loginForm">
    <h2>Login</h2>
    <div class="idForm">
        <input type="text" class="id" name="uid" id="uid" placeholder="ID">
    </div>
    <div class="passForm">
        <input type="password" class="pw" name="pwd" id="pwd" placeholder="PW">
    </div>
    <button type="submit" class="btn">
        LOG IN
    </button>
    <div class="bottomText">
        Don't you have ID? <a href="/library/signup">sign up</a>
    </div>
</form>
</body>
<script type="text/javascript">
    function login() {
        var id = $("#uid").val();
        var pw = $("#pwd").val();
        if ((id == '') || (pw == '')) {
            alert('plz check ID&PW...\n ID==lucas \n PW==52');
            return false;
        }
        var serData = $('#login_form').serialize();
        $.ajax({
            url: '/library/login',
            method: 'post',
            cache: false,
            data: serData,
            dataType: 'json',
            success: function (res) {
                if (res.ok) {
                    alert("로그인 성공");
                    location.href = "/library/main";
                    return false;
                }
                alert("로그인 실패");
                return false;
            },
            error: function (xhr, status, err) {
                alert('에러:' + err);
            }
        });
        return false;
    }
</script>
</html>
