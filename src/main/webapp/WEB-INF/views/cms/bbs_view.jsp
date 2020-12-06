<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class=" uk-margin-medium-left uk-margin-small-top uk-margin-medium-right ">
    <div class="uk-heading-divider uk-card-default uk-padding">
        <div class="uk-flex uk-flex-between">
            <span class="uk-text-large">${bbsObj.subject}</span>
            <span>등록일: ${bbsObj.reg_date}</span>
        </div>
        <div>
            <span>조회수: ${bbsObj.read_cnt}</span>
            <br>
            <span>작성자: ${bbsObj.writer}</span>
            <br>
            <span>이메일: ${bbsObj.email}</span>
        </div>
    </div>
    <div class="uk-padding" style="height:500px;">
        <div class="">${bbsObj.content}</div>
    </div>
    <div class="uk-float-left">
        <a href="../bbs/edit?idx=${bbsObj.idx}&code=${mst_bbs.code}" class="uk-button uk-button-default">수정</a>
        <a href="javascript:void(0)" onclick="confirm_data('./delete.do?idx=${item.idx}&code=${mst_bbs.code}','삭제')" class="uk-button uk-button-default">삭제</a>
    </div>
    <div class="uk-float-right">
        <a href="javascript:history.back()" class="uk-button uk-button-default">취소</a>
        <a href="../bbs/write?code=${mst_bbs.code}" class="uk-button uk-button-primary">등록</a>
    </div>
</div>