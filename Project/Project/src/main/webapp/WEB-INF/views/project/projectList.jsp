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
                        <link rel="stylesheet" href="${contextPath}/resources/css/project/projectList.css">
                        <link rel="stylesheet" href="${contextPath}/resources/css/common/aside.css">
                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
                            crossorigin="anonymous"></script>
                    </head>

                    <body>
                        <div id="wrap">

                            <main>

                                <jsp:include page="/WEB-INF/views/common/aside.jsp" />

                                <div class="rightDiv">

                                    <!-- Title -->
                                    <div>
                                        <p>Management System</p>
                                        <div>
                                            <a href="">나가기</a>
                                        </div>
                                    </div>

                                    <!-- content -->
                                    <div>

                                        <div class="content-wrap">

                                            <!-- 검색 area -->
                                            <div>
                                                <p>프로젝트 조회</p>
                                                <div class="impl-search-area">

                                                    <form action="projectList" method="GET" class="search-form">

                                                        <select id="bigCategory" name="bcategory">
                                                            <option selected value="1">전체</option>

                                                            <c:forEach var="selectCategory"
                                                                items="${bigCategoryList.searchBigCat}">
                                                                <option value="${selectCategory.codeNo}">
                                                                    ${selectCategory.codeId}</option>
                                                            </c:forEach>

                                                            <option value="9999">이름</option>

                                                            <option value="7942">선택조회</option>
                                                        </select>
                                                        <input class="hireDate" type="date" name="startDate"
                                                            id="startDate" class="dateBtn" onfocus="this.showPicker()"
                                                            style="display: none;">
                                                        <div class="waterbooom" style="display: none;">~</div>
                                                        <input class="hireDate" type="date" name="endDate" id="endDate"
                                                            class="dateBtn" onfocus="this.showPicker()"
                                                            style="display: none;">

                                                        <c:forEach var="selectCategory"
                                                            items="${bigCategoryList.searchBigCat}" varStatus="status">
                                                            <select name="ssearch" style="display:none"
                                                                data-codeNo="${selectCategory.codeNo}"
                                                                class="searchValue${status.index + 1} ssearch-content">
                                                                <option selected disabled
                                                                    value="${selectCategory.codeNo}">
                                                                    ${selectCategory.codeId}&nbsp;&nbsp;
                                                                </option>
                                                                <option value="all">전체</option>
                                                            </select>
                                                        </c:forEach>



                                                        <input type="text" id="memberName" style="display: none;"
                                                            name="memberName">
                                                        <button class="searchBtn">검색하기</button>

                                                    </form>


                                                </div>
                                            </div>

                                            <div class="member-deatil-wrap">

                                                <div class="md-left-side">

                                                    <div class="image_wrap">
                                           
                                                            <p>프로젝트 리스트</p>
                                                   
                                                    </div>

                                                    <div class="changeWrap">
                                                        <div><a>등록된 프로젝트 목록</a>
                                                        </div>
                                                    </div>
                                                    <div class="default-infoWrap">
                                                        <c:forEach var="projectList"
                                                            items="${getProjectList.projectList}">
                                                            <div class="project_value">
                                                                <a data-projectno="${projectList.projectNo}">${projectList.projectValue}</a>
                                                            </div>
                                                        </c:forEach>    
                                                    </div>

                                                    <div class="page_Nation" style="display: none;">
                                                        <c:set var="url" value="?cp=" />
                                                        <c:set var="pagination"
                                                            value="${getProjectList['pagination']}" />
                                                        <c:set var="currentPage" value="${pagination.currentPage}"
                                                            scope="request" />
                                                        <div>
                                                            <a href="${url}1">&lt;&lt;</a>
                                                        </div>
                                                        <div>
                                                            <a href="${url}${pagination.prevPage}">&lt;</a>
                                                        </div>
                                                        <c:forEach var="i" begin="${pagination.startPage}"
                                                            end="${pagination.endPage}" step="1">
                                                            <c:choose>
                                                                <c:when test="${i == currentPage}">
                                                                    <div>
                                                                        <a class="selected_Cp">${i}</a>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div>
                                                                        <a href="${url}${i}">${i}</a>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                        <div>
                                                            <a href="${url}${pagination.nextPage}">&gt;</a>
                                                        </div>
                                                        <div>
                                                            <a href="${url}${pagination.maxPage}">&gt;&gt;</a>
                                                        </div>
                                                    </div>

                                                </div>

                                                <div class="md-middle-side">
                                                    <div>
                                                        <div class="image_wrap">
                                                            <p>세부사항</p>
                                                        </div>
                                                        <div class="changeWrap">
                                                            <div><a>프로젝트 세부 사항</a>
                                                            </div>
                                                        </div>
                                                        <div class="default-infoWrap">
                                                            <div class="dateArea">
                                                                <p>기간</p><input type="date" class="dateBtn" id="projectSDate"
                                                                    name="projectSDate" onfocus="this.showPicker()" readonly>
                                                                <span class="dateSep">~</span>

                                                                <input type="date" name="projectEDate" id="projectEDate" class="dateBtn"
                                                                    onfocus="this.showPicker()" readonly>
                                                            </div>
                                                            <div>
                                                                <p>금액</p><input type="text" class="projectPrice" id="projectPrice"
                                                                    readonly value="">
                                                            </div>
                                                            <div class="input-addr-wrap">
                                                                <p>주소</p>
                                                                <div class="post-detail">
                                                                    <div class="signUp-input-area">
                                                                        <input type="text" id="sample4_postcode" class="projectAddr"
                                                                            name="projectAddr" placeholder="우편번호"
                                                                            maxlength="6" readonly>

                                                                        <button type="button" class="post-search-btn"
                                                                            onclick="return sample4_execDaumPostcode()">검색</button>
                                                                    </div>

                                                                    <div class="signUp-input-area">
                                                                        <input type="text" id="sample4_roadAddress" class="projectAddr"
                                                                            name="projectAddr" placeholder="도로명주소"
                                                                            readonly>
                                                                    </div>

                                                                    <div class="signUp-input-area">
                                                                        <input type="text" id="sample4_detailAddress" class="projectAddr"
                                                                            name="projectAddr" placeholder="상세주소"
                                                                            readonly>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <p>회사명</p><input type="text" class="corpName" id="corpName" readonly
                                                                    value="">
                                                            </div>
                                                            <div>
                                                                <p>회사번호</p><input type="text" class="corpTel" id="corpTel" readonly
                                                                    value="">
                                                            </div>
                                                            <div>
                                                                <p>책임자명</p><input type="text" class="corpManager" id="corpManager"
                                                                    readonly value="">
                                                            </div>
                                                            <div>
                                                                <p>책임자번호</p><input type="text" class="corpManagerTel" id="corpManagerTel"
                                                                    readonly value="">
                                                            </div>
                                                            <div>
                                                                <p>진행상황</p><input type="text" class="" readonly
                                                                    value="">
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>

                                                <div class="md-right-side">
                                                    <div>
                                                        <div class="image_wrap">
                                                            <p>세부사항</p>
                                                        </div>
                                                        <div class="changeWrap">
                                                            <div><a>참가중인 인원</a>
                                                            </div>
                                                        </div>
                                                        <div class="default-infoWrap">

                                                        </div>
                                                    </div>

                                                </div>



                                            </div>

                                        </div>




                                    </div>

                                </div>

                            </main>

                        </div>



                    </body>



                    <script src="${contextPath}/resources/js/project/projectList.js">

                    </script>


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

                    </html>