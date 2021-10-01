package org.pando.pandokola.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        log.info("hello, {}", name);
        return "Hello, " + name;
    }

}
