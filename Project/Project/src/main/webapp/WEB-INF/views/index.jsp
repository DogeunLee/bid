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

						<link rel="stylesheet" href="${contextPath}/resources/css/style.css">

					</head>

					<body>

						<div id="wrapper">

							<main>
								<div class="loginWrapper">
									<div>
										<p>인사관리</p>
									</div>
									<form action="login" method="POST" onsubmit="return loginValidate();">
										<div class="formInputWrap">
											<div>
												<p>ID</p>
												<input type="text" placeholder="Input your ID" name="memberId" id="memberId">
											</div>
											<div>
												<p>PW</p>
												<input type="password" placeholder="Input your Password" name="memberPw" id="memberPw">
											</div>
											<div class="buttonWrap">
												<button id="loginBtn">LogIn
											</div>
										</div>


										<div class="signUpWrapper">

											<a href="">Sign Up</a> <a href="">Find ID</a> <a href="">Find
												Pw</a>
										</div>

									</form>


								</div>
							</main>

						</div>


					</body>
					<c:if test="${ !empty sessionScope.message }">
						<script>
							alert("${message}");

						</script>

						<%-- message 1회 출력 후 session에서 제거 --%>
							<c:remove var="message" scope="session" />
					</c:if>

					<c:if test="${ !empty message }">
						<script>
							alert("${message}");

						</script>
					</c:if>
					<script src="${contextPath}/resources/js/script.js"></script>

					</html>