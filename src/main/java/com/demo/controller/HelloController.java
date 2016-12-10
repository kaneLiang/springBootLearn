package com.demo.controller;

/**
 * Created by Sniff on 2016/11/2.
 */
import com.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    Map<String, Service> router;

    @RequestMapping("/")
    public String index() {
        return router.get("GET").doSomeThing();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world.";
    }
}