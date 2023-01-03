package org.peng.dblook2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义工程项目信息
 */
public class Device {
    static public Map<String,String[]> project = new HashMap<String,String[]>();

    static {
        project.put("mysql", new String[] {"com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/royi?useUnicode=true&characterEncoding=UTF-8"});
        project.put("sqlite",new String[]{"org.sqlite.JDBC","jdbc:sqlite:/test/test.db"});
    }

}
