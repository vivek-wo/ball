package com.vivek.wo.ball.controller;

import com.vivek.wo.ball.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/v0/get")
    public ResultVo<String> getHttpLog() {
        return new ResultVo<String>(200, "" + System.currentTimeMillis());
    }
}
