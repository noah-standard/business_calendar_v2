<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="./include/inc_head.jsp" %>
</head>
<body>
<section class="top-banner">
    <div class="uk-margin-auto uk-width-1-2" style="margin:auto 0;">

    </div>
    <%/*
    <div class="top-banner-overlay">
        <h1>Big Title Here</h1>
        <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    </div>
    */ %>
</section>
<nav class="top-nav">
    <div class="menu-wrapper">
        <ul class="menu">
            <li>
                <a href="">home</a>
            </li>
            <li>
                <a href="">about</a>
                <ul class="sub-menu">
                    <li>
                        <a href="">— company</a>
                    </li>
                    <li>
                        <a href="">— people</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="">projects</a>
            </li>
            <li>
                <a href="">clients</a>
            </li>
            <li>
                <a href="">contact</a>
            </li>
        </ul>
<%--        <button class="menu-close" aria-label="close menu">✕</button>--%>
    </div>
    <div class="fixed-menu">
        <%//<h2 class="logo">Calware</h2>%>
        <ul class="uk-iconnav uk-iconnav-vertical">
            <li><a href="#" uk-icon="icon: plus;ratio:1.3"></a></li>
            <li><a href="#" uk-icon="icon: file-edit;ratio:1.3"></a></li>
            <li><a href="#" uk-icon="icon: copy;ratio:1.3"></a></li>
            <li><a href="#" uk-icon="icon: trash;ratio:1.3"></a></li>
        </ul>
        <button class="menu-open" aria-label="open menu">☰</button>
    </div>
</nav>
</body>
</html>
