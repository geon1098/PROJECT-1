package unpre.project.first.adwrite;

import java.util.List;
import java.util.Map;

public interface AdWriteService {

String adcreate(Map<String, Object> map);
	
	
	//관리자 게시글 상세 인터페이스
	Map<String, Object> addetail(Map<String, Object> map);
	
	//관리자 게시글 수정 인터페이스
	boolean adedit(Map<String, Object> map);

	//관리자 게시글 삭제 인터페이스
	boolean adremove(Map<String, Object> map);

	//관리자 게시글 목록 인터페이스
	List<Map<String, Object>> adlist(Map<String, Object> map);
	
}
