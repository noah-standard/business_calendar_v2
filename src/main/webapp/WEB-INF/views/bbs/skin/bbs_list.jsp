<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function list(page){
        location.href = './bbs?curPage='+page
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
        location.href = './bbs?curPage=${curPage}'
            +'&search_order=${search_order}'
            +'&keyword=${keyword}'
            +'&code=${mst_bbs.code}'
            +'&list_order='+order_value
            +'&list_scale=${param.list_scale}';
    }

    function list_scale(){
        let list_scale = document.querySelector("#list_scale");
        let scale_value = list_scale.options[list_scale.selectedIndex].value;
        location.href = './bbs?curPage=${curPage}'
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
        <option value="subject">제목</option>
        <option value="writer">작성자</option>
        <option value="content">내용</option>
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
</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-width-small uk-text-center">번호</th>
            <th class="uk-table-expand uk-text-center">제목</th>
            <th class="uk-table-shrink uk-text-center">첨부파일</th>
            <th class="uk-table-shrink uk-text-center">작성자</th>
            <th class="uk-table-shrink uk-text-center">등록일</th>
            <th class="uk-table-shrink uk-text-center">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bbsList}" var="item">
            <tr>
                <td class="uk-width-small uk-text-center">${item.rn}</td>
                <td class="uk-text-bold"><a href="./view?idx=${item.idx}">${item.subject}</a></td>
                <td class="uk-width-small uk-text-center">첨부파일</td>
                <td class="uk-width-small uk-text-center">${item.writer}</td>
                <td class="uk-width-small uk-text-center"><fmt:parseDate var="regDateStr" value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${regDateStr}" pattern="yyyy-MM-dd" /></td>
                <td class="uk-width-small uk-text-center">${item.read_cnt}</td>
            </tr>
            <c:set var="listNo" value="${listNo-1}"></c:set>
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
