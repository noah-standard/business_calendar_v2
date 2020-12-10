<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms" />
<header class="uk-flex uk-flex-between ">
    <a href="/" class="uk-text-default" style="text-decoration:none;cursor: pointer" ><h2>VacationWare</h2></a>
    <nav class="uk-navbar-container" uk-navbar>
        <div class="uk-navbar-left">
            <ul class="uk-navbar-nav">
                <c:choose>
                    <c:when test="${loginCheck == null}">
                        <li class="">
                            <!-- This is an anchor toggling the modal -->
                            <a href="#modal-example" uk-toggle>로그인</a>
                        </li>
                    </c:when>
                    <c:when test="${loginCheck != null}">
                        <li class="">
                            <a href="/logout.do" uk-toggle>로그아웃</a>
                        </li>
                        <li>
                            <a href="/mypage">마이페이지</a>
                        </li>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
                <c:forEach items="${userMenuList}" var="item">
                    <li>
                        <a href="${item.link}">${item.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </nav>
</header>
<!-- This is the login modal -->
<div id="modal-example" uk-modal>
    <div class="uk-modal-dialog uk-modal-body">
        <h2 class="uk-modal-title">로그인 정보 입력</h2>
        <form action="/" method="post">
            <fieldset class="uk-fieldset">

                <div class="uk-margin">
                    <input class="uk-input" type="text" name="userid" placeholder="id">
                </div>

                <div class="uk-margin">
                    <input class="uk-input" type="password" name="password" placeholder="password">
                </div>

            </fieldset>
            <p class="uk-text-right">
                <button class="uk-button uk-button-default uk-modal-close" type="button">취소</button>
                <button class="uk-button uk-button-primary" type="submit">로그인</button>
            </p>
        </form>
    </div>
</div>