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
						<link rel="stylesheet" href="${contextPath}/resources/css/myPage/memberDetailPw.css">
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
										<form action="updateInfosPw/${memberNo}" method="post"
											onsubmit="return checkPwValidation()">
											<div>
												<p>직원 세부정보</p>
												<div class="btnWrap">
													<button class="saveBtn">저장</button>
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
															<div>
																<span class="memberNo">${memberInfo.memberId}</span>
															</div>
															<div>
																<span>${memberInfo.memberName}</span>
															</div>
														</div>
													</div>

													<div class="changeWrap">
														<div>
															<a
																href="${contextPath}/myPage/memberDetail/${memberNo}">기본정보</a>
														</div>
														<div>
															<a
																href="${contextPath}/myPage/memberDetailPw/${memberNo}">비밀번호</a>
														</div>
													</div>


													<div class="default-infoWrap">

														<div class="pwInputWrap">


															<div class="pwFormWrap">
																<h3>비밀번호 변경</h3>
																<div class="pw-input-triple">
																	<div class="currentPwArea">
																		<span>현재비밀번호</span><input type="password"
																			placeholder="비밀번호를 입력해주세요" name="memberPw">
																		<p id="currentPwMessage"></p>
																	</div>
																	<div class="newPwArea">
																		<span>새로운비밀번호</span><input type="password"
																			placeholder="새로운 비밀번호 입력"
																			class="newPasswordIp" name="newMemberPw">
																		<p id="newPwMessage"></p>
																	</div>
																	<div class="newPwAreaCheck">
																		<span>새비밀번호 확인</span><input type="password"
																			placeholder="새로운 비밀번호 확인"
																			class="newPasswordIpCheck">
																		<p id="newPwCheckMessage"></p>
																	</div>
																</div>
															</div>
														</div>

													</div>

												</div>

												<div class="md-middle-side">
													<div>
														<div class="image_wrap">
															<p>세부사항</p>
														</div>
														<div class="addtitle">추가정보</div>
														<div class="addInfoWrap">
															<div>
																<p>최종학교</p>
																<div><input type="text"
																		value="${memberInfo.graduate.gradName}"
																		id="gradName" name="gradName" readonly>
																</div>
															</div>
															<div>
																<p>학과</p>
																<div><input type="text"
																		value="${memberInfo.graduate.gradValue}"
																		id="gradValue" name="gradValue" readonly>
																</div>
															</div>
															<div class="dateArea">
																<p>재학</p>
																<div><input type="date" class="dateBtn"
																		name="gradHsDate" onfocus="this.showPicker()"
																		value="${memberInfo.graduate.gradHsDate}"
																		readonly id="gradHsDate"><span
																		class="dateSep">~</span><input type="date"
																		name="gradHeDate" class="dateBtn"
																		onfocus="this.showPicker()"
																		value="${memberInfo.graduate.gradHeDate}"
																		id="gradHeDate" readonly></div>
															</div>
															<div>
																<p>경력</p>
																<div><input type="text"
																		value="${memberInfo.exp.expName}" id="expName"
																		name="expName" readonly></div>
															</div>
															<div>
																<p>부서명</p>
																<div><input type="text"
																		value="${memberInfo.exp.expDept}" id="expDept"
																		name="expDept" readonly></div>
															</div>
															<div class="dateArea">
																<p>재직기간</p>
																<div><input type="date" class="dateBtn" name="expSDate"
																		onfocus="this.showPicker()"
																		value="${memberInfo.exp.expSDate}" id="expSDate"
																		readonly><span class="dateSep">~</span><input
																		type="date" class="dateBtn" name="expEDate"
																		onfocus="this.showPicker()" id="expEate"
																		value="${memberInfo.exp.expEDate}" readonly>
																</div>
															</div>

															<c:forEach var="certi" items="${certi}">
																<div>
																	<p>자격증</p>
																	<div><input value="${certi.certiName}"
																			class="certiName" name="certiName" readonly>
																	</div>
																</div>
																<div>
																	<p>취득일</p>
																	<div><input type="date" class="dateBtn certiDate"
																			name="certiDate" onfocus="this.showPicker()"
																			value="${certi.certiDate}" readonly>
																	</div>
																</div>
															</c:forEach>

															<div>
																<p>상태</p>
																<select class="memLv" name="memberSt">
																	<option selected disabled>
																		${memberInfo.memberSt}&nbsp;&nbsp;</option>
																	<c:forEach var="category" items="${statusOptions}">
																		<option value="${category.codeId}">
																			${category.codeName}</option>
																	</c:forEach>
																	</select>
															</div>
														</div>

													</div>
												</div>


												<div class="md-right-side">

													<div></div>
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
					<script src="${contextPath}/resources/js/myPage/memberDetailPw.js"></script>

					</html>