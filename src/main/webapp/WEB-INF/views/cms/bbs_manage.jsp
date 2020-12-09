<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
    function getTitleMenu(code) {
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                console.log(xhr.responseText);
                if (xhr.responseText != "") {
                    document.querySelector("#path_" + code).innerHTML += "게시판 관리 > " + xhr.responseText;
                } else {
                    document.querySelector("#path_" + code).innerHTML += "지정된 위치가 없습니다.";
                }

            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', "/cms/ajax/title?code=" + code);
        xhr.send(); // 요청 전송
    }

    function getBBSCnt(code) {
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                console.log(xhr.responseText);
                document.querySelector("#bbs_cnt_" + code).innerHTML += "" + xhr.responseText + " 개";
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', "/cms/ajax/bbs_cnt?code=" + code);
        xhr.send(); // 요청 전송
    }
</script>
<h3 class="uk-heading-divider uk-padding-small">게시판관리</h3>
<div class="uk-padding">
    <div class="uk-panel uk-padding-large uk-flex uk-flex-between uk-text-center uk-card uk-card-default">
        <div class=" uk-flex">
            <div>
                <div class="uk-heading uk-margin uk-margin-small"><span class="uk-text-large">총 게시판 수</span><br><span
                        class="uk-text-center uk-text-middle">${totalBBS} 개</span></div>
                <a href="./bbs_manage/write" class="uk-button uk-button-primary">게시판 등록하기</a>
            </div>
            <div class="uk-column-1-2 uk-margin-medium-left">
                <ul class="uk-list">
                    <c:forEach items="${BBSManageGroupList}" var="item">
                        <c:if test="${item.BBS_TYPE == 'notice'}">
                            <c:set var="bbs_title" value="공지사항"></c:set>
                        </c:if>
                        <c:if test="${item.BBS_TYPE == 'reply'}">
                            <c:set var="bbs_title" value="답변형"></c:set>
                        </c:if>
                        <c:if test="${item.BBS_TYPE == 'gallery'}">
                            <c:set var="bbs_title" value="갤러리"></c:set>
                        </c:if>
                        <li>${bbs_title}&nbsp <span class="uk-badge">${item.CNT}</span></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="uk-float-right">
            <div class="uk-heading uk-margin uk-margin-small">
                <span class="uk-text-large">총 게시물 수</span>
                <br>
                <span class="uk-text-center uk-text-middle">${BBSCnt} 개</span>
            </div>
        </div>
    </div>
    <table class="uk-table uk-table-hover uk-table-divider uk-table-middle uk-text-center">
        <thead>
        <tr>
            <td>번호</td>
            <td>코드</td>
            <td>경로</td>
            <td>게시판명</td>
            <td>게시물수</td>
            <td>등록일</td>
            <td>관리</td>
        </tr>
        </thead>
        <tbody>
        <c:set var="listNo" value="${totalBBS}"></c:set>
        <c:forEach items="${BBSManageList}" var="item">
            <tr>
                <td>${listNo}</td>
                <td>${item.code}</td>
                <td id="path_${item.code}">
                    <script>
                        getTitleMenu("${item.code}");
                    </script>
                </td>
                <td>${item.name}</td>
                <td id="bbs_cnt_${item.code}">
                    <script>
                        getBBSCnt("${item.code}");
                    </script>
                </td>
                <td><fmt:parseDate var="regDateStr" value="${item.reg_date}"
                                   pattern="yyyy-MM-dd HH:mm:ss"/><fmt:formatDate value="${regDateStr}"
                                                                                  pattern="yyyy-MM-dd"/></td>
                <td><a href="./bbs_manage/edit?idx=${item.idx}" class="uk-margin-small-left"><span
                        class="uk-icon-button " uk-icon="file-edit"></span></a><a
                        href="./bbs_manage/delete.do?idx=${item.idx}" class="uk-margin-small-left"><span
                        class="uk-icon-button" uk-icon="trash"></span></a></td>
            </tr>
            <c:set var="listNo" value="${listNo-1}"></c:set>
        </c:forEach>
        </tbody>
    </table>
</div>