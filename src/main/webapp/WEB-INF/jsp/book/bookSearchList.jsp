<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>도서 검색 목록</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<div class="board">
    <table>
        <tr>
            <td>제목</td>
            <td>표지</td>
            <td>글쓴이</td>
            <td>출판사</td>
            <td>출판일</td>
            <td>옮긴이</td>
            <td>찜</td>
        </tr>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>
                    <a href="/library/bookDetail/${book.isbn}">${book.title}</a>
                </td>
                <td><img src="${book.imageUrl}"></td>
                <td>${book.author}</td>
                <td>${book.publisher}</td>
                <td>${book.pubDate}</td>
                <td>${book.translator}</td>
                <td>찜 버튼</td>
            </tr>
        </c:forEach>
    </table>
    <form id="regi_form" onsubmit="return search();">
        <input type="text" id="keyWord" name="keyWord">
        <button type="submit">검색</button>
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
<style>
    .board img {
        height: 90px;
    }

    #regi_form {
        padding: 10px;
    }
</style>
</html>