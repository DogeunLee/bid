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

								<jsp:include page="/WEB-INF/views/common/aside.jsp" />
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

                                            <form action="signUp" method="POST" name="signUp-form"
                                                onsubmit="return signUpValidate()">

                                                <div class="memberRegWrap">
                                                    <div>
                                                        <div>
                                                            <p>사번</p><input type="text" placeholder="Only Number"
                                                                id="memberId" name="memberId">
                                                            <div id="memberMessage"></div>
                                                        </div>

                                                        <div>
                                                            <p>비밀번호</p><input type="password"
                                                                placeholder="Input your Password" id="memberPw"
                                                                name="memberPw">
                                                        </div>
                                                        <div>
                                                            <p>비밀번호 확인</p><input type="password"
                                                                placeholder="Input your Password" id="memberPwConfirm">
                                                            <div id="pwMessage"></div>
                                                        </div>
                                                        <div>
                                                            <p>이름</p><input type="text" placeholder="Name"
                                                                id="memberName" name="memberName">
                                                            <div id="nameMessage"></div>
                                                        </div>

                                                        <div>
                                                            <p>주민등록번호</p>
                                                            <input type="text" name="memberBirth" maxlength="14">
                                                        </div>

                                                        <div class="member-tel">
                                                            <p>전화번호</p>
                                                            <input type="text" placeholder="input without ' - '"
                                                                id="memberTel" name="memberTel">
                                                            <div id="telMessage"></div>

                                                        </div>

                                                        <div>
                                                            <p>이메일</p>
                                                            <input type="text" id="memberEmail" name="memberEmail">
                                                            <div id="emailMessage"></div>

                                                        </div>

                                                        <div>
                                                            <p>입사일</p>
                                                            <input id="hireDate" type="date" name="memberHire"
                                                                placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                                onfocus="this.showPicker()">
                                                        </div>

                                                        <div>
                                                            <p>레벨</p>
                                                            <select id="mLevel" name="memberLv">
                                                                <option disabled selected>레벨</option>
                                                                <option value="LV01">신입</option>
                                                                <option value="LV02">초급</option>
                                                                <option value="LV03">중급</option>
                                                                <option value="LV04">고급</option>
                                                                <option value="LV05">특급</option>
                                                            </select>
                                                        </div>


                                                        <div>
                                                            <p>주소</p>
                                                            <div class="input-addr-wrap">
                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_postcode"
                                                                        name="memberAddr" placeholder="우편번호"
                                                                        maxlength="6" readonly>

                                                                    <button type="button" class="post-search-btn"
                                                                        onclick="return sample4_execDaumPostcode()">검색</button>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_roadAddress"
                                                                        name="memberAddr" placeholder="도로명주소" readonly>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_detailAddress"
                                                                        name="memberAddr" placeholder="상세주소" readonly>
                                                                </div>
                                                            </div>

                                                        </div>

                                                    </div>

                                                    <!-- <div class="photo-wrap">
                                                        <div class="photo-image-wrap addfileList"></div>
                                                        <input type="file" id="fileInput" class="align_File"
                                                            name="file">
                                                        <input type="hidden" name="imageUrl1" id="imageURL" value="">

                                                    </div> -->
                                                    <div>
                                                        <div>
                                                            <p>최종학력</p>
                                                            <select id="mGrad" name="memberGrad">
                                                                <option disabled selected>학력</option>
                                                               <option value="ACHE03">고졸</option>
                                                                <option value="ACHE04">전문대졸</option>
                                                                <option value="ACHE05">대졸</option>
                                                                <option value="ACHE06">대학원졸</option>
                                                            </select>
                                                            <div id="gradMessage"></div>
                                                        </div>
                                                        <div>
                                                            <p>학교이름</p>
                                                            <input type="text">
                                                        </div>
                                                        <div>
                                                            <p>재학기간</p>
                                                            <input id="hireDate" type="date" name="memberHire"
                                                            placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                            onfocus="this.showPicker()">
                                                            <span class="space_area"> ~ </span>
                                                            <input id="hireDate" type="date" name="memberHire"
                                                            placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                            onfocus="this.showPicker()">
                                                        </div>


                                                        <div>
                                                            <p>경력</p>
                                                            <select id="mGrad" name="memberGrad">
                                                                <option disabled selected>학력</option>
                                                               <option value="ACHE03">고졸</option>
                                                                <option value="ACHE04">전문대졸</option>
                                                                <option value="ACHE05">대졸</option>
                                                                <option value="ACHE06">대학원졸</option>
                                                            </select>
                                                            <div id="gradMessage"></div>
                                                        </div>
                                                        <div>
                                                            <p>회사명</p>
                                                            <input type="text">
                                                        </div>
                                                        <div>
                                                            <p>재직기간</p>
                                                            <input id="hireDate" type="date" name="memberHire"
                                                            placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                            onfocus="this.showPicker()">
                                                            <span class="space_area"> ~ </span>
                                                            <input id="hireDate" type="date" name="memberHire"
                                                            placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
                                                            onfocus="this.showPicker()">
                                                        </div>

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