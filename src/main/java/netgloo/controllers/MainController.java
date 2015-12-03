package netgloo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {

  @RequestMapping("/")
  //@ResponseBody
  public String index(Map<String, Object> model) {
    System.out.println("ddddddddddd");
    model.put("time", 123456);
    return "index";
  }

  @RequestMapping("/w")
  public String welcome(Map<String, Object> model) {
    model.put("time", new Date());
    return "welcome";
  }
}
