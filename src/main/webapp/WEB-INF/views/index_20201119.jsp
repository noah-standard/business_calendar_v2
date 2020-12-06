<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="j_spring_security_check" var="loginUrl"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle} || Vacation Accepting Program</title>
    <link rel="stylesheet" href="/static/resources/stylesheets/common.css?v=<%=Math.random()%>">
    <link rel="stylesheet" href="/static/resources/stylesheets/default.css?v=<%=Math.random()%>">
    <link rel="stylesheet" href="/static/resources/stylesheets/object/object.css?v=<%=Math.random()%>">
    <link rel="stylesheet" href="/static/resources/stylesheets/templates/${templates}.css?v=<%=Math.random()%>">
    <script src="https://kit.fontawesome.com/ea53519ccc.js" crossorigin="anonymous"></script>
    <script src="/static/resources/js/common.js?v=<%=Math.random()%>"></script>
    <c:if test="${pageTitle == 'accept'}">
        <link rel="stylesheet" href="/static/resources/stylesheets/fullcalendar/core/main.min.css">
        <link rel="stylesheet" href="/static/resources/stylesheets/fullcalendar/daygrid/main.min.css">
        <script src="/static/resources/js/fullcalendar/locale/ko.js"></script>
        <script src="/static/resources/js/fullcalendar/core/main.min.js"></script>
        <script src="/static/resources/js/fullcalendar/daygrid/main.min.js"></script>
    </c:if>
</head>
<body class="preload">
<div class="base">
    <header class="base-item logo">
        <div class="logo-item caption"><a href="/">테스트</a></div>
        <div class="logo-item description">일정 프로그램</div>
    </header>
    <nav class="base-item nav">
        <div class="nav-item layout">
            <c:choose>
                <c:when test="${session != null}">
                    <div class="object object-btn"><a href="/logout">로그아웃</a></div>
                    <div class="object object-btn"><a href="/accept">일정</a></div>
                    <div class="object object-btn"><a href="/check?page=1">리스트</a></div>
                </c:when>
                <c:otherwise>
                    <div class="object object-btn btn-login">로그인</div>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
    <div class="base-item login">
        <div class="login-item bg"></div>
        <div class="login-item modal_box">
            <div class="modal_box-item caption">로그인</div>
            <form class="modal_box-item form"  method="post" action="${loginUrl}">
                <input class="object object-input" type="text" placeholder="아이디" name="id" autocomplete="off"><br>
                <input class="object object-input" type="password" placeholder="비밀번호" name="password" autocomplete="off"><br>
                <c:if test="${param.error != null}">
                    ${error_message}
                </c:if>
                <input class="object object-btn" type="submit" value="로그인">
            </form>
        </div>
    </div>
    <div class="base-item templates">
        <jsp:include page="${page}.jsp"></jsp:include>
    </div>
    <footer class="base-item footer">
        <div class="footer-item copyright">저작권은<br>노아&copy에게 있습니다.</div>
    </footer>
</div>
</body>
</html>