package com.example.controller;

import com.example.model.JobInstanceModel;
import com.example.model.JobScheduleModel;
import com.example.service.JobDetailService;
import com.example.service.JobScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class JobManageController {
    @Autowired
    private JobScheduleService jobScheduleService;
    @Autowired
    private JobDetailService jobDetailService;

    @GetMapping(value = {"/", "/main"})
    public String main() {
        return "html/main";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "html/about";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "html/login";
    }

    @GetMapping(value = "/batch/web/scheduleList")
    public String scheduleList(Model model) {
        try {
            List<JobScheduleModel> joblist = jobScheduleService.getJobScheduleList();
            model.addAttribute("joblist", joblist);
        } catch (Exception e) {
            log.error("scheduleList ERROR", e);
        }
        return "html/batch/scheduleList";
    }

    @GetMapping(value = "/batch/web/jobExecuteList")
    public String jobExecuteList(JobInstanceModel jobInstanceModel, Model model) {
        try {
            final int pageNo = jobInstanceModel.getPageNo() < 1 ? 1 : jobInstanceModel.getPageNo();
            final int pageRows = jobInstanceModel.getPageRows() < 10 ? 10 : jobInstanceModel.getPageNo();
            jobInstanceModel.setPageNo(pageNo);
            jobInstanceModel.setPageRows(pageRows);
            List<JobInstanceModel> execJobList = jobDetailService.getJobInstanceList(jobInstanceModel);
            model.addAttribute("execJobList", execJobList);
        } catch (Exception e) {
            log.error("jobExecuteList ERROR", e);
        }
        return "html/batch/jobExecuteList";
    }
}
