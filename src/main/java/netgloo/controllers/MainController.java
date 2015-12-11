package netgloo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

  @RequestMapping("/")
  //@ResponseBody
  public String index(Map<String, Object> model) {
    return "index";
  }

  @RequestMapping("/mainPage")
  public String welcome(Map<String, Object> model) {
    return "mainPage";
  }

}
