package com.georsoft.system.constant;

import java.util.HashMap;
import java.util.Map;

public class DictionaryType {

    private static final DictionaryType INSTANCE = new DictionaryType();
    private final Map<String, Integer> dictionaryTypes;

    private DictionaryType() {
        dictionaryTypes = new HashMap<>();
        initializeDictionaryTypes();
    }

    private void initializeDictionaryTypes() {
        dictionaryTypes.put("sys_user_sex", 9001);
        dictionaryTypes.put("sys_show_hide", 9002);
        dictionaryTypes.put("sys_normal_disable", 9003);
        dictionaryTypes.put("sys_job_status", 9004);
        dictionaryTypes.put("sys_job_group", 9005);
        dictionaryTypes.put("sys_yes_no", 9006);
        dictionaryTypes.put("sys_notice_type", 9007);
        dictionaryTypes.put("sys_notice_status", 9008);
        dictionaryTypes.put("sys_oper_type", 9009);
        dictionaryTypes.put("sys_common_status", 9010);
    }

    public static DictionaryType getInstance() {
        return INSTANCE;
    }

    public Map<String, Integer> getDictionaryTypes() {
        return dictionaryTypes;
    }
}