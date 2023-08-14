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
													<button><a>저장</a></button>
													<button><a>삭제</a></button>
												</div>
											</div>

											<div class="member-deatil-wrap">

												<div class="md-left-side">

													<div>
														<P>기본정보</P>
													</div>

													<div class="default-infoWrap">
														<div class="image_wrap">
															<div>
																<img src="${contextPath}/resources/images/main/shin.png"
																	alt="">
															</div>
															<div>
																<div><span class="memberNo">102827</span></div>
																<div><span>이도근</span></div>
															</div>
														</div>

														<div class="infosWrap">


															<div>
																<p>전화번호</p><span>010-8229-7832</span>
																<button>수정</button>
															</div>

															<div class="input-addr-wrap">
																<p>주소</p>
																<div>
																	<div class="signUp-input-area">
																		<input type="text" id="sample4_postcode"
																			name="memberAddr" placeholder="우편번호"
																			maxlength="6" readonly>

																		<button type="button" class="post-search-btn"
																			onclick="return sample4_execDaumPostcode()">검색</button>
																	</div>

																	<div class="signUp-input-area">
																		<input type="text" id="sample4_roadAddress"
																			name="memberAddr" placeholder="도로명주소"
																			readonly>
																	</div>

																	<div class="signUp-input-area">
																		<input type="text" id="sample4_detailAddress"
																			name="memberAddr" placeholder="상세주소"
																			readonly>
																	</div>
																</div>
															</div>

															<div>
																<p>이메일</p>
																<span>leewch90@naver.com</span><button class="emailBtn">수정</button>
															</div>

															<div>
																<p>성별</p><select name="" id="" class="memLv">
																	<option value="">남자</option>
																	<option value="">여자</option>
																	<option value="">없음</option>
																</select>
															</div>

															<div>
																<p>레벨</p>
																<select name="" id="" class="memLv">
																	<option value="">신입</option>
																	<option value="">초급</option>
																	<option value="">중급</option>
																	<option value="">고급</option>
																	<option value="">특급</option>

																</select>
															</div>


															<div>
																<p>학력</p>
																<select name="" id="" class="memLv">
																	<option value="">초졸</option>
																	<option value="">중졸</option>
																	<option value="">전문대졸</option>
																	<option value="">대졸</option>
																	<option value="">대학원졸</option>

																</select>
															</div>

															<div>
																<p>입사일</p>

																<input id="hireDate" type="date" name="memberHire"
																	placeholder=" 입사 날짜를 입력해 주세요." class="dateBtn"
																	onfocus="this.showPicker()">

															</div>

															<div class="passwordInput">
																<p>비밀번호 수정</p>
																<div>
																	<input type="password" placeholder="새로운 비밀번호 입력">
																	<input type="password" placeholder="비밀번호 재입력">
																</div>
																<button>변경</button>

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