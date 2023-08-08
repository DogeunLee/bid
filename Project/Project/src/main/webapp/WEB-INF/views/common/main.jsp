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
						<link rel="stylesheet" href="${contextPath}/resources/css/main/main.css">
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
											<li class="highlighted"><a href="${contextPath}/main">직원
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
												<p>직원 조회</p>
												<div class="impl-search-area">
													<select name="" id="">
														<option value="">아이디</option>
														<option value="">이름</option>
														<option value="">성별</option>
														<option value="">나이</option>
														<option value="">레벨</option>
													</select> <input type="text"> <a href="">Search</a>
												</div>
											</div>

											<div class="table-wrap">
												<table>

													<thead>
														<th>직원ID</th>
														<th>이름</th>
														<th>성별</th>
														<th>주민번호</th>
														<th>입사일</th>
														<th>등급</th>
														<th>연락처</th>
														<th>이메일</th>
														<th>주소</th>
														<th>퇴사여부</th>
													</thead>

													<tbody>
														<c:forEach var="memberList" items="${getMemberList.memberList}">
															<tr class="row" onclick="">
																<td>${memberList.memberId}</td>
																<td>${memberList.memberName}</td>
																<td>${memberList.memberGender}</td>
																<td>${memberList.memberBirth}</td>
																<td>${memberList.memberHire}</td>
																<td>${memberList.memberLv}</td>
																<td>${memberList.memberTel}</td>
																<td>${memberList.memberEmail}</td>
																<td>${memberList.memberAddr}</td>
																<td>${memberList.memberSt}</td>
															</tr>
														</c:forEach>
													</tbody>

												</table>

												<div class="page_Nation">
													<c:set var="url" value="?cp=" />
													<c:set var="pagination" value="${lostList['pagination']}" />
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

										</div>



									</div>
							</main>


						</div>
					</body>

					

					<script src="${contextPath}/resources/js/main/main.js">
						
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