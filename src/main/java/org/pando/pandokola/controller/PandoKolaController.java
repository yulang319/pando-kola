package org.pando.pandokola.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网站的所有接口
 */

@RestController
@RequestMapping("/pandokola")
@Log4j2
public class PandoKolaController {

    @RequestMapping("/testPandoKola")
    public String testPandoKola() {
        return "test hello pando kola";
    }

}
