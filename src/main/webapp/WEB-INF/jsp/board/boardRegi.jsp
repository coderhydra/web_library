<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서관 가온누리</title>
    <script src="/js/jquery-3.6.0.min.js"></script>

</head>
<body bgcolor="#664499">
<%@include file="../fragments/header.jsp" %>
<div class="board">
    <form id="regi_form" onsubmit="return regi('${section}');">
        <input type="hidden" name="section" value="${section}">
        <div>
            <span><input class="btn" "button" onclick="backPage('${section}',${curPage})" value="뒤로"></span>
            <span><button class="btn" type="submit">등록</button></span>
        </div>
        <div class="regiTitle">
            <span>제목</span><span><input type="text" id="title" name="title"></span>
        </div>
        <div class="regiContent">
            <span>내용</span>
        </div>
        <div>
            <span><textarea rows="20" cols="100" id="content" name="content"></textarea></span>
        </div>
    </form>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function regi(section) {
        var serData = $("#regi_form").serialize();
        $.ajax({
            method: 'post',
            url: '/library/board/regi',
            data: serData,
            dataType: 'json',
            cache: false,
            success: function (res) {
                if (res.saved == 1) {
                    alert("글이 등록 되었습니다");
                    location.href = "/library/board/" + section;
                } else if (res.saved == 2) {
                    alert('로그인 사용자만 사용할수 있는 서비스 입니다.');
                } else {
                    alert('등록에 실패하였습니다.');
                }
            },
            error: function (xhr, status, err) {
                alert("등록 에러");
                console.log("notice insert fail : " + error);
            }
        });
        return false;
    }

    function backPage(section, no) {
        if (!confirm("글 작성을 취소하시겠어요?")) return;
        location.href = "/library/board/" + section + "/list/page/" + no;
    }
</script>
<style>
    .regiTitle {
        display: inline-block;
        float: left;
        margin-left: 30px;
        margin-bottom: 9px;
        font-size: 1.5em;
    }

    .regiContent {
        display: inline-block;
        float: center;
        font-size: 1.5em;

    }
</style>
</html>