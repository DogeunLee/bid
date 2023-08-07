<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <%@ page session="false" %>

                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>signUp</title>

                        <link rel="stylesheet" href="${contextPath}/resources/css/main/signUp.css">
                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
                            crossorigin="anonymous"></script>
                    </head>


                    <body>
                        <div id="wrap">

                            <main>
                                <aside class="leftSide">
                                    <div class="photo-area">
                                        <img src="${contextPath}/resources/images/main/shin.png" alt="">
                                        <p>${loginMember.memberName}님 어서오세요!</p>
                                    </div>

                                    <div class="myPageWrap">

                                        <ul>
                                            <li><a href="${contextPath}/main">직원
                                                    조회</a></li>
                                            <li class="highlighted"><a href="${contextPath}/signUp">직원 등록</a></li>
                                            <li><a href="#">프로젝트 목록</a></li>
                                            <li><a href="#">프로젝트 등록</a></li>
                                        </ul>

                                    </div>

                                    <div class="search-area">
                                        <p>경력검색</p>
                                        <input type="text">
                                        <div><a href="">Search</a></div>

                                    </div>

                                </aside>
                                <div class="rightDiv">

                                    <div>
                                        <p>Management System</p>
                                        <div><a href="">나가기</a></div>
                                    </div>

                                    <div>
                                        <div class="content-wrap">
                                            <div>
                                                <p>직원 등록</p>
                                            </div>

                                            <form action="">

                                                <div class="memberRegWrap">
                                                    <div>
                                                        <div>
                                                            <p>사번</p><input type="text" placeholder="Only Number"
                                                                id="memberId">
                                                            <div id="memberMessage"></div>
                                                        </div>

                                                        <div>
                                                            <p>비밀번호</p><input type="password"
                                                                placeholder="Input your Password" id="memberPw">
                                                        </div>
                                                        <div>
                                                            <p>비밀번호 확인</p><input type="password"
                                                                placeholder="Input your Password" id="memberPwConfirm">
                                                            <div id="pwMessage"></div>
                                                        </div>
                                                        <div>
                                                            <p>이름</p><input type="text" placeholder="Name"
                                                                id="memberName">
                                                            <div id="nameMessage"></div>
                                                        </div>

                                                        <div>
                                                            <p>주민등록번호</p>
                                                            <input type="text" id="firstPart" placeholder="input only number"> <span class="birthpace">-</span> 
                                                            <input type="text" id="secondPart" placeholder="input only number">
                                                        </div>

                                                        <div class="member-tel">
                                                            <p>전화번호</p>
                                                            <input type="text" placeholder="input without ' - '" id="memberTel"> <div id="telMessage"></div>

                                                        </div>

                                                        <div>
                                                            <p>이메일</p>
                                                            <input type="text" id="memberEmail">
                                                            <span class="email-at">@</span>
                                                            <input type="text" id="email-domain"
                                                                placeholder="Input / Select your Email">
                                                            <select name="" id="select-mail">
                                                                <option value="type">직접입력</option>
                                                                <option value="naver">@naver.com</option>
                                                                <option value="google">@google.com</option>
                                                                <option value="daum">@duam.net</option>
                                                            </select>
                                                        </div>

                                                        <div>
                                                            <p>입사일</p>
                                                            <input id="hireDate" type="date"
                                                                placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                                onfocus="this.showPicker()">
                                                        </div>

                                                        <div>
                                                            <p>레벨</p>
                                                            <select name="mLevel" id="mLevel">
                                                                <option disabled selected>레벨</option>
                                                                <option value="10">신입</option>
                                                                <option value="20">초급</option>
                                                                <option value="30">중급</option>
                                                                <option value="40">고급</option>
                                                            </select>
                                                        </div>


                                                        <div>
                                                            <p>주소</p>
                                                            <div class="input-addr-wrap">
                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_postcode"
                                                                        name="userAddr" placeholder="우편번호" maxlength="6"
                                                                        readonly>

                                                                    <button type="button" class="post-search-btn"
                                                                        onclick="return sample4_execDaumPostcode()">검색</button>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_roadAddress"
                                                                        name="userAddr" placeholder="도로명주소" readonly>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_detailAddress"
                                                                        name="userAddr" placeholder="상세주소" readonly>
                                                                </div>
                                                            </div>

                                                        </div>

                                                    </div>

                                                    <div class="photo-wrap">
                                                        <div class="photo-image-wrap addfileList"></div>
                                                        <input type="file" id="fileInput" class="align_File"
                                                            name="file">
                                                    </div>

                                                    <div>
                                                        <button id="insertInfo">
                                                            저장하기
                                                        </button>
                                                    </div>
                                                </div>



                                            </form>

                                        </div>

                                    </div>



                                </div>
                            </main>


                        </div>

                    </body>

<c:if test="${ !empty sessionScope.message }">
	<script>
                                alert("${message}");
				
                            </script>

	<%-- message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${ !empty message }">
	<script>
                                alert("${message}");
        					
                            </script>
</c:if>
                    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
                    <script src="${contextPath}/resources/js/main/signUp.js"></script>

                    </html>