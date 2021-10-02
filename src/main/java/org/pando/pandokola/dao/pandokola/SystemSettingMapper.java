package org.pando.pandokola.dao.pandokola;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.pando.pandokola.model.SystemSetting;

@Mapper
public interface SystemSettingMapper {
    @Insert("INSERT INTO system_setting (system_setting_key, system_setting_value) VALUE (#{setting.systemSettingKey}, #{setting.systemSettingValue})")
    long insert(@Param("setting") SystemSetting setting);

    @Select("SELECT * FROM system_setting LIMIT 1")
    SystemSetting getOne();
}
