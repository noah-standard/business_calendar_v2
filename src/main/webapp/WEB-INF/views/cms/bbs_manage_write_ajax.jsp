<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="uk-margin uk-grid-small uk-child-width-auto uk-grid">
    <label><input class="uk-checkbox" type="checkbox" name="allow_${allow}" value="nobody" onclick="check_chk()" checked> 관리자</label>
    <label><input class="uk-checkbox" id="anybody" type="checkbox" name="allow_${allow}" value="anybody" onclick="check_chk()" checked> 누구나</label>
    <c:forEach items="${memList}" var="item">
    <label><input class="uk-checkbox member" type="checkbox" name="allow_${allow}" value="${item.mem_level}" checked disabled> ${item.mem_name}</label>
    </c:forEach>
</div>