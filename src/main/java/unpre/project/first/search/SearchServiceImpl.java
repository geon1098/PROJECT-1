package unpre.project.first.search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unpre.project.first.write.WriteDao;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	SearchDAO searchDao;
	
	//게시글 목록
	@Override  
	public List<Map<String, Object>> searchList(Map<String, Object> map){  
		return this.searchDao.selectList(map);
	}
	
	//게시글 목록
	@Override  
	public List<Map<String, Object>> nicknameDistinct (Map<String, Object> map){  
		return this.searchDao.distinct(map);
	}
}
