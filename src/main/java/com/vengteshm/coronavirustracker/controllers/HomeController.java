package com.vengteshm.coronavirustracker.controllers;

import com.vengteshm.coronavirustracker.models.LocationStats;
import com.vengteshm.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller// Not a rest controller we return html file as response
public class HomeController {

    @Autowired
    CoronaVirusDataService service;

//    @GetMapping("/")
//    public String home() {
//        return "home"; // Thyme leaf resolves this as html file and default error thrown if file not found in the path
//    }

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = service.getAllStats();
        int totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
        model.addAttribute("locationStats", allStats);// Can be accessed using thymeleaf syntax
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
