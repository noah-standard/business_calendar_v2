<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class=" uk-margin-medium-left uk-margin-small-top uk-margin-medium-right ">
<div class="uk-heading-divider uk-card-default uk-padding">
    <div class="uk-flex uk-flex-between">
        <h3>${BBSObject.subject}</h3>
        <span>등록일: ${BBSObject.reg_date}</span>
    </div>
    <div>
        <span>작성자: ${BBSObject.writer}</span>
        <br>
        <span>이메일: ${BBSObject.email}</span>
    </div>
</div>
<div class="uk-padding" style="height:500px;">
    <div class="">${BBSObject.content}</div>
</div>
    <div class="uk-float-right">
        <a href="javascript:history.back()" class="uk-button uk-button-default">뒤로가기</a>
    </div>
</div>