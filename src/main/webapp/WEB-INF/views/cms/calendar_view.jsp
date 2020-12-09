<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class=" uk-margin-medium-left uk-margin-small-top uk-margin-medium-right ">
<div class="uk-heading-divider uk-card-default uk-padding">
    <div class="uk-flex uk-flex-between">
        <span class="uk-text-large">${calendarObject.type}</span>
        <span>등록일: ${calendarObject.reg_date}</span>
    </div>
    <div>
        <span>일자: ${calendarObject.s_date}</span>
    </div>
</div>
<div class="uk-padding" style="height:500px;">
    <div class="">${calendarObject.content}</div>
</div>
    <div class="uk-float-left">
        <a href="./edit?idx=${calendarObject.idx}" class="uk-button uk-button-default">수정</a>
        <a href="javascript:void(0)" onclick="confirm_data('./delete.do?idx=${calendarObject.idx}','삭제')" class="uk-button uk-button-default">삭제</a>
    </div>
    <div class="uk-float-right">
        <a href="javascript:history.back()" class="uk-button uk-button-default">취소</a>
        <a href="./write" class="uk-button uk-button-primary">등록</a>
    </div>
</div>