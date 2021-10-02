package org.pando.pandokola.controller;

import lombok.extern.log4j.Log4j2;
import org.pando.pandokola.dao.pandokola.SystemSettingMapper;
import org.pando.pandokola.model.SystemSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Log4j2
@RequestMapping("/test")
public class TestController {

    @Autowired
    SystemSettingMapper systemSettingMapper;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        log.info("hello, {}", name);
        return "Hello, " + name;
    }

    @RequestMapping("/getOneSystemSetting")
    public String getOneSystemSetting() {
        SystemSetting one = systemSettingMapper.getOne();
        if (Objects.isNull(one)) {
            return "还没有数据";
        } else {
            return one.toString();
        }
    }

    @RequestMapping("/addOneSystemSetting")
    public long addOneSystemSetting(@RequestParam("key") String key, @RequestParam("value") String value) {
        SystemSetting setting = new SystemSetting();
        setting.setSystemSettingKey(key);
        setting.setSystemSettingValue(value);

        return systemSettingMapper.insert(setting);
    }
}
