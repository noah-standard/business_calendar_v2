package com.end.demo.controllor.cms.rest;

import com.end.demo.service.cms.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationRestControllor {

    @Autowired
    VacationService vacationService;

    @GetMapping("/cms/ajax/getvacation")
    public int getVacation(@RequestParam(required = false) int idx){
        int count = vacationService.getVacation(idx);
        return count;
    }

    @GetMapping("/cms/ajax/getvacationapply")
    public double getVacationApply(@RequestParam(required = false) int idx){
        double count = vacationService.getVacationApply(idx);
        return count;
    }

    @GetMapping("/cms/ajax/getvacationrest")
    public double getVacationRest(@RequestParam(required = false) int idx){
        double vacation_total = vacationService.getVacation(idx);
        double vacation_apply = vacationService.getVacationApply(idx);
        return vacation_total-vacation_apply;
    }


}
