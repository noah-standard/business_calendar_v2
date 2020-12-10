<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .uk-form-label {
        font-size: 18px;
    }
</style>
<form method="post" action="../mypage/edit.do" class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top">
    <input type="hidden" name="idx" value="${memberObject.idx}">
    <div class="uk-margin">
        <label class="uk-form-label" for="userid">아이디</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5" id="userid" name="userid" type="text" value="${memberObject.userid}" readonly>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="name">이름</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5" id="name" name="name" type="text" value="${memberObject.name}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="name">비밀번호</label>
        <div class="uk-form-controls uk-flex">
            <input class="uk-input uk-width-1-5 uk-margin-small-right"  id="password" name="password" type="password" value="">
            <div class="uk-alert-success uk-margin-remove uk-width-1-2  uk-text-middle" style="height:40px;" uk-alert>
                <a class="uk-alert-close" uk-close></a>
                <p>비밀번호 변경시 입력하세요</p>
            </div>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="name">직급</label>
        <div class="uk-form-controls">
            <input type="hidden" name="mem_level" value="${memberObject.mem_level}">
            <span>${memberLevelObject.mem_name}</span>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="phone1">전화번호</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5" maxlength="13" onkeyup="inputPhoneNumber(this);" id="phone1" name="phone1" type="text" value="${memberObject.phone1}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="phone2">휴대폰</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5" maxlength="13" onkeyup="inputPhoneNumber(this);" id="phone2" name="phone2" type="text" value="${memberObject.phone2}">
        </div>
    </div>

    <div class="uk-margin">
        <label class="uk-form-label" for="zip">주소</label>
        <div class="uk-form-controls">
        <input class="uk-input uk-width-1-6" name="zip" id="zip" type="text" maxlength="5" onclick="javascript:sample3_execDaumPostcode('zip','add1','post_ifr');" readonly="" value="${memberObject.zip}" class="__ck form-control" title="우편번호 검색버튼을 눌러 주소를 입력해주세요.">
        <a href="javascript:sample3_execDaumPostcode('zip','add1','post_ifr');" class="uk-button uk-button-default">우편번호검색</a>
            <p><input class="uk-input uk-width-1-1" name="addr1" id="add1" type="text" value="${memberObject.addr1}" class="__ck form-control" title="상세주소를 입력해주세요."></p>
            <div id="post_ifr" style="display:none;border:1px solid;width:400px;height:500px;margin:5px 0;position:relative">
                <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:-1px;top:-20px;z-index:1" onclick="foldDaumPostcode('post_ifr')" alt="닫기">
            </div>
            <p><input class="uk-input uk-width-1-1" name="addr2" type="text" value="${memberObject.addr2}" class="__ck form-control" title="상세주소를 입력해주세요."></p>
        </div>
    </div>

    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <c:choose>
            <c:when test="${memberObject.idx == null}">
                <button class="uk-button uk-button-primary" type="submit">등록</button>
            </c:when>
            <c:when test="${memberObject.idx != ''}">
                <button class="uk-button uk-button-primary" type="submit">수정</button>
            </c:when>
        </c:choose>
    </p>
</form>
