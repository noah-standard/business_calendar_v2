<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="uk-heading-divider uk-padding-small">휴일관리</h3>
<h4 class="uk-margin-medium-left uk-margin-remove-top uk-margin-remove-bottom">휴일 ${mode}</h4>
<c:choose>
    <c:when test="${holiday_template == null}">
    </c:when>
    <c:when test="${holiday_template != null}">
        <jsp:include page="${holiday_template}.jsp"></jsp:include>
    </c:when>
</c:choose>
