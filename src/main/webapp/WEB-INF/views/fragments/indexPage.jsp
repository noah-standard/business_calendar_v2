<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='../static/resources/stylesheets/fullcalendar/daygrid/main.min.css' rel='stylesheet'/>
<script src='../static/resources/js/fullcalendar/daygrid/main.min.js'></script>
<script src='../static/resources/js/fullcalendar/locale/ko.js'></script>
<script>

    document.addEventListener('DOMContentLoaded', function () {

        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {

            headerToolbar: {
                left: 'prevYear,prev today',
                center: 'title',
                right: 'next,nextYear'
            },
            locale: "ko",
            initialDate: new Date(),
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            dayMaxEvents: true, // allow "more" link when too many events
            editable: true,
            displayEventTime: false,
            dateClick: function () {
                <c:choose>
                <c:when test="${loginCheck == null}">
                    UIkit.modal(document.getElementById("modal-example")).show();
                </c:when>
                <c:when test="${loginCheck != null}">

                </c:when>
                </c:choose>
            },
            events: [
                <c:forEach items="${holidayList}" var="item">
                {
                    <c:if test="${item.holi_flag == 'Y' ? true : false}" var="result">
                    color: '#f00',
                    </c:if>
                    title: '${item.locdate_name}',
                    start: '${item.locdate_min}',
                    end: '${item.locdate_max}'
                },
                </c:forEach>
                <c:choose>
                <c:when test="${loginCheck == null}">
                </c:when>
                <c:when test="${loginCheck != null}">
                <c:forEach items="${userVacationList}" var="item">
                {
                    title: '(${item.name}/${item.type})${item.content}',
                    start: '${item.s_date}',
                    <c:if test="${item.state == '0' ? true : false}" var="result">
                    color: '#f00'
                    </c:if>
                    <c:if test="${item.state == '1' ? true : false}" var="result">
                    color: '#00f'
                    </c:if>
                },
                </c:forEach>

                </c:when>
                <c:otherwise></c:otherwise>
                </c:choose>
            ]
        });

        calendar.render();
    });

</script>
<style>

    body {
        margin: 40px 10px;
        padding: 0;
        font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
        font-size: 14px;
    }

    #calendar {
        max-width: 1100px;
        margin: 0 auto;
    }

</style>
<div class="uk-margin-top" id='calendar'></div>