<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="templates-item calendar">
    <div class="calendar-item agree_range">
        <div class="agree_range-item agree">
            <span>승인</span>
            <div class="agree-item color"></div>
        </div>
        <div class="agree_range-item disagree">
            <span>미승인</span>
            <div class="disagree-item color"></div>
        </div>
    </div>
</div>
<div class="templates-item object object-btn accept_btn">일정 신청</div>
<div class="templates-item modal_box">
    <div class="modal_box-item bg"></div>
    <div class="modal_box-item form">
        <form class="form-item input" action="/accept/insert" method="post">
            <input class="input-item id" type="hidden" name="id" value="${etc.id}" autocomplete="off">
            <label for="title">사유</label><input class="object object-input" maxlength="4" id="title" type="text"
                                                name="title" autocomplete="off"><br>
            <label for="type">유형</label>
            <select class="object object-input" name="type" id="type">
                <option value="">선택</option>
                <option value="0">반차</option>
                <option value="1">연차</option>
                <option value="2">하계휴가</option>
                <option value="3">출장</option>
                <option value="4">외근</option>
            </select><br>
            <label for="startday">시작</label><input class="object object-input" type="date" id="startday" name="startday"
                                                   autocomplete="off"><br>
            <label for="endday">끝</label><input class="object object-input" type="date" id="endday" name="endday"
                                                autocomplete="off"><br>
            <label for="content">내용</label><textarea class="object object-input" type="text" id="content"
                                                     name="content"></textarea><br>
            <input type="submit" class="object object-btn submit" value="제출">
        </form>
    </div>
</div>