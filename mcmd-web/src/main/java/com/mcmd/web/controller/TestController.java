package com.mcmd.web.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ppliu
 * @version 1.0
 * @date 2022/6/23 11:34
 */
@RestController
public class TestController {
    @PostMapping("/test")
    public String test() {
        DateUtil.format(new Date(), "yyyy-MM-dd");
        return "Hello Test";
    }
}
