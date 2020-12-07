<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms" />
<c:if test="${menu_cnt == 1}">
    <c:forEach items="${menuList}" var="item">
        <li><a href='${path}${item.link}' class="uk-button uk-button-default" style="opacity: 0.8"><span style="font-size:20px;color:#fff;opacity: 0.8;" class="uk-text-bold">${item.name}</span></a></li>
    </c:forEach>
</c:if>
<c:if test="${menu_cnt != 1}">
    <c:forEach items="${menuList}" var="item" varStatus="status">
        <c:if test="${status.count == 1}">
        <li class='uk-parent'>
            <a href='#' class="uk-button uk-button-default" style="opacity: 0.8">
                <span style="font-size:20px;color:#fff;opacity: 0.8;" class="uk-text-bold">${item.name}</span></a>
            <ul class='uk-nav-sub uk-card uk-card-primary'  style="background-color: #0f6ecd;">
        </c:if>
        <c:if test="${status.count > 1}">
            <li><a href='${path}${item.link}' style="opacity: 0.8;">${item.name}</a></li>
        </c:if>
        <c:if test="${menu_cnt == status.count}">
            </ul>
        </li>
        </c:if>
    </c:forEach>
</c:if>