package unpre.project.first.adwrite;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdWriteDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insert(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("adboard.adwrite_insert", map); //xml잘 보고 바꾸자

	}


	//관리자 게시글 상세 메소드
	public Map<String, Object> selectDetail(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectOne("adboard.adselect_detail", map);
	}


	//관리자 게시글 수정 메소드
	public int adupdate(Map<String, Object> map) {
		return this.sqlSessionTemplate.update("adboard.adupdate", map);
	}


	//관리자 게시글 삭제 메소드
	public int delete(Map<String, Object> map) {
		return this.sqlSessionTemplate.delete("adboard.addelete", map);
	}

	//관리자 게시글 목록 메소드
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return this.sqlSessionTemplate.selectList("adboard.adselect_list", map);
	}
}