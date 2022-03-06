<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서관 가온누리</title>
    <link href="/css/styles.css" rel="stylesheet"/>
</head>
<body>
<body class="d-flex flex-column">
<div class="header">
    <main class="flex-shrink-0">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a href="/library"><img id="headLogo" src="/img/gaon.png" height="60px"></a>
                <a class="navbar-brand" href="index.html"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/library">홈</a></li>
                        <li class="nav-item"><a class="nav-link" href="contact.html">안내</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">도서</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="/library/bookSearch">도서검색</a></li>
                                <li><a class="dropdown-item" href="/library/hotbook">인기도서</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">서비스</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="/library/board/free">희망도서 신청</a></li>
                                <li><a class="dropdown-item" href="/library/board/free">책배달 서비스 신청</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="/library/board/FAQ">FAQ</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownBlog" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">게시판</a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownBlog">
                                <li><a class="dropdown-item" href="/library/board/notice">공지사항</a></li>
                                <li><a class="dropdown-item" href="/library/board/free">자유게시판</a></li>
                                <li><a class="dropdown-item" href="/library/board/photo">사진게시판</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="/library/myShelf">내 서재</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Bootstrap core JS-->
        <!-- Core theme JS-->
        <script src="/js/scripts.js"></script>
    </main>
</div>
</body>
</html>