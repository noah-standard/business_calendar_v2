<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4 class="uk-margin-medium-left uk-margin-remove-top uk-margin-remove-bottom">${mst_bbs.name} ${bbs_mode}</h4>
<c:choose>
    <c:when test="${bbs_template == null}">
    </c:when>
    <c:when test="${bbs_template != null}">
        <jsp:include page="${bbs_template}.jsp"></jsp:include>
    </c:when>
</c:choose>
