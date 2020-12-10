<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="uk-margin-small-top uk-margin-small-bottom uk-margin-medium-left uk-flex">
    <form action="javascript:void(0)" id="ajax_search" onsubmit="search_order();">
        <input type="hidden" name="search_order" value="all">
        <input type="hidden" name="curPage" value="${param.curPage}">
        <input type="hidden" name="list_order" value="${param.list_order}">
        <input type="hidden" name="list_scale" value="${param.list_scale}">
        <input type="hidden" name="keyword">
    </form>
    <span class="uk-margin-small-left" uk-search-icon></span>
    <input class="uk-search-input uk-margin-small-left" type="search" id="keyword" placeholder="" onkeyup="search_order()">
</div>
<div class="uk-flex uk-width-1-1 uk-flex-between uk-flex-middle uk-margin-small-top uk-margin-small-bottom">
    <div class="uk-margin-medium-left ">
        총 ${listCount}개의 회원이 있습니다.
    </div>
</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-width-small uk-text-center">번호</th>
            <th class="uk-table-expand uk-text-center">직책</th>
            <th class="uk-table-expand uk-text-center">아이디</th>
            <th class="uk-table-expand uk-text-center">이름</th>
            <th class="uk-table-expand uk-text-center">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${memberList}" var="item" varStatus="status">
            <tr>
                <td class="uk-width-small uk-text-center">${item.rn}</td>
                <td class="uk-width-small uk-text-center">${item.mem_name}</td>
                <td class="uk-width-small uk-text-center">${item.userid}</td>
                <td class="uk-width-small uk-text-center" id="name_${item.idx}">${item.name}</td>
                <td class="uk-width-small uk-text-center"><a href="javascript:void(0)" onclick="select_member('${item.idx}')" class="uk-button uk-button-small uk-button-primary">선택</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="uk-pagination uk-flex-center uk-flex-middle" uk-margin>
        <li><a href="javascript:list('${pager.prevPage}')"><span uk-pagination-previous></span></a></li>
        <c:forEach var="num" begin="${pager.blockBegin}" end="${pager.blockEnd}">
            <li><a href="javascript:list('${num}')">${num}</a></li>
        </c:forEach>
        <li><a href="javascript:list('${pager.nextPage}')"><span uk-pagination-next></span></a></li>
    </ul>
</div>
