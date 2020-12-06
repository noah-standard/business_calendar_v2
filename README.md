## business_calendar_v1(일정관리 프로그램)
### 개요 및 모듈

#### - 개요 -
<ol>
  <li>직원이 일정신청 (반차, 연차, 하계휴가, 출장, 외근)</li>
  <li>관리자가 일정 검토 후 승인</li>
</ol>

#### - 모듈 -
<ul>
  <li>스프링 부트(Spring Boot)
    <ul>
      <li>스프링 시큐리티(Spring Security) - 관리자(admin), 직원(user) 세션 구분</li>
      <li>MVC 패턴 Controller, Service, Repository 구분</li>
    </ul>
  </li>
  <li>마이바티스(mybatis)
    <ul>
      <li>페이징</li>
      <li>동적쿼리</li>
    </ul>
  </li>
  <li>그래들(Gradle)</li>
  <li>FullCalendar API(JavaScript) - 달력 API</li>
</ul>

### - 테이블 소개 -
<p><img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/calendar_table.jpg" alt="calendar_table"></img>&nbsp<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/color_table.jpg" alt="color_table">
<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/user_list_table.jpg" alt="user_list_table"></p>
<pre>
<strong>CALENDAR TABLE</strong>
- DELETE_FLAG(삭제여부) : 0(삭제미승인), 1(삭제승인)
- AGREE_FLAG FK(일정여부) : 0(일정미승인), 1(일정승인)
<strong>COLOR TABLE</strong>
- AGREE_FLAG PK : CALENDAR TABLE의 부모테이블
- COLOR(색) : 1(#e4006c), 0(#3788d8)
<strong>USER_LIST TABLE</strong>
- PASSWORD : 스프링 시큐리티 암호화 후 저장
</pre>


### - index 페이지 및 로그인 (index) -
<p><img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/index.jpg" alt="index" width="400px" height="200px"></img>&nbsp<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/login.jpg" alt="login" width="230px" height="200px"></p>
<pre>
로그인을 누르면 왼쪽에서 오른쪽으로 로그인화면이 나타남
id : admin으로 관리자 세션 로그인
id : user로 직원 세션 로그인
</pre>

### - 일정 및 신청 (accept) -
<p><img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/calendar.jpg" alt="calendar" width="500px" height="400px"></img>&nbsp<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/calendar_accept.jpg" alt="calendar_accept" width="350px" height="300px"></p>
<pre>
<strong>FullCalendar API 기반 달력</strong>
승인 : 빨간색
미승인 : 파란색<br>
<strong>신청 페이지</strong>
사유 : 5자
유형 : 반차, 연차, 하계휴가, 출장, 외근
시작날짜, 끝날짜, 내용
</pre>

### - 리스트 (check) -
<p><img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/check_user.jpg" alt="check_user" width="400px" height="350px"></img>&nbsp<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/check_admin.jpg" alt="check_admin" width="400px" height="350px"></p>
<pre>
<strong>직원 세션</strong>
- 체크박스 선택 후 삭제 버튼<br>
<strong>관리자 세션</strong>
- 체크박스 선택 후 승인 미승인 버튼
</pre>

### - 페이징 및 동적쿼리 -
<img src="https://github.com/dirend7/business_calendar_v1/blob/master/image/skill.jpg" alt="skill" width="750px" height="350px"></img>
<pre>
- 마이바티스(mybatis)로 조건에 맞게 쿼리를 작성
- 페이징을 위해서 Controller 에서 startIndex & endIndex, maxCountIndex xml로 보냄
</pre>
