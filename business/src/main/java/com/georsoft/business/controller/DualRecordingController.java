package com.georsoft.business.controller;

import com.georsoft.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dualRecording")
public class DualRecordingController {

    @GetMapping("/getIdTypeOptions")
    public AjaxResult getIdTypeOptions() {

    }
}
