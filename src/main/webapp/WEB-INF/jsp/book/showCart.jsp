<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>도서관 가온누리</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
</head>
<body bgcolor="#664499">
<%@include file="../fragments/header.jsp" %>
<div class="board">
    <h3>Shopping Cart</h3>
    <table>
        <thead>
        <tr>
            <th class="bhead"></th>
            <th class="bheadTitle">제목</th>
            <th class="bhead">지은이</th>
            <th class="bhead">출판사</th>
            <th class="bhead">현황</th>
            <th class="bhead">대출</th>
            <th class="bhead">예약</th>
            <th class="bhead">배달</th>
            <th class="bhead">삭제</th>
        </tr>
        </thead>
        <c:forEach var="item" items="${list}">
            <tr>
                <td><img src="${item.imageUrl}"></td>
                <td>${item.title}</td>
                <td>${item.author}</td>
                <td>${item.publisher}</td>
                <td>${item.return_time}/${item.reserve}</td>
                <td>
                    <button class="btn" onclick="rent(${item.isbn});">대출</button>
                </td>
                <td>
                    <button class="btn" onclick="reserve(${item.isbn});">예약</button>
                </td>
                <td>
                    <button class="btn" onclick="delivery(${item.isbn});">배달</button>
                </td>
                <td>
                    <button class="btn" onclick="del(${item.isbn});">삭제</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <br>
    <button class="deleteBtn" onclick="cartEmpty();">비우기</button>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function del(isbn) {
        var reqURL = '/library/cart/delete/' + isbn;
        if (!(confirm('정말로 바구니에서 삭제할까요?'))) return;
        location.href = reqURL;
    }

    function cartEmpty() {
        if (!(confirm('정말로 책바구니를 비울까요?'))) {
            return;
        }
        location.href = '/library/cartempty/';
    }

    function rent(isbn) {
        if (!confirm("선택한 도서를 대출하시겠어요?")) return;
        $.ajax({
            url: '/library/cart/rent/' + isbn,
            method: 'post',
            cache: false,
            dataType: 'json',
            success: function (res) {
                alert(res.msg);
                if (res.msg == "이미 3개이상의 도서를 대출중입니다.(대출가능 도서는 3개입니다.)") return false;
                if (res.msg == "대출중인 도서입니다") return false;
                if (res.msg == "로그인한 사용자만 사용가능한 기능입니다") {
                    if (confirm("로그인 하시겠어요?")) {
                        location.href = '/library';
                        return false;
                    }
                    return false;
                }
                location.href = '/library/cart/delete/' + isbn;
            },
            error: function (xhr, status, err) {
                alert('error:' + err);
            }
        });
        return false;
    }

    function reserve(isbn) {
        if (!confirm("선택한 도서를 예약하시겠어요?")) return;
        $.ajax({
            url: '/library/cart/reserve/' + isbn,
            method: 'post',
            cache: false,
            dataType: 'json',
            success: function (res) {
                if (res.msg == "로그인한 사용자만 사용가능한 기능입니다") {
                    if (confirm("로그인 하시겠어요?")) {
                        location.href = '/library';
                        return false;
                    }
                } else {
                    alert(res.msg + "번으로 예약 되었습니다.");
                    location.href = '/library/cart/delete/' + isbn;
                }
            },
            error: function (xhr, status, err) {
                alert('error:' + err);
            }
        });
        return false;
    }

    function delivery(isbn) {
        if (!confirm("선택한 도서를 배달신청하시겠어요?")) return;
        $.ajax({
            url: '/library/cart/delivery/' + isbn,
            method: 'post',
            cache: false,
            dataType: 'json',
            success: function (res) {
                alert(res.msg);
                if (res.msg == "이미 3개이상의 도서를 대출중입니다.(대출가능 도서는 3개입니다.)") return false;
                if (res.msg == "대출중인 도서입니다") return false;
                if (res.msg == "로그인한 사용자만 사용가능한 기능입니다") {
                    if (confirm("로그인 하시겠어요?")) {
                        location.href = '/library';
                        return false;
                    }
                    return false;
                }
                location.href = '/library/cart/delete/' + isbn;
                return false;
            },
            error: function (xhr, status, err) {
                alert('로그인 하였는지 확인해주세요 error=' + err);
            }
        });
        return false;
    }
</script>
<style>
    .board img {
        height: 90px;
    }

    .deleteBtn {
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
        width: 60px;
        display: inline-block;
        float: center;
        margin-bottom: 15px;
        margin-top: -30px;
    }

    table {
        display: inline-block;
        float: center;
    }

</style>
</html>