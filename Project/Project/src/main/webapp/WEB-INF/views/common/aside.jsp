<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
         <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ page session="false" %>

                     <aside class="leftSide">
                        <div class="photo-area">
                           <img src="${contextPath}/resources/images/main/shin.png" alt="">
                           <div>
                              <p>${loginMember.memberName}님</p>
                              <p>어서오세요!</p>
                           </div>
                        </div>

                        <div class="myPageWrap">

                           <ul>
                              <li><a href="${contextPath}/main">직원
                                    조회</a></li>
                              <li><a href="${contextPath}/signUp">직원 등록</a></li>
                              <li><a href="#">프로젝트 목록</a></li>
                              <li><a href="#">프로젝트 등록</a></li>
                           </ul>

                        </div>

                        <div class="search-area">
                           <p>경력검색</p>
                           <input type="text">
                           <div>
                              <a href="">Search</a>
                           </div>
                        </div>

                     </aside>
                     

               <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

             