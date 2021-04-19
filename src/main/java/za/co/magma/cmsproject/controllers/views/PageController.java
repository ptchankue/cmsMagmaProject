package za.co.magma.cmsproject.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/view/{pageId}")
    String myExample1(@PathVariable Long pageId) {
        System.out.println("Page ID: " + pageId);
        // get template

        // get site and page data
        return "viewPage";
    }


}
