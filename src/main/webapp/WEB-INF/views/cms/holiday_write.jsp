<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .uk-form-label {
        font-size: 18px;
    }
</style>
<form method="post" name="bbs_form" class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top" onsubmit="return chk_form(this);">
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">휴일명</label>
        <div class="uk-form-controls">
            <input class="uk-input __ck" title="휴일명" name="locdate_name" id="form-horizontal-text" type="text" value="${holidayObject.locdate_name}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">일자</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5 __ck" title="일자" name="locdate" type="date" value="${holidayObject.locdate}">
        </div>
    </div>
    <div class="uk-margin">
        <div class="uk-form-label">휴일여부</div>
        <div class="uk-form-controls uk-form-controls-text uk-flex">
            <c:choose>
                <c:when test="${holidayObject.holi_flag == 'Y'}">
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="holi_flag" value="Y" checked>허용</label>
                    <label><input class="uk-radio" type="radio" name="holi_flag" value="N">미허용</label><br>
                </c:when>
                <c:when test="${holidayObject.holi_flag == 'N'}">
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="holi_flag" value="Y">허용</label>
                    <label><input class="uk-radio" type="radio" name="holi_flag" value="N"  checked>미허용</label><br>
                </c:when>
                <c:otherwise>
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="holi_flag" value="Y" checked>허용</label>
                    <label><input class="uk-radio" type="radio" name="holi_flag" value="N" >미허용</label><br>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <c:if test="${holidayObject.idx == null}">
            <button class="uk-button uk-button-primary" type="submit">등록</button>
        </c:if>
        <c:if test="${holidayObject.idx != null}">
            <button class="uk-button uk-button-primary" onclick="return confirm('휴일정보를 수정하시겠습니까?')" type="submit">수정</button>
        </c:if>
    </p>
</form>