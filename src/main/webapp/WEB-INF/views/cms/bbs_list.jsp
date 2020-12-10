<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>

    function list(page){
        location.href = './list?curPage='+page
        +'&search_order=${search_order}'
        +'&keyword=${keyword}'
        +'&code=${mst_bbs.code}'
        +'&list_order=${param.list_order}'
        +'&list_scale=${param.list_scale}';
    }

    function search_order(){
        let search_order = document.querySelector("#search_order");
        let order_value = search_order.options[search_order.selectedIndex].value;
        document.querySelector("input[name=search_order]").value = order_value;
    }

    function list_order(){
        let list_order = document.querySelector("#list_order");
        let order_value = list_order.options[list_order.selectedIndex].value;
        location.href = './list?curPage=${curPage}'
            +'&search_order=${search_order}'
            +'&keyword=${keyword}'
            +'&code=${mst_bbs.code}'
            +'&list_order='+order_value
            +'&list_scale=${param.list_scale}';
    }

    function list_scale(){
        let list_scale = document.querySelector("#list_scale");
        let scale_value = list_scale.options[list_scale.selectedIndex].value;
        location.href = './list?curPage=${curPage}'
            +'&search_order=${search_order}'
            +'&keyword=${keyword}'
            +'&code=${mst_bbs.code}'
            +'&list_order=${param.list_order}'
            +'&list_scale='+scale_value;
    }

    document.addEventListener("DOMContentLoaded",function(){
        search_order();
    });
</script>
<div class="uk-margin-small-top uk-margin-small-bottom uk-margin-medium-left uk-flex">
    <select id="search_order" class="uk-select uk-width-1-6" onchange="search_order()">
        <option value="all" selected>전체검색</option>
        <option value="subject" ${param.search_order eq 'subject' ? "selected" : ""}>제목</option>
        <option value="writer" ${param.search_order eq 'writer' ? "selected" : ""}>작성자</option>
        <option value="content" ${param.search_order eq 'content' ? "selected" : ""}>내용</option>
    </select>
    <form class="uk-search uk-search-default">
        <input type="hidden" name="search_order">
        <input type="hidden" name="list_order" value="${param.list_order}">
        <input type="hidden" name="list_scale" value="${param.list_scale}">
        <input type="hidden" name="code" value="${mst_bbs.code}">
        <span class="uk-margin-small-left" uk-search-icon></span>
        <input class="uk-search-input uk-margin-small-left" type="search" name="keyword" placeholder="">
    </form>
</div>
<div class="uk-flex uk-width-1-1 uk-flex-between uk-flex-middle uk-margin-small-top uk-margin-small-bottom">
    <div class="uk-margin-medium-left ">
    총 ${bbs_count}개의 게시물이 있습니다.
    </div>
    <div class="uk-text-right uk-flex  uk-width-1-4 uk-margin-medium-right">
        <select class="uk-select" id="list_order" onchange="list_order()">
            <option value="0" ${param.list_order eq 0 ? 'selected' : list_order eq '' ? 'selected' : ''}>최근등록순</option>
            <option value="1" ${param.list_order eq 1 ? 'selected' : ''}>과거등록순</option>
        </select>
        <select class="uk-select uk-margin-small-left" id="list_scale" onchange="list_scale()">
            <option value="10" ${param.list_scale eq '10' ? 'selected' : list_scale eq '' ? 'selected' : '' }>10개씩</option>
            <option value="20" ${param.list_scale eq '20' ? 'selected' : ''}>20개씩</option>
            <option value="50" ${param.list_scale eq '50' ? 'selected' : ''}>50개씩</option>
        </select>
    </div>
</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-width-small uk-text-center">번호</th>
            <th class="uk-table-expand uk-text-center">제목</th>
<%--            <th class="uk-table-small uk-text-center">첨부파일</th>--%>
            <th class="uk-table-small uk-text-center">작성자</th>
            <th class="uk-table-small uk-text-center">등록일</th>
            <th class="uk-table-small uk-text-center">조회수</th>
            <th class="uk-table-small uk-text-center">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bbsList}" var="item">
            <tr>
                <td class="uk-width-small uk-text-center">${item.rn}</td>
                <td class="uk-text-bold"><a href="./view?idx=${item.idx}&code=${mst_bbs.code}">${item.subject}</a></td>
<%--                <td class="uk-width-small uk-text-center">첨부파일</td>--%>
                <td class="uk-width-small uk-text-center">${item.writer}</td>
                <td class="uk-width-small uk-text-center"><fmt:parseDate var="regDateStr" value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${regDateStr}" pattern="yyyy-MM-dd" /></td>
                <td class="uk-width-small uk-text-center">${item.read_cnt}</td>
                <td class="uk-width-small uk-text-center"><a href="./edit?idx=${item.idx}&code=${mst_bbs.code}" class="uk-margin-small-left"><span class="uk-icon-button " uk-icon="file-edit"></span></a><a
                        href="javascript:void(0)" onclick="confirm_data('./delete.do?idx=${item.idx}&code=${mst_bbs.code}','삭제')" class="uk-margin-small-left"><span class="uk-icon-button" uk-icon="trash"></span></a></td>
            </tr>
            <c:set var="listNo" value="${listNo-1}"></c:set>
        </c:forEach>
        </tbody>
    </table>
    <div class=" uk-text-right">
        <a href="./write?code=${mst_bbs.code}" class="uk-button uk-button-primary">등록</a>
    </div>
    <ul class="uk-pagination uk-flex-center uk-flex-middle" uk-margin>
        <li><a href="javascript:list('${pager.prevPage}')"><span uk-pagination-previous></span></a></li>
        <c:forEach var="num" begin="${pager.blockBegin}" end="${pager.blockEnd}">
            <li><a href="javascript:list('${num}')">${num}</a></li>
        </c:forEach>
        <li><a href="javascript:list('${pager.nextPage}')"><span uk-pagination-next></span></a></li>
    </ul>
</div>
