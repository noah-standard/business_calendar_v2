<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class=" uk-margin-medium-left uk-margin-small-top uk-margin-medium-right ">
    <div class="uk-card uk-card-default uk-width-1-1 uk-margin-small-bottom">
        <div class="uk-card-header">
            <div class="uk-grid-small uk-flex-middle" uk-grid>
                <div class="uk-width-expand">
                    <h3 class="uk-card-title uk-margin-remove-bottom">${memberObject.name}
                        <small>(${memberObject.userid})</small></h3>
                    <p class="uk-text-meta uk-margin-remove-top">${memberLevelObject.mem_name}</p>
                </div>
            </div>
        </div>
        <div class="uk-card-body uk-height-large">
            <p><label>전화번호 : </label><span>${memberObject.phone1}</span></p>
            <p><label>휴대폰번호 : </label><span>${memberObject.phone2}</span></p>
            <p><label>주소 : </label><span>(${memberObject.zip}) ${memberObject.addr1} ${memberObject.addr2}</span></p>
        </div>
        <div class="uk-card-footer">
            <a href="../mypage/edit" class="uk-button uk-button-text">수정하기</a>
        </div>
    </div>
</div>