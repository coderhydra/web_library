<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서관 가온누리</title>
    <script src="/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@include file="../fragments/header.jsp" %>
<div class="board">
    <button class="btn" id="regibtn" type="button" onclick="return regi('free',${page})">등록</button>
    <h2 class="boardTitle">자유게시판</h2>
    <table>
        <thead>
        <tr>
            <th class="bheadnum">번 호</th>
            <th class="bheadTitle">제 목</th>
            <th class="bhead">작성자</th>
            <th class="bhead">조회수</th>
            <th class="bheadDate">작성일</th>
            <th class="bheadend"></th>
            <th class="bheadend"></th>
        </tr>
        </thead>
        <c:forEach var="l" items="${pageInfo.list}">
            <tr>
                <td class="thborder">${l.id}</td>
                <td class="rowsTitle">
                    <a href="javascript:detail('${section}',${page},${l.id});">${l.title}</a>
                </td>
                <td>${l.writer}</td>
                <td>${l.view_cnt}</td>
                <td>${l.insert_time}</td>
                <td class="rowsEnd">
                    <button class="btn" type="button" onclick="return edit('${section}',${page},${l.id})">수정</button>
                </td>
                <td class="rowsEnd">
                    <button class="btn" type="button" onclick="return del('${section}',${page},${l.id})">삭제</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div id="pagination" class="page">
        <c:forEach var="i" items="${pageInfo.navigatepageNums}">
            <c:choose>
                <c:when test="${i==pageInfo.pageNum}">
                    <span class="boxNow"> ${i} </span>
                </c:when>
                <c:otherwise>
                    <span class="box"> <a href="/library/board/${section}/list/page/${i}">${i}</a></span>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <form style="display:inline" id="search_form" onsubmit="return search(${section});">
        <div class="goRight">
            <select class="selectbox" id="cmd">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="both">제목+내용</option>
                <option value="writer">작성자</option>
            </select>
            <input type="text" id="word" name="word">
            <button class="btn" type="submit">검색</button>
        </div>
    </form>
</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script type="text/javascript">
</script>
</html>
