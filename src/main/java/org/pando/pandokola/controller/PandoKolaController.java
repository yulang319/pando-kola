package org.pando.pandokola.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import org.pando.pandokola.model.Result;
import org.pando.pandokola.model.SystemSetting;
import org.pando.pandokola.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 网站的所有接口
 */

@RestController
@RequestMapping("/pandokola")
@Log4j2
public class PandoKolaController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/testPandoKola")
    public String testPandoKola() {
        return "test hello pando kola";
    }

    /**
     * 获取菜单信息.
     * 1. 菜单需要根据登录的用户来确定显示哪些
     * 2. TODO 所有的内容应该从数据库中查询，目前先写死数据
     * <p>
     * 目前的菜单格式,当然应该还有一些其它的信息，以后再补充
     * [{
     * "menu_code":"",
     * "menu_name":"",
     * "child":[
     * {
     * "menu_code":"",
     * "menu_name":""
     * },
     * {
     * "menu_code":"",
     * "menu_name":""
     * },
     * ]
     * }]
     */
    @RequestMapping("/menuInfo")
    @CrossOrigin
    public Result<JSONArray> getMenuInfo() {
        return menuService.getMenuInfo();
    }

    /**
     * 分页获取配置数据记录
     */
    @RequestMapping("/systemSettings")
    @CrossOrigin
    public Result<PageInfo<SystemSetting>> getAllSystemSettingByPage(@RequestParam("pageStart") int pageStart, @RequestParam("pageLimit") int pageLimit) {
        return menuService.getAllSystemSettingByPage(pageStart, pageLimit);
    }

    /**
     * 搜索接口
     */
    @PostMapping("/testSearch")
    @CrossOrigin
    public Result<String> testSearch(@RequestParam("text") String text) {
        return menuService.testSearch(text);
    }
}
