	function adcheck(){
			if(adwriteboard.adcategory.value == ""){
				alert("카테고리를 선택해 주세요.");
				adwriteboard.adcategory.focus();
				return false;
			}else if (adwriteboard.adtitle.value == 0){
				alert("제목을 작성해 주세요.");
				adwriteboard.adtitle.focus();
				return false;
			}

}
