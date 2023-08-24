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
                        <link rel="stylesheet" href="${contextPath}/resources/css/common/aside.css">
                        <link rel="stylesheet" href="${contextPath}/resources/css/project/project.css">
                        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
                            crossorigin="anonymous"></script>
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
                    
                                            <div>
                                                <p>직원 세부정보</p>
                                                <div class="btnWrap">
                                                    <button class="saveBtn">저장</button>
                                                    <button type="button">삭제</button>
                                                </div>
                                            </div>

                                            <div class="member-deatil-wrap">
                                            </div>

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