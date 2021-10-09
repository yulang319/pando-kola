package org.pando.pandokola.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.pando.pandokola.dao.pandokola.SystemSettingMapper;
import org.pando.pandokola.model.Result;
import org.pando.pandokola.model.SystemSetting;
import org.pando.pandokola.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class MenuService {

    @Autowired
    SystemSettingMapper systemSettingMapper;

    public Result<JSONArray> getMenuInfo() {
        log.info("getMenuInfo enter");
        // 目前先写死 等数据库建立起来再说
        // 而且，什么用户显示什么菜单，都需要由具体的权限管理的
        JSONArray retAry = new JSONArray();

        JSONObject sysJson = new JSONObject();
        sysJson.put("menu_code", 1);
        sysJson.put("menu_name", "系统管理");
        // 系统管理的子菜单
        JSONArray sysSubAry = new JSONArray();
        JSONObject sysSubConfigJson = new JSONObject();
        sysSubConfigJson.put("menu_code", 10);
        sysSubConfigJson.put("menu_name", "配置管理");
        JSONObject sysSubConfigJson2 = new JSONObject();
        sysSubConfigJson2.put("menu_code", 11);
        sysSubConfigJson2.put("menu_name", "权限管理");
        JSONObject sysSubConfigJson3 = new JSONObject();
        sysSubConfigJson3.put("menu_code", 12);
        sysSubConfigJson3.put("menu_name", "用户管理");
        sysSubAry.add(sysSubConfigJson);
        sysSubAry.add(sysSubConfigJson2);
        sysSubAry.add(sysSubConfigJson3);

        sysJson.put("child", sysSubAry);

        JSONObject userJson = new JSONObject();
        userJson.put("menu_code", 2);
        userJson.put("menu_name", "用户管理");
        // 用户管理的子菜单
        JSONArray userSubAry = new JSONArray();
        JSONObject userSubConfigJson = new JSONObject();
        userSubConfigJson.put("menu_code", 20);
        userSubConfigJson.put("menu_name", "用户管理");
        userSubAry.add(userSubConfigJson);

        userJson.put("child", userSubAry);

        retAry.add(sysJson);
        retAry.add(userJson);

        log.info("getMenuInfo end menuInfo:{}", retAry);

        return new Result<>(ApiConstants.ResponseStatusCode.OK, "OK", retAry);
    }

    public Result<PageInfo<SystemSetting>> getAllSystemSettingByPage(int pageStart, int pageLimit) {
        PageHelper.startPage(pageStart, pageLimit);
        List<SystemSetting> dataList = systemSettingMapper.getAllSystemSettings();
        if (CollectionUtils.isEmpty(dataList)) dataList = new ArrayList<>();
        return new Result<>(ApiConstants.ResponseStatusCode.OK, "OK", new PageInfo<>(dataList));
    }

    public Result<String> testSearch(String text) {
        return new Result<>(ApiConstants.ResponseStatusCode.OK, "OK", "后端返回结果为：" + text);
    }

    public static void main(String[] args) {
        new MenuService().getMenuInfo();
    }
}
