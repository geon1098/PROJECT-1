<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- upper navbar -->
<header style="margin-top: 70px;">

	<nav class="navbar navbar-expand-lg bg-white fixed-top"
		style="height: 47px;">

		<div class="container" style="max-width:1320px">
			<a class="navbar-brand" href="main">Unpredictable</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="navbarSupportedContent">
				<ul class="navbar-nav" style="height: 47px;">

					<c:if test="${signIn.user_id == '관리자'}">
						<li class="nav-item"
							style="border-right: 2px solid; border-left: 2px solid; width: 95px; text-align: center; margin-top: 15px;">
							<a class="nav-link active" href="#">관리페이지</a>
						</li>
					</c:if>

					<li class="nav-item dropdown"
						style="border-right: 2px solid; width: 95px; text-align: center; padding-top:4px;">
						<a class="nav-link dropdown-toggle" href="" role="button" data-bs-toggle="dropdown" aria-expanded="false" style="color:black">커뮤니티</a>
						<ul class="dropdown-menu" style="min-width: 110px; padding-top: 5px; padding-bottom: 5px;">
						
							<li class="nav-item" style="border-bottom: inset;"><a
									class="dropdown-item" style="text-align: center;"
									href="list">전체 게시판</a>
							</li>
							<li class="nav-item" style="border-bottom: inset;"><a
									class="dropdown-item" style="text-align: center;"
									href="list_wine">와인 게시판</a>
							</li>
							<li class="nav-item" style="border-bottom: inset;"><a
									class="dropdown-item" style="text-align: center;"
									href="list_whisky">위스키 게시판</a>
							</li>
							<li class="nav-item" style="border-bottom: inset;"><a
									class="dropdown-item" style="text-align: center;"
									href="list_beer">맥주 게시판</a>
							</li>
							
						
						</ul>
					</li>
					<li class="nav-item"
						style="border-right: 2px solid; width: 95px; text-align: center; padding-top:4px;">
						<a class="nav-link active" href="adlist">대자보</a>
					</li>

					<li class="nav-item"
						style="border-right: 2px solid; width: 95px; text-align: center; padding-top:4px;">
						<a class="nav-link active" href="bar">Bar</a>
					</li>

					<c:if test="${signIn == null }">
						<li class="nav-item"
							style="width: 95px; text-align: center; padding-top:4px;">

							<a class="nav-link active" href="login">로그인</a>
						</li>
					</c:if>

					<c:if test="${signIn.user_id != null}">
						<li class="nav-item dropdown" style=" width: 95px; text-align: center; padding-top:4px;">
						<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">${signIn.nickname} </a>
							<ul class="dropdown-menu"
								style="min-width: 110px; padding-top: 5px; padding-bottom: 5px;">
								<li class="nav-item" style="border-bottom: inset;"><a
									class="dropdown-item" style="text-align: center;"
									href="mypage.do">마이페이지</a></li>
								<li><c:if test="${signIn != null }">
										<a class="dropdown-item" style="text-align: center;"
											href="logout.do">로그아웃</a>
									</c:if></li>

							</ul></li>
					</c:if>

				</ul>
			</div>
		</div>

	</nav>
</header>
<hr style="border: solid 1px gray; box-shadow: 1px 1px 5px 2px gray; margin-top: -23px;">