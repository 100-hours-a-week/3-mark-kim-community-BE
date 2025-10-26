package kr.adapterz.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/terms")
    public String getTerms() {
        return "terms";
    }

    @GetMapping("/privacy")
    public String getPrivacy() {
        return "privacy";
    }
}
