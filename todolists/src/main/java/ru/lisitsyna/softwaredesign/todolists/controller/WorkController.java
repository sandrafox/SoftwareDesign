package ru.lisitsyna.softwaredesign.todolists.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lisitsyna.softwaredesign.todolists.dao.WorkDao;
import ru.lisitsyna.softwaredesign.todolists.model.Work;
import ru.lisitsyna.softwaredesign.todolists.model.WorkList;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WorkController {
    @Autowired
    WorkDao workDao;

    @RequestMapping("/")
    public String start(Model model) {
        model.addAttribute("workLists", workDao.getWorkLists());
        model.addAttribute("worklistN", new WorkList());
        model.addAttribute("workN", new Work());
        return "index";
    }

    @RequestMapping("/get-worklist")
    public String getWorkList(Model model) {
        return start(model);
    }

    @PostMapping(value = "/add-worklist")
    public String addWorkList(@ModelAttribute("worklistN") WorkList workList) {
        workDao.addWorkList(workList);
        return "redirect:/get-worklist";
    }

    @PostMapping(value = "/add-work")
    public String addWork(@ModelAttribute("workN") Work work, @RequestParam("worklistid") int workListId) {
        System.out.println(work.getName());
        workDao.addWork(work, workListId);
        return "redirect:/get-worklist";
    }

    @PostMapping("/remove-work")
    public String removeWork(@RequestParam("worklistid") int worklistid, @RequestParam("workid") int workid) {
        workDao.removeWork(workid, worklistid);
        return "redirect:/get-worklist";
    }

    @PostMapping("/remove-worklist")
    public String removeWorkList(@RequestParam("worklistid") int worklistid) {
        workDao.removeWorkList(worklistid);
        return "redirect:/get-worklist";
    }
}
