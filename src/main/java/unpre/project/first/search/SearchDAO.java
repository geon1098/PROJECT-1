package unpre.project.first.search;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// 게시글 목록 메소드
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("board.select_list", map);
	}
	
	//게시글 닉네임 중복제거
	public List<Map<String, Object>> distinct(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("user.distinct", map);
	}
}
