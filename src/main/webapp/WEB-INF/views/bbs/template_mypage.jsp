<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4 class="uk-margin-medium-left uk-margin-remove-top uk-margin-remove-bottom">마이페이지 ${mode}</h4>
<div class="uk-margin-small-top">
    <c:choose>
        <c:when test="${mypage_template == 'skin/mypage_view'}">
            <ul class="uk-tab uk-child-width-expand">
                <li class="uk-active"><a href="#" style="font-size:18px;">마이페이지</a></li>
                <li><a href="../calendar/list" style="font-size:18px;">신청내역</a></li>
            </ul>
        </c:when>
        <c:when test="${mypage_template == 'skin/calendar_list'}">
            <ul class="uk-tab uk-child-width-expand">
                <li><a href="../mypage" style="font-size:18px;">마이페이지</a></li>
                <li class="uk-active"><a href="#" style="font-size:18px;">신청내역</a></li>
            </ul>
        </c:when>
    </c:choose>
</div>
<c:choose>
    <c:when test="${mypage_template == null}">
    </c:when>
    <c:when test="${mypage_template != null}">
        <jsp:include page="${mypage_template}.jsp"></jsp:include>
    </c:when>
</c:choose>
