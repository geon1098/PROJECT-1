<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
    <link rel="stylesheet" href="/resources/css/searchbar.css">
    <link rel="stylesheet" href="/resources/css/search.css">
<title>Insert title here</title>


<!-- CLOUDTURING 챗봇 -->
<script>
	window.dyc = {
		chatbotUid : "b3979c0b019058cb"
	};
</script>
<script async src="https://cloudturing.chat/v1.0/chat.js"></script>
<!-- End CLOUDTURING -->


</head>
<body>
<!-- upper navbar here -->
<jsp:include page="./common/navbar.jsp"/>

<!-- searchbar here -->
<jsp:include page="./common/searchbar.jsp"/><br>

<div class="container" style="margin-top: 5px; margin-bottom: 50px;">

	<div class="integrate_cont" style="border-top:none;">
		<div>
		<h3>게시물</h3>
		</div>	
		<c:forEach var="row" items="${data}">		
		<ul>
			<li>
			<a href="/detail?bNum=${row.b_num}">${row.b_title}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- <fmt:formatDate value="${row.cdate}" pattern="yyyy.MM.dd" />
			</li>	
		</ul>
		</c:forEach>			
		<a href="http://localhost:8081/list?keyword=${keyword}" class="result_more">게시물 더보기 ▶</a>
	</div>	
	
	
	<div class="integrate_cont">
		<div>
		<h3>닉네임</h3>
		</div>
		<c:forEach var="row2" items="${nickname}">
		<ul>
			<li>
			<a href="/list?keyword=${row2.nickname}">${row2.nickname}</a>
			</li>
		</ul>

		</c:forEach>
		<a href="" class="result_more">닉네임 더보기 ▶</a>
		
		
		
	</div>
	<div class="integrate_cont">
		<div>
		<h3>아이템</h3>
		</div>
		<ul>
			<li>
			<a href="">키워드 표시</a>
			</li>
			<li>
			<a href="">키워드 표시</a>
			</li>
			<li>
			<a href="">키워드 표시</a>
			</li>
			<li>
			<a href="">키워드 표시</a>
			</li>
			<li>
			<a href="">키워드 표시</a>
			</li>
		</ul>
		<a href="" class="result_more">아이템 더보기 ▶</a>
	</div>

</div>

<!-- searchbar here -->
<jsp:include page="./common/searchbar.jsp"/><br>


<!-- footer here -->
<jsp:include page="./common/footer.jsp"/>
<script
      src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
   <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>