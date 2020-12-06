<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .uk-form-label {
        font-size: 18px;
    }
</style>
<form method="post" action="${action}" name="bbs_form"
      class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top"
      onsubmit="return chk_form(this);">
    <input type="hidden" name="code" value="${param.code}">
    <input type="hidden" name="idx" value="${param.idx}">
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">제목</label>
        <div class="uk-form-controls">
            <input class="uk-input __ck" title="제목" name="subject" id="form-horizontal-text" type="text"
                   value="${bbsObj.subject}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">작성자</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5 __ck" title="작성자" name="writer" type="text" value="${bbsObj.writer}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">이메일</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5" name="email" type="text" value="${bbsObj.email}">
        </div>
    </div>
    <div class="uk-margin">
        <div class="uk-form-label">공지여부</div>
        <div class="uk-form-controls uk-form-controls-text uk-flex">
            <c:choose>
                <c:when test="${bbsObj.notice == 0}">
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="notice" value="0"
                                                                checked>미허용</label><br>
                    <label><input class="uk-radio" type="radio" name="notice" value="1">허용</label>
                </c:when>
                <c:when test="${bbsObj.notice == 1}">
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="notice" value="0">미허용</label><br>
                    <label><input class="uk-radio" type="radio" name="notice" value="1" checked>허용</label>
                </c:when>
                <c:otherwise>
                    <label class="uk-margin-small-right"><input class="uk-radio" type="radio" name="notice" value="0"
                                                                checked>미허용</label><br>
                    <label><input class="uk-radio" type="radio" name="notice" value="1">허용</label>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <style>
        .ck-editor__editable {
            height: 370px;
        }
    </style>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">내용</label>
        <div class="uk-form-controls">
            <textarea name="content" style="display: none"></textarea>
            <div id="editor">
                ${bbsObj.content}
            </div>
        </div>
    </div>

    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <c:if test="${bbsObj.idx != null}">
            <button class="uk-button uk-button-primary" type="submit" onclick="return confirm('수정하시겠습니까?')">수정</button>
        </c:if>
        <c:if test="${bbsObj.idx == null}">
            <button class="uk-button uk-button-primary" type="submit">등록</button>
        </c:if>
    </p>
</form>
<script src="/static/resources/ckeditor5-build-classic/ckeditor.js"></script>
<script src="/static/resources/ckeditor5-build-classic/translations/ko.js"></script>
<script>
    ClassicEditor
        .create(document.querySelector('#editor'), {
            // The language code is defined in the https://en.wikipedia.org/wiki/ISO_639-1 standard.
            language: 'ko',

        })
        .then(editor => {
            const form = document.bbs_form;
            form.addEventListener('submit', e => {
                const data = document.createTextNode(editor.getData());
                if(data == '' || data.length == 0){
                    alert('내용을 확인해주세요.');
                    editor.editing.view.focus();
                    e.preventDefault();
                }else{
                    document.querySelector('textarea[name=content]').appendChild(data);
                }
            });
        })
        .catch(error => {
            console.error(error);
        });
</script>