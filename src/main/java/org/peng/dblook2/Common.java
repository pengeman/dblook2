/*
 * 公共静态类
 */
package org.peng.dblook2;

import org.springframework.boot.system.SystemProperties;

/**
 *
 * @author peng
 */
public class Common {
    /**
     * userdir:当前应用的家目录
     */
    static final public String userdir = System.getProperty("user.home")+"/.dblook2";

    /**
     * dblook_cnofig: 数据库链接项配置文件
     */
    static final public String dblook_conf = userdir + "/dblook.properties";
    /**
     * dblook_file:笔记本记录文件
     */
    static final public String dblook_file = userdir + "/dblook.txt";
    static public dbhelp.DataBase dataBase; // 数据库对象
}
