package unpre.project.first.adwrite;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdWriteController {
	@Autowired
	AdWriteService AdwriteService;

	@RequestMapping(value = "/adwrite", method = RequestMethod.GET)
	public ModelAndView adwrite() {
		return new ModelAndView("main/AdminBoard/adwrite");

	}

	//관리자 게시글 등록 컨트롤러
	@RequestMapping(value = "/adwrite", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		String adbNum = this.AdwriteService.adcreate(map);
		if (adbNum == null) {
			mav.setViewName("redirect:/adwrite");
		} else {
			mav.setViewName("redirect:/Admin_detail?adbNum=" + adbNum);
		}

		return mav;
		
	}


	//관리자 게시글 상세 컨트롤러
	@RequestMapping(value = "/Admin_detail", method = RequestMethod.GET)
	public ModelAndView addetail(@RequestParam Map<String, Object> map) {
		Map<String, Object> addetailMap = this.AdwriteService.addetail(map);
		{

			ModelAndView mav = new ModelAndView();
			mav.addObject("addata", addetailMap);

			String adbNum = map.get("adbNum").toString();

			mav.addObject("adbNum", adbNum); // 파라미터 넘겨주는?
			mav.setViewName("/main/AdminBoard/Admin_detail"); // jsp 경로

			return mav;
		}
	}

	//관리자 수정 페이지
	@RequestMapping(value = "/adupdate", method = RequestMethod.GET)
	public ModelAndView adupdate(@RequestParam Map<String, Object> map) {
		Map<String, Object> addetailMap = this.AdwriteService.addetail(map);

		ModelAndView mav = new ModelAndView();
		mav.addObject("addata", addetailMap);
		mav.setViewName("/main/AdminBoard/adupdate");
		return mav;
	}

	//관리자 게시글 수정 컨트롤러
	@RequestMapping(value = "adupdate", method = RequestMethod.POST)
	public ModelAndView adupdatePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		boolean isUpdateSuccess = this.AdwriteService.adedit(map);
		if (isUpdateSuccess) {
			String adbNum = map.get("adbNum").toString();
			mav.setViewName("redirect:/Admin_detail?adbNum=" + adbNum);
		} else {
			mav = this.adupdate(map);
		}

		return mav;
	}


	@RequestMapping(value = "/addelete", method = RequestMethod.POST)
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();

		boolean isDeleteSuccess = this.AdwriteService.adremove(map); // 삭제가 성공했는지 확인한다.
		if (isDeleteSuccess) {
			mav.setViewName("redirect:/adlist"); // 삭제가 성공했으면 상세페이지가 없으므로 목록으로 리다이렉트.
		} else {
			String adbNum = map.get("adbNum").toString();
			mav.setViewName("redirect:/Admin_detail?adbNum=" + adbNum); // 삭제 실패시 다시 상세페이지로
		}

		return mav;
	}


	@RequestMapping(value = "/adlist") // 전체 목록
	public ModelAndView adlist(@RequestParam Map<String, Object> map) {

		List<Map<String, Object>> list = this.AdwriteService.adlist(map);

		ModelAndView mav = new ModelAndView();

		mav.addObject("addata", list);

		if (map.containsKey("keyword")) {
			mav.addObject("keyword", map.get("keyword"));
		}

		mav.setViewName("main/AdminBoard/adlist");
		return mav;
	}
}
