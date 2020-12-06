<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="templates-item check">
    <div class="check-item caption">${etc.nickname} 님 환영합니다</div>
    <table class="check-item checkbox">
        <tr class="checkbox-item header">
            <th class="header-item allcheck">전체선택</th>
            <th class="header-item code">근무코드</th>&nbsp;
            <th class="header-item type">근무타입</th>&nbsp;
            <th class="header-item startday">시작날짜</th>&nbsp;
            <th class="header-item endday">마감일짜</th>&nbsp;
            <th class="header-item agree">승인</th>
            <c:if test="${session == 'admin'}">
                <th class="header-item admin">이름</th>
            </c:if>
        </tr>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach items="${list}" var="item">
                    <tr class="checkbox-item session">
                        <td class="session-item check">
                            <div class="object object-checkbox"><i class="fas fa-check"></i></div>
                        </td>
                        <td class="session-item i">${item.i}</td>&nbsp;
                        <td class="session-item type"><c:choose>
                            <c:when test="${item.type == 0}">반차</c:when>
                            <c:when test="${item.type == 1}">연차</c:when>
                            <c:when test="${item.type == 2}">하계휴가</c:when>
                            <c:when test="${item.type == 3}">출장</c:when>
                            <c:when test="${item.type == 4}">외근</c:when>
                        </c:choose></td>&nbsp;
                        <td class="session-item startday">${item.startday}</td>&nbsp;
                        <td class="session-item endday">${item.endday}</td>
                        <td class="session-item agree"><c:choose>
                            <c:when test="${item.agree_flag == 0}">미승인</c:when>
                            <c:when test="${item.agree_flag == 1}">승인</c:when>
                        </c:choose></td>&nbsp;
                        <c:if test="${session == 'admin'}">
                            <td class="header-item admin">${item.nickname}</td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="checkbox-item empty">비어있습니다</div>
            </c:otherwise>
        </c:choose>
    </table>
    <div class="check-item page">
        <c:forEach var="page" begin="1" end="${maxCountList}">
            <span class="page-item index">${page}</span>
        </c:forEach>
    </div>
    <c:choose>
        <c:when test="${session == 'admin'}">
            <button class="check-item object object-btn agree">승인</button>
            <button class="check-item object object-btn disagree">미승인</button>
        </c:when>
        <c:otherwise>
            <button class="check-item object object-btn delete">삭제</button>
        </c:otherwise>
    </c:choose>

</div>