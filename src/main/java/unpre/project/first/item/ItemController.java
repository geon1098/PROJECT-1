package unpre.project.first.item;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;

	/*
	 * @RequestMapping(value="/create", method = RequestMethod.GET) public
	 * ModelAndView create() { return new ModelAndView("item/create"); }
	 */
	/*
	 * @RequestMapping(value = "/create", method = RequestMethod.POST) public
	 * ModelAndView createPost(@RequestParam Map<String, Object> map) { ModelAndView
	 * mav = new ModelAndView();
	 * 
	 * String itemNum = this.itemService.create(map); if (itemNum == null) {
	 * mav.setViewName("redirect:/create"); }else {
	 * mav.setViewName("redirect:/introduce?itemNum=" +itemNum ); }
	 * 
	 * return mav; }
	 */

	//상세페이지
	@RequestMapping(value = "/introduce", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = this.itemService.introduce(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("data", detailMap);
		String itemNum = map.get("itemNum").toString();
		mav.addObject("itemNum", itemNum);
		mav.setViewName("/main/Item/introduce");
		return mav;
	}

	//
	@RequestMapping(value = "/winelist", method = RequestMethod.GET)
	public ModelAndView itemlist() {
		return new ModelAndView("main/Item/wineList");
	}

	//
	@RequestMapping(value = "/whiskylist", method = RequestMethod.GET)
	public ModelAndView itemlist1() {
		return new ModelAndView("main/Item/whiskyList");
	}

	@RequestMapping(value = "/beerlist", method = RequestMethod.GET)
	public ModelAndView itemlist2() {
		return new ModelAndView("main/Item/beerList");
	}

}