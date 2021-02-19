package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        ArrayList<Job> allJobs = JobData.findAll();
        ArrayList<Job> jobSearchList = new ArrayList<>();
        if(searchType.equals("all") || searchType.isEmpty()) {
            // search all jobs for search term and add to jobSearchList
            for(int i=0; i <allJobs.size(); i++) {
                String search = allJobs.get(i).toString().toLowerCase();
                if (search.contains(searchTerm.toLowerCase())) {
                    jobSearchList.add(allJobs.get(i));
                }
            }
            model.addAttribute("jobList", jobSearchList);
        } else {
        // send search term and search type into findByColumnAndValue and store in arrayList
            jobSearchList = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("jobList", jobSearchList);

        }
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}
