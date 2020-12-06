<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="templates-item detail">
    <div class="detail-item row">
        <div class="row-item i"><span class="i-item caption">번호</span>${vo.i}
        </div>
        <div class="row-item title"><span class="title-item caption">제목</span>${vo.title}</div>
        <div class="row-item name"><span class="name-item caption">이름</span>
            ${vo.name}
        </div>
        <div class="row-item type">
            <span class="type-item caption">유형</span>
            <c:choose>
                <c:when test="${vo.type == 0}">반차</c:when>
                <c:when test="${vo.type == 1}">연차</c:when>
                <c:when test="${vo.type == 2}">하계휴가</c:when>
                <c:when test="${vo.type == 3}">출장</c:when>
                <c:when test="${vo.type == 4}">외근</c:when>
            </c:choose>
        </div>
    </div>
    <div class="detail-item row">
        <div class="row-item startday"><span class="startday-item caption">시작</span>${vo.startday}
        </div>
        <div class="row-item endday"><span class="endday-item caption">끝</span>${vo.endday}
        </div>
    </div>
    <div class="detail-item row">
        <div class="row-item content"><span class="content-item caption">내용</span>${vo.content}</div>
    </div>
    <c:if test="${session != 'admin'}">
    <button class="detail-item updatebtn object object-btn">수정</button>
    </c:if>
</div>