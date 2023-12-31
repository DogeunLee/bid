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
                        <title>Document</title>

                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
                            crossorigin="anonymous"></script>

                        <link rel="stylesheet" href="${contextPath}/resources/css/common/aside.css">
                        <link rel="stylesheet" href="${contextPath}/resources/css/newCorp/newCorp.css">


                    </head>

                    <body>

                        <main>

                            <jsp:include page="/WEB-INF/views/common/aside.jsp" />

                            <div class="rightDiv">

                                <div>
                                    <p>Management System</p>
                                    <div>
                                        <a href="">나가기</a>
                                    </div>
                                </div>


                                <div>
                                    <div class="content-wrap">
                                        <form action="newCorp" method="POST" name="corp-form">
                                        <!-- onsubmit="return corpRegValidate()"> -->
                                            <div>
                                                <p>회사 등록</p>
                                                <div class="btnWrap">
                                                    <button class="saveBtn">저장</button>
                                                    <button type="button">삭제</button>
                                                </div>
                                            </div>

                                            <div class="project-deatil-wrap">
                                                <div>
                                                    <div class="image_wrap">
                                                        <div>
                                                            <p>회사 등록</p>
                                                        </div>
                                                    </div>
                                                    <div class="addtitle">새로운 회사 등록</div>
                                                    <div class="addInfoWrap">

                                                        <div>
                                                            <p>회사명</p>
                                                            <div><input type="text" name="corpName" placeholder="input New CorpName"></div>
                                                            <div id="message"></div>
                                                        </div>

                                                        <div>
                                                            <p>회사번호</p>
                                                            <div><input type="text" name="corpTel" placeholder="input New CorpTel"></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        



                                                        <div class="input-addr-wrap">
                                                            <p>주소</p>
                                                            <div class="post-detail">
                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_postcode"
                                                                       name="corpAddr" placeholder="우편번호"
                                                                        maxlength="6" readonly>

                                                                    <button type="button" class="post-search-btn"
                                                                        onclick="return sample4_execDaumPostcode()">검색</button>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_roadAddress"
                                                                    name="corpAddr" placeholder="도로명주소" readonly>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_detailAddress"
                                                                    name="corpAddr" placeholder="상세주소" >
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div>
                                                            <p>계약일자</p>
                                                            <div><input type="date" class="dateBtn"
                                                                onfocus="this.showPicker()"
                                                               name="corpRegDate"
                                                               ></div>
                                                            <div id="message"></div>
                                                        </div>

                                                        <div class="mat10">
                                                            <p>업종</p>
                                                            <select class="memLv" name="corpUpj">
                                                                <option selected disabled>
                                                                    업종&nbsp;&nbsp;</option>
                                                                <c:forEach var="category" items="${uptOption}">
                                                                    <option value="${category.codeId}">
                                                                        ${category.codeName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="mat10">
                                                            <p>업태</p>
                                                            <select class="memLv" name="corpUpt">
                                                                <option selected disabled>
                                                                  업태&nbsp;&nbsp;</option>
                                                                <c:forEach var="category" items="${upjOption}">
                                                                    <option value="${category.codeId}">
                                                                        ${category.codeName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div>
                                                            <p>책임자명</p>
                                                            <div><input type="text" name="corpManager" placeholder="input New CorpManager"></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        <div>
                                                            <p>책임자번호</p>
                                                            <div><input type="text" name="corpManagerTel" placeholder="input New CorpManagerTel"></div>
                                                            <div id="message"></div>
                                                        </div>

                                                        



                                                    </div>
                                                </div>

                                                <div class="input-wrap">

                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>



                        </main>




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
                    <script src="${contextPath}/resources/js/newCorp/newCorp.js"></script>

                    </html>