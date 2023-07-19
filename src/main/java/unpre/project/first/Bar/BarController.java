package unpre.project.first.Bar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BarController {

	@RequestMapping(value="/bar", method= RequestMethod.GET)
	public ModelAndView bar() {
		return new ModelAndView("main/Bar/bar");
	}
	
	@RequestMapping(value="/bar2", method= RequestMethod.GET)
	public ModelAndView bar2() {
		return new ModelAndView("main/Bar/bar2");
	}
}
