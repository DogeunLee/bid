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
						<link rel="stylesheet" href="${contextPath}/resources/css/myPage/memberDetail.css">
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
										<form action="">

											<div>
												<p>직원 세부정보</p>
												<div class="btnWrap">
													<button type="button" class="saveBtn">저장</button>
													<button type="button">삭제</button>
												</div>
											</div>

											<div class="member-deatil-wrap">

												<div class="md-left-side">

													<div class="image_wrap">
														<div>
															<img src="${contextPath}/resources/images/main/shin.png"
																alt="">
														</div>
														<div>
															<div><span
																	class="memberNo">${memberInfo.memberId}</span>
															</div>
															<div><span>${memberInfo.memberName}</span></div>
														</div>
													</div>

													<div class="changeWrap">
														<div><a href="">기본정보</a></div>
														<div><a href="">비밀번호</a></div>
													</div>
													

													<div class="default-infoWrap">
														

														<div class="infosWrap">
															<div>
																<p>전화번호</p><input class="telInput" id="memberTel"
																	value="${memberInfo.memberTel}" readonly><div id="telMessage">

																	</div>
															</div>

															<c:set var="addr"
																value="${fn:split(memberInfo.memberAddr, ',,')}" />
															<div class="input-addr-wrap">
																<p>주소</p>
																<div class="post-detail">
																	<div class="signUp-input-area">
																		<input type="text" id="sample4_postcode"
																			name="memberAddr" value="${addr[0]}"
																			placeholder="우편번호" maxlength="6" readonly>

																		<button type="button" class="post-search-btn"
																			onclick="return sample4_execDaumPostcode()">검색</button>
																	</div>

																	<div class="signUp-input-area">
																		<input type="text" id="sample4_roadAddress"
																			name="memberAddr" value="${addr[1]}"
																			placeholder="도로명주소" readonly>
																	</div>

																	<div class="signUp-input-area">
																		<input type="text" id="sample4_detailAddress"
																			name="memberAddr" value="${addr[2]}"
																			placeholder="상세주소" readonly>
																	</div>
																</div>
															</div>

															<div>
																<p>이메일</p>
																<input class="emailInput"
																	value="${memberInfo.memberEmail}" readonly>
																	<div id="emailMessage"></div>
															</div>

															<div>
																<p>성별</p>
																<select class="memberGender">
																	<option selected disabled>
																		${memberInfo.memberGender}&nbsp;&nbsp;</option>

																	<!-- 성별 옵션 렌더링 -->
																	<c:forEach var="category" items="${genderOptions}">
																		<option class="hideOnbush1"
																			value="${category.codeId}">
																			${category.codeName}</option>
																	</c:forEach>
																</select>
															</div>

															<div>
																<p>레벨</p>
																<select class="memLv">
																	<option selected disabled>
																		${memberInfo.memberLv}&nbsp;&nbsp;</option>

																	<!-- 레벨 옵션 렌더링 -->
																	<c:forEach var="category" items="${lvOptions}">
																		<option class="hideOnbush2"
																			value="${category.codeId}">
																			${category.codeName}</option>
																	</c:forEach>
																</select>
															</div>

															<div>
																<p>학력</p>
																<select class="memberGrad">
																	<option selected disabled>
																		${memberInfo.memberGrad}&nbsp;&nbsp;</option>

																	<!-- 학력 옵션 렌더링 -->
																	<c:forEach var="category" items="${gradOptions}">
																		<option class="hideOnbush3"
																			value="${category.codeId}">
																			${category.codeName}</option>
																	</c:forEach>
																</select>
															</div>

															<div>
																<p>입사일</p>

																<input id="hireDate" type="date" name="memberHire"
																	placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
																	onfocus="this.showPicker()"
																	value="${memberInfo.memberHire}" readonly>

															</div>
															
															<div>
																<p>주민등록</p>

																<input class="juminDate"
																	value="${hiddenSSN}" readonly>
															</div>
															
															<div>
																<p>생년월일</p>

																<input  type="date" name="memberBirth"
																	 class="dateBtn" id="memberBirth"
																	onfocus="this.showPicker()"
																	value="${formattedBirth}" readonly>
																

															</div>

														</div>


													</div>

												</div>

												<div class="md-right-side">
													<div>
														<p>추가정보</p>
													</div>
												</div>
											</div>
										</form>
									</div>
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
					<script src="${contextPath}/resources/js/myPage/memberDetail.js"></script>

					</html>