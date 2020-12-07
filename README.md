## business_calendar_v2(사내휴가 프로그램) -개발중-
### 개요 및 모듈

#### - 개요 -
##### 노동법 기준 연차법에의해 연차를 사원들에게 부여 및 사용 관리 프로그램

### 관리자 기능
<ol>
  <li>직원이 일정신청 (반차,연차)</li>
  <li>관리자가 일정 검토 후 승인</li>
  <li>사용자메뉴 관리기능</li>
  <li>관리자메뉴 관리기능</li>
  <li>게시판 관리기능</li>
</ol>

### 사용자 기능
<ol>
  <li>로그인 후 일정확인</li>
  <li>마이페이지에서 사원정보 수정 및 일정신청조회</li>
  <li>일정신청조회 승인 전 취소기능</li>
</ol>

#### - 모듈 -
<ul>
  <li>스프링 부트(Spring Boot)
    <ul>
      <li>스프링 시큐리티(Spring Security)를 통한 비밀번호 암호화</li>
      <li>MVC 패턴 Controller, Service, Repository 적용</li>
      <li>스프링 MVC 인터셉터로 로그인여부 확인</li>
    </ul>
  </li>
  <li>마이바티스(mybatis)
    <ul>
      <li>페이징</li>
      <li>동적쿼리</li>
    </ul>
  </li>
  <li>그래들(Gradle) CI</li>
  <li>FullCalendar API(JavaScript) - 달력 API</li>
  <li>휴일 api를 통한 휴일 설정</li>
</ul>

### - 테이블 소개 -
<pre>작성 준비중입니다.</pre>


### - 사용자 (/) -
<pre>
작성 준비중입니다.
</pre>

### - 관리자 (cms) -
<pre>
작성 준비중입니다.
</pre>
