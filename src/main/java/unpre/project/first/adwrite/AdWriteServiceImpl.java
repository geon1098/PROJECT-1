package unpre.project.first.adwrite;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdWriteServiceImpl implements AdWriteService{

	@Autowired
	AdWriteDao AdwriteDao;
		
		
		@Override
		public String adcreate(Map<String, Object> map) {
			int affectRowCount = this.AdwriteDao.insert(map);
			if (affectRowCount == 1) {
				return map.get("adb_num").toString();
			}
			return null;
			
		}
		
		@Override
		public Map<String, Object> addetail(Map<String, Object> map){
		    return this.AdwriteDao.selectDetail(map);
		}

		
		@Override  
		public boolean adedit(Map<String, Object> map) {  
		int affectRowCount = this.AdwriteDao.adupdate(map);  
		return affectRowCount == 1;  

		}
		
		@Override  
		public boolean adremove(Map<String, Object> map) {
		int affectRowCount = this.AdwriteDao.delete(map);  
		return affectRowCount == 1;

		}
		@Override  
		public List<Map<String, Object>> adlist(Map<String, Object> map){  
		return this.AdwriteDao.selectList(map);
		
		}
}
