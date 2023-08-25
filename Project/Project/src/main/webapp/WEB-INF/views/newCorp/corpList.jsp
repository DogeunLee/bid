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
						<link rel="stylesheet" href="${contextPath}/resources/css/newCorp/corpList.css">
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
												<p>회사 조회</p>
												<div class="impl-search-area">

													<form action="main" method="GET" class="search-form">

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
														id="startDate"
														class="dateBtn"
															onfocus="this.showPicker()" style="display: none;">
														<div class="waterbooom" style="display: none;">~</div>
															<input class="hireDate" type="date" name="endDate"
														id="endDate"
															class="dateBtn"
															onfocus="this.showPicker()"  style="display: none;">	

															<c:forEach var="selectCategory" items="${bigCategoryList.searchBigCat}" varStatus="status">
																<select name="ssearch" style="display:none" data-codeNo="${selectCategory.codeNo}" class="searchValue${status.index + 1} ssearch-content">
																	<option selected disabled value="${selectCategory.codeNo}">
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

											<div class="table-wrap">
												<table>
													<thead>
														<th>회사번호</th>
														<th>회사이름</th>
														<th>업종</th>
														<th>업태</th>
														<th>회사번호</th>
														<th>주소</th>
														<th>담당자</th>
														<th>담당자번호</th>
														<th>등록일자</th>
													</thead>

													<tbody>
														<c:forEach var="corpList" items="${getCorpList.corpList}">
															<tr class="row"
																data-url="${contextPath}/newCorp/corpDetail/${corpList.corpNo}">
																<td>${corpList.corpNo}</td>
																<td>${corpList.corpName}</td>
																<td>${corpList.corpUpj}</td>
																<td>${corpList.corpUpt}</td>
																<td>${corpList.corpTel}</td>
																<td>${corpList.corpAddr}</td>
																<td>${corpList.corpManager}</td>
																<td>${corpList.corpManagerTel}</td>
																<td>${corpList.corpRegDate}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											
											</div>


											<div class="page_Nation">
												<c:set var="url" value="?cp=" />
												<c:set var="pagination" value="${getMemberList['pagination']}" />
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



					<script src="${contextPath}/resources/js/newCorp/corpList.js">

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