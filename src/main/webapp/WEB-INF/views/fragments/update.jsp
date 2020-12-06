<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="templates-item update">
    <form action="/update" method="post">
        <div class="update-item row">
            <div class="row-item i"><span class="i-item caption">번호</span>${vo.i}
                <input type="hidden" name="i" value="${vo.i}">
                <input type="hidden" name="agree_flag" value="0">
            </div>
            <div class="row-item title"><span class="i-item caption">제목</span>
                <input class="object object-input" maxlength="4" name="title" type="text" value="${vo.title}"></div>
            <div class="row-item name"><span class="name-item caption">이름</span>
                ${vo.name}
            </div>
            <div class="row-item type">
                <span class="type-item caption">유형</span>
                <select name="type" class="object object-input">
                    <option value="0">반차</option>
                    <option value="1">연차</option>
                    <option value="2">하계휴가</option>
                    <option value="3">출장</option>
                    <option value="4">외근</option>
                </select>
            </div>
        </div>
        <div class="update-item row">
            <div class="row-item startday"><span class="startday-item caption">시작</span>
                <input class="object object-input" type="date" name="startday" value="${vo.startday}">
            </div>
            <div class="row-item endday"><span class="endday-item caption">끝</span>
                <input class="object object-input" type="date" name="endday" value="${vo.endday}">
            </div>
        </div>
        <div class="update-item row">
            <div class="row-item content"><span class="content-item caption">내용</span>
                <textarea class="object object-input" name="content" cols="95" rows="23">${vo.content}</textarea></div>
        </div>
        <input type="submit" class="update-item updatebtn object object-btn" value="수정하기"
               onclick="confirm('수정하시겠습니까?\ncf. 승인 일시 미승인으로 바뀝니다.')">
    </form>
</div>