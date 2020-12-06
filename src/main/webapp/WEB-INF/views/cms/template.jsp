<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="./include/inc_head.jsp" %>
</head>
<body>
<%@include file="./include/inc_sub.jsp"%>
<section class="top-banner">
    <div class="uk-width">
        <%@include file="./include/inc_header.jsp"%>
        <c:choose>
            <c:when test="${page == null}">
            </c:when>
            <c:when test="${page != null}">
                <jsp:include page="${page}.jsp"></jsp:include>
            </c:when>
        </c:choose>
    </div>
</section>
</body>
</html>
