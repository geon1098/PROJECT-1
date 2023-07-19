package unpre.project.first.search;

import java.util.List;
import java.util.Map;

public interface SearchService {

	List<Map<String, Object>> searchList(Map<String, Object> map);

	//닉네임 중복제거
	List<Map<String, Object>> nicknameDistinct(Map<String, Object> map);

	
}
