package unpre.project.first.item;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	 ItemDao itemDao;
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.itemDao.insert(map);
	    if (affectRowCount ==  1) {
	        return map.get("item_num").toString();
	    }
	    return null;
}
	 @Override
	   public Map<String,Object> introduce(Map<String,Object>map){
	      return this.itemDao.selectDetail(map);
	   }
	 
}
