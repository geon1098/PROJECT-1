function check(){
   
   var result = confirm("등록하시겠?");
   
         if(writeboard.category.value == ""){
            alert("카테고리를 선택해 주세요.");
            writeboard.category.focus();
            return false;
         }
         else if (writeboard.title.value == 0){
            alert("제목을 작성해 주세요.");
            writeboard.title.focus();
            return false;
         }
         else if(result == true){
            alert("확인~");
            
            }
            else{
           alert("취소");
            return false;   
   }
}
