package prac3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {
    @RequestMapping("/dashboards")
    public String index(Map<String, Object> model){
        return "dashboards";
    }
}
