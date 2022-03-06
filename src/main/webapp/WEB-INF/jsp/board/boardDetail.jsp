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
<div class="board">
    <div class="goRight">
        <input class="btn" type="button" onclick="backPage('${section}',${page})" value="뒤로">
        <button class="btn" type="button" onclick="return edit('${section}',${page},${detail.id})">수정</button>
        <button class="btn" type="button" onclick="return del('${section}',${page},${detail.id})">삭제</button>
    </div>
    <div>
        <h1></h1> </h1><span>제목</span><span>${detail.title}</span>
    </div>
    <div>
        <span>작성자</span><span>${detail.writer}</span>
    </div>
    <div>
        <span>작성일</span><span>${detail.insert_time}</span>
    </div>
    <div>
        <span>내용</span>
        <c:forEach var="img" items="${imgList}">
            <div><img class="tableImg" src="${img.imageUrl}"></div>
        </c:forEach>
        <div>
            <span>${detail.content}</span>
        </div>
    </div>
    <hr>
    <c:forEach var="r" items="${comments}">
        <form id="commentEdit${r.id}" onsubmit="return commentSubmit('${section}',${page},${detail.id},${r.id});">
            <input type="hidden" id="id" name="id" value="${r.id}">
            <span>${r.writer}</span>
            <span id="commentEditOn${r.id}">${r.comment}</span>
            <span>${r.time}</span>
            <span id="commentEditButton${r.id}">
	   <button type="button" onclick="return commentEditOn(${page},${detail.id},${r.id},'${r.comment}')">수정</button>
	   </span>
            <button type="button" onclick="return commentDel('${section}',${page},${detail.id},${r.id})">삭제</button>
            </td>
        </form>
    </c:forEach>
    <div class="goCenter">
        <form id="comment_form" onsubmit="return saveComment('${section}',${page},${detail.id})">
            <input type="hidden" name="parent_id" value="${detail.id}"/>
            <span><textarea rows="3" cols="300" id="comment" name="comment"></textarea></span>
            <span><button class="btn" id="comment" type="submit">댓글 남기기</button></span>
        </form>
    </div>

</div>
<%@include file="../fragments/footer.jsp" %>
</body>
<script>
    function edit(section, page, id) {
        $.ajax({
            url: "/library/board/writerCheck/" + id,
            method: 'get',
            cache: false,
            dataType: 'json',
            success: function (res) {
                if (res.msg == "로그인하지 않으셨습니다.") {
                    alert(res.msg);
                    if (!confirm("로그인 하시겠어요?")) return;
                    location.href = "/library";
                } else if (res.msg == "master") {
                    location.href = "/library/board/" + section + "/edit/" + page + "/" + id;
                } else {
                    alert("운영진만 사용할수 있는 기능입니다.");
                }
                return false;
            },
            err: function (xhr, status, err) {
                alert(err);
            }
        });
        return false;
    }

    function del(section, pg, id) {
        $.ajax({
            url: "/library/board/writerCheck/" + id,
            method: 'get',
            cache: false,
            dataType: 'json',
            success: function (res) {
                if (res.msg == "로그인하지 않으셨습니다.") {
                    alert(res.msg);
                    location.href = "/library";
                } else if (res.msg == "master") {
                    if (!confirm("정말로 글을 삭제하시겠어요?")) return;
                    $.ajax({
                        url: "/library/board/delete/" + id,
                        method: 'get',
                        cache: false,
                        dataType: 'json',
                        success: function (res) {
                            if (res.ok) {
                                location.href = "/library/board/" + section + "/list/page/" + pg;
                            } else {
                                alert("삭제 실패");
                            }
                            return false;
                        },
                        err: function (xhr, status, err) {
                            alert(err);
                        }
                    });
                    return false;
                } else {
                    alert("운영진만 사용할수 있는 기능입니다.");
                }
                return false;
            },
            err: function (xhr, status, err) {
                alert(err);
            }
        });
        return false;
    }

    function saveComment(section, page, id) {
        var serData = $("#comment_form").serialize();
        $.ajax({
            url: '/library/board/comment',
            method: 'post',
            cache: false,
            data: serData,
            dataType: 'text',
            success: function (res) {
                if (res == 1) {
                    alert("댓글이 등록되었습니다.");
                    location.href = "/library/board/" + section + "/detail/" + page + "/" + id;
                } else if (res == 2) {
                    alert('로그인 사용자만 사용할수 있는 서비스 입니다.');
                } else {
                    alert("댓글 등록에 실패했습니다.");
                }
                return false;
            },
            error: function (xhr, status, err) {
                alert("에러:" + err);
            }
        });
        return false;
    }

    function commentDel(section, page, pid, id) {
        $.ajax({
            url: "/library/comment/writerCheck/" + id,
            method: 'get',
            cache: false,
            dataType: 'json',
            success: function (res) {
                if (res.msg == "로그인하지 않으셨습니다.") {
                    alert(res.msg);
                    location.href = "/library";
                } else if (res.msg == "match") {
                    if (!confirm("정말로 글을 삭제하시겠어요?")) return;
                    location.href = "/library/board/" + section + "/comentDelete/" + page + "/" + pid + "/" + id;
                } else {
                    alert(res.msg);
                }
                return false;
            },
            err: function (xhr, status, err) {
                alert(err);
            }
        });
        return false;
    }

    function backPage(section, pg) {
        location.href = "/library/board/" + section + "/list/page/" + pg;
        return false
    }

    function commentEditOn(page, pid, id, comment) {
        $.ajax({
            url: "/library/comment/writerCheck/" + id,
            method: 'get',
            cache: false,
            dataType: 'json',
            success: function (res) {
                if (res.msg == "로그인하지 않으셨습니다.") {
                    alert(res.msg);
                    if (confirm("로그인 하시겠어요?(새창)")) {
                        window.open("/library");
                    }
                } else if (res.msg == "match") {
                    $("#commentEditOn" + id).html("<input type='text' id='commentE'name='comment' value='" + comment + "'>");
                    $("#commentEditButton" + id).html("<button type='submit'>반영</button>");
                } else {
                    alert(res.msg);
                }
                return false;
            },
            err: function (xhr, status, err) {
                alert(err);
            }
        });
        return false;
    }

    function commentSubmit(section, page, pid, id) {
        var serData = $("#commentEdit" + id).serialize();
        $.ajax({
            url: '/library/board/commentEdit',
            method: 'post',
            cache: false,
            data: serData,
            dataType: 'text',
            success: function (res) {
                if (res) {
                    location.href = "/library/board/" + section + "/detail/" + page + "/" + pid;
                } else alert("댓글 수정에 실패했습니다.");
                return false;
            },
            error: function (xhr, status, err) {
                alert("에러:" + err);
            }
        });
        return false;
    }
</script>
<style>
    .comment {
        all: unset;
        display: inline-block;
        float: center;
        width: 300px;
    }

</style>
</html>