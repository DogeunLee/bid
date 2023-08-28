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

                        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
                            rel="stylesheet" />
                        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
                        <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
                            rel="stylesheet">
                        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

                        <link rel="stylesheet" href="${contextPath}/resources/css/common/aside.css">
                        <link rel="stylesheet" href="${contextPath}/resources/css/project/project.css">


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
                                        <form action="newProj" method="POST" name="proj-form">
                                            <div>
                                                <p>프로젝트 등록</p>
                                                <div class="btnWrap">
                                                    <button class="saveBtn">저장</button>
                                                    <button type="button">삭제</button>
                                                </div>
                                            </div>

                                            <div class="project-deatil-wrap">
                                                <div>
                                                    <div class="image_wrap">
                                                        <div>
                                                            <p>프로젝트 등록</p>
                                                        </div>
                                                    </div>
                                                    <div class="addtitle">새로운 프로젝트</div>
                                                    <div class="addInfoWrap">

                                                        <div>
                                                            <p>프로젝트명</p>
                                                            <div><input type="text" name="projectValue"></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        <div class="dateArea">
                                                            <p>기간</p>
                                                            <div><input type="date" class="dateBtn" name="projectSDate"
                                                                    onfocus="this.showPicker()">
                                                                    <span
                                                                    class="dateSep">~</span>
                                                                    
                                                                    <input type="date"
                                                                    name="projectEDate" class="dateBtn"
                                                                    onfocus="this.showPicker()">
                                                            </div>
                                                        </div>

                                                        <div>
                                                            <p>금액</p>
                                                            <div><input type="text" name="projectPrice"></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        <div class="input-addr-wrap">
                                                            <p>주소</p>
                                                            <div class="post-detail">
                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_postcode"
                                                                        name="projectAddr" placeholder="우편번호"
                                                                        maxlength="6" readonly>

                                                                    <button type="button" class="post-search-btn"
                                                                        onclick="return sample4_execDaumPostcode()">검색</button>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_roadAddress"
                                                                        name="projectAddr" placeholder="도로명주소" readonly>
                                                                </div>

                                                                <div class="signUp-input-area">
                                                                    <input type="text" id="sample4_detailAddress"
                                                                        name="projectAddr" placeholder="상세주소" readonly>
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <div class="mat10">
                                                            <p>회사명</p>
                                                            <select class="memLv" name="corpNo">
                                                                <option selected disabled>
                                                                    회사명&nbsp;&nbsp;</option>
                                                                <c:forEach var="category" items="${corperation}">
                                                                    <option value="${category.corpNo}">
                                                                        ${category.corpName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div>
                                                            <p>책임자명</p>
                                                            <div><input type="text" name="corpManager" readonly></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        <div>
                                                            <p>책임자번호</p>
                                                            <div><input type="text" name="corpManagerTel" readonly></div>
                                                            <div id="message"></div>
                                                        </div>
                                                        
                                                    </div>
                                                </div>

                                                <div class="input-wrap">
                                                    <div class="image_wrap">
                                                        <div>
                                                            <p>프로젝트 세부정보 입력</p>
                                                        </div>
                                                    </div>
                                                    <div class="addtitle">세부정보</div>
                                                    <div class="addInfoWraps">

                                                        <textarea id="summernote"
                                                            name="projectDetail"></textarea>

                                                    </div>
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
                    <script src="${contextPath}/resources/js/project/project.js"></script>

                    </html>