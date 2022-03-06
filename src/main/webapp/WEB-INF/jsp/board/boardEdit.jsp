<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>도서관 가온누리</title>
    <script src="/js/jquery-3.6.0.min.js"></script>

</head>
<body>
<%@include file="../fragments/header.jsp" %>
<form id="edit_form" onsubmit="return edit('${section}',${page});">
    <input type="hidden" id="id" name="id" value="${detail.id}">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" id="title" name="title" value="${detail.title}"></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td>${detail.writer}</td>
        </tr>
        <tr>
            <td>작성일</td>
            <td>${detail.insert_time}</td>
        </tr>
        <tr>
            <td>조회수</td>
            <td>${detail.view_cnt}</td>
        </tr>
        <tr>
            <td>내용</td>
            <td><textarea rows="20" cols="50" id="content" name="content">${detail.content}</textarea></td>
        </tr>
        <tr>
            <td>
                <button type="submit">수정</button>
                <input type="button" onclick="backPage('${section}',${page})" value="뒤로">
            </td>
        </tr>
    </table>
</form>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function edit(section, pg) {
        var serData = $("#edit_form").serialize();
        $.ajax({
            url: '/library/board/edit',
            method: 'post',
            cache: false,
            data: serData,
            dataType: 'text',
            success: function (res) {
                if (res) {
                    alert("글 수정 성공");
                    location.href = "/library/board/" + section + "/list/page/" + pg;
                } else alert("글 수정 실패 ");
                return false;
            },
            error: function (xhr, status, err) {
                alert("에러:" + err);
            }
        });
        return false;
    }

    function backPage(section, pg) {
        if (!confirm("글 수정을 취소하시겠어요")) return false;
        location.href = "/library/board/" + section + "/list/page/" + pg;
        return false
    }
</script>
</html>