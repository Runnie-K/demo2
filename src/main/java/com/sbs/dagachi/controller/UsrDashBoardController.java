package com.sbs.dagachi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.dagachi.service.TestService;
import com.sbs.dagachi.vo.Test;

@Controller
public class UsrDashBoardController {

    @Autowired
    private TestService testService;
    
    @RequestMapping("/usr/dashBoard/main")
    public String showList(Model model, @RequestParam(defaultValue = "1") int tno,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "title, content") String searchKeywordTypeCode,
        @RequestParam(defaultValue = "") String searchKeyword) {
        
        Test test = testService.getTestTno(tno);
        int testsCount = testService.getTestsCount(tno, searchKeywordTypeCode, searchKeyword);
        int itemsCountInAPage = 5;
        int pagesCount = (int) Math.ceil((double) testsCount / itemsCountInAPage);
        
        List<Test> tests = testService.getForPrintTest(tno,
				searchKeywordTypeCode, searchKeyword, itemsCountInAPage, page);

        model.addAttribute("test", test);
        model.addAttribute("tno", tno);
        model.addAttribute("pagesCount", pagesCount);
        model.addAttribute("tests", tests);

        return "usr/dashBoard/main";
    }
}
