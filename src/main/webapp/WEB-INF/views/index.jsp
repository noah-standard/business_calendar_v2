<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@include file="include/inc_head.jsp" %>
</head>
<body class="preload">
<div class="uk-flex uk-flex-center ">
    <div class="uk-flex-stretch">
        <%@include file="include/inc_sub.jsp" %>
        <div class="base-item templates">
            <jsp:include page="${page}.jsp"></jsp:include>
        </div>
    </div>
</div>
</body>
</html>