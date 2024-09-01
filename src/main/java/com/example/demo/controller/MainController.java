package com.example.demo.controller;

import com.example.demo.entities.FooterModel;
import com.example.demo.entities.HeaderModel;
import com.example.demo.entities.Machine;
import com.example.demo.services.FooterModelService;
import com.example.demo.services.HeaderModelService;
import com.example.demo.services.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MachineService machineService;
    private final FooterModelService footerModelService;
    private final HeaderModelService headerModelService;

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Machine> machines = machineService.getAllMachines();
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        model.addAttribute("machineList", machines);
        return "index";
    }
    @GetMapping("/1")
    public String p1(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "page_1";
    }
    @GetMapping("/2")
    public String p2(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "page_2";
    }
    @GetMapping("/3")
    public String p3(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "page_3";
    }
    @GetMapping("/4")
    public String p4(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "page_4";
    }
    @GetMapping("/5")
    public String p5(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "page_5";
    }
    @GetMapping("/reviews")
    public String rp(Model model) {
        FooterModel footerModel = footerModelService.getFooterModelById(1L)
                .orElse(new FooterModel());
        HeaderModel headerModel = headerModelService.getHeaderModelById(1L)
                .orElse(new HeaderModel());

        model.addAttribute("headerModel", headerModel);
        model.addAttribute("footerModel", footerModel);
        return "reviews";
    }

    @GetMapping("/register")
    public String login() {
        return "register";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}


//TODO:Add render database and add uml diagram
//TODO:Add load time (lab2)
