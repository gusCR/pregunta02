package pe.edu.cibertec.DAW1_CL2_GustavoCalderon;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("year", 2023);
        return "home";

    }
    @GetMapping("/nosotros")
    public String about() {
        return "about";
    }
    
}
