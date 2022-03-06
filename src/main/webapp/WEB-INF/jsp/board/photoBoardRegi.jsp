<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서관 가온누리</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <style>
        body {
            color: white;
        }
    </style>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<div class="board">
    <form id="uploadForm" onsubmit="return upload('${section}');">
        <input class="btn" type="button" onclick="backPage('${section}',${curPage})" value="뒤로">
        <button class="btn" type="submit">등록</button>
        <input type="hidden" name="section" value="photo">
        <div>제목</div>
        <span><input type="text" id="title" name="title"></span>
        <div>내용</div>
        <span><textarea id="content" name="content"></textarea></span>
        <div>File <input type="file" id='files' name="files" multiple="multiple"></div>
        <br>
    </form>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function backPage(section, no) {
        if (!confirm("글 작성을 취소하시겠어요?")) return;
        location.href = "/library/board/" + section + "/list/page/" + no;
    }

    function upload(section) {
        if ($('#files').val() == '') {
            alert('파일을 선택해주세요');
            return false;
        }
        var form = $('#uploadForm')[0]
        var formData = new FormData(form);
        $.ajax({
            url: '/library/upload',
            method: 'post',
            enctype: 'multipart/form-data',
            data: formData,
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false,
            timeout: 000000,
            success: function (res) {
                alert(res.msg);
                location.href = "/library/board/" + section;
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