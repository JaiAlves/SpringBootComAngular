package br.com.jailson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class MainController {
 
	/***
	 * ESSE MÉTODO CARREGA A PÁGINA(index.html) DE LOGIN DA NOSSA APLICAÇÃO
	 * @return
	 */
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String index(){	
		String pagina = "index.html";
		
		System.out.println("pagina chamada : "+pagina);
		
	    return pagina;
	}
	
////	 @RequestMapping(value="/", method=RequestMethod.GET)
//	 @RequestMapping(value = "/", method = RequestMethod.GET)
//	    public ModelAndView redirect() {
//	        ModelAndView mv = new ModelAndView("index.html");
//	        return mv;
//	    }
 
}