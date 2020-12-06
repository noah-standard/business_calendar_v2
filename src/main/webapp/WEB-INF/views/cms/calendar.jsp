<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="uk-heading-divider uk-padding-small">일정통합관리</h3>
<h4 class="uk-margin-medium-left uk-margin-remove-top uk-margin-remove-bottom">일정 ${mode}</h4>
<c:choose>
    <c:when test="${calendar_template == null}">
    </c:when>
    <c:when test="${calendar_template != null}">
        <jsp:include page="${calendar_template}.jsp"></jsp:include>
    </c:when>
</c:choose>
