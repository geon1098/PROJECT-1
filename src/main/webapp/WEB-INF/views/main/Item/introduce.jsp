<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.container {
  width: 100%;
  height: 200px;

}
img {
  max-width: 100%;
  height: 350px;
  display: block;

}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<!-- Navigator -->
	<!-- upper navbar here -->
	<jsp:include page="../common/navbar.jsp" />

	<!-- Content -->
	<div class="container px-4 px-lg-5">
		
	<!-- Heading Row-->
		<div class="row gx-4 gx-lg-5 align-item-center my-5" style="margin-top: 7rem!important; ">
    		<div class="col-lg-5">
    
        	</div>
        	<div class="col-lg-7">
				<h1 class="font-weight-light" style="margin-top: 2rem; margin-bottom: 1rem;">${ data.item_name }</h1>
				          
			</div>
    	</div>    
    	<hr style="border-bottom: 1px solid #dbdbdb; margin-bottom: 3rem;">           
		    
      <!-- Content Row-->       
      <div class="row gx-5 align-items-center my-5">
      	<div class="col-lg-4">
      	
      		<span>
      		<img src="/resources/image/item/${data.img }"/>
      		</span>
      		
      	</div>
      	<div class="col-lg-7">
      		<h1 class="font-weight-light"></h1>
				<ul class="info-list">
				
                       
                        <li><strong>대분류</strong> 
                            <span>
                                 
                                ${ data.b_category } &nbsp;
                                
                            </span>
                        </li>
                        <li><strong>소분류</strong> 
                            <span>${ data.s_category }</span>
                        </li>
                        <li><strong>도수</strong> 
                            <span>${ data.proof }</span>
                        </li>
                        <li><strong>원산지</strong>
                            <span>${ data.origin }</span>
                        </li>
                        <li><strong>품종</strong>
                            <span>${ data.race }</span>
                        </li>
                        <li><strong>생산연도</strong>
                            <span>${ data.p_year }</span>
                        </li>
                      <li><strong>제품 소개</strong><br>
                      <span>${data.i_introduce }</span>
                      <li><strong>제품 출처</strong>
                      <span>${data.m_place }</span>
                    </ul>                    
      		
      	</div>
      </div>
     
	</div>
            
	
	
	
	
	

	<!-- Footer -->
	<!-- footer here -->
	<footer class="fixed-bottom">
	<jsp:include page="../common/footer.jsp" />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</footer>
</body>
</html>