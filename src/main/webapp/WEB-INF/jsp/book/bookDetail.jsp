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
    <form id="detail_form" onsubmit="return inCart();">
        <input type="hidden" id="title" name="title" value="${book.title}">
        <input type="hidden" id="author" name="author" value="${book.author}">
        <input type="hidden" id="publisher" name="publisher" value="${book.publisher}">
        <input type="hidden" id="isbn" name="isbn" value="${book.isbn}">
        <input type="hidden" id="imageUrl" name="imageUrl" value="${book.imageUrl}">
        <button class="addBtn" type="submit">담기</button>
    </form>
    <form class="goRight" id="search" onsubmit="return search();">
        <input type="text" id="keyWord" name="keyWord">
        <button class="btn" type="submit">검색</button>
    </form>
    <div class="detail">
        <div class="title"><span>${book.title}</span></div>
        <div><span>지은이</span> <span>${book.author}</span></div>
        <div><span>출판사/</span> <span>${book.publisher}</span>
            <span>,출판일/</span> <span>${book.pubDate}</span></div>
        <div><span>옮긴이</span> <span>${book.translator}</span>
            <span>isbn</span><span>${book.isbn}</span></div>
        <div><img src="${book.imageUrl}"></div>
        <div id="description">${book.description}</div>
    </div>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function search() {
        var keyWord = $('#keyWord').val();
        location.href = "/library/bookSearch/" + keyWord;
        return false;
    }

    function inCart() {
        var serData = $('#detail_form').serialize();
        $.ajax({
            url: '/library/cart/tocart',
            method: 'post',
            cache: false,
            data: serData,
            dataType: 'json',
            success: function (res) {
                if (res.ok) {
                    alert("장바구니에 저장 성공")
                    location.href = '/library/cart';
                } else {
                    alert("장바구니에 들어있는 책입니다");
                }
            },
            error: function (xhr, status, err) {
                alert("error" + err);
            }
        });
        return false;
    }
</script>
<style>
    .detail {
        font-size: 1.5em;
        margin-top: 30px;
    }

    .board img {
        width: 300px;
    }

    .addBtn {
        all: unset;
        background: #6F4E37;
        padding-right: 10px;
        padding-left: 10px;
        color: #fff;
        border: none;
        position: relative;
        height: 30px;
        font-size: 1em;
        cursor: pointer;
        transition: 800ms ease all;
        outline: none;
        width: 30px;
        display: inline-block;
        float: left;
        margin-left: 30px;
        margin-bottom: 9px;
    }

    .title {
        all: unset;
        display: flex;
        justify-content: center;
        float: center;
        font-size: 1.3em;
    }

    #description {
        font-size: 0.7em;
    }
</style>
</html>