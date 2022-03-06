<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>도서 검색!</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <style>
        h1 {
            color: white;
        }
    </style>
</head>
<body bgcolor="#664499">
<%@include file="../fragments/header.jsp" %>
<div class="board">

    <div>도서 검색</div>
    <form id="regi_form" onsubmit="return search();">
        <input type="text" id="keyWord" name="keyWord">
        <button class="btn" type="submit">검색</button>
    </form>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function search() {
        var keyWord = $('#keyWord').val();
        location.href = "/library/bookSearch/" + keyWord;
        return false;
    }
</script>
</html>