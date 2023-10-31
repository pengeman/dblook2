package fileHelp;

import dbhelp.YamlUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * 处理properties文件
 */
public class ProHelp {

    // 读取config文件,返回properties
    public Properties searchConfig(){
        return this.getConfig();
    }

    /**
     * 查找配置文件,查找顺序:properties,xml,yml,json,如果没有找到,返回null
     *
     * @return
     */
    private Properties getConfig() {
        File configFile = null;
        //URL url = null;
        String urlname = "";
        // 查找配置文件,查找顺序:properties,xml,yml,json
        int urlFlag = 0; // url文件的类型,0:properties,1:xml,2:yml,3:json
        urlname = this.getClass().getResource("/").getPath() + "dblook_config.properties";
        System.out.println("140,rul " + urlname);
        if (urlname == null) {
            urlname = this.getClass().getResource("/").getPath() + "dblook_config.xml";
            urlFlag = 1;
            if (urlname == null) {
                urlname = this.getClass().getResource("/").getPath() + "dblook_config.yml";
                urlFlag = 2;
            }
            if (urlname == null) {
                urlname = this.getClass().getResource("/").getPath() + "dblook_config.json";
                urlFlag = 3;
            }
        }
        if (urlname == null)
            return null;
        else {
            // 解析url
            return parseURL(urlname, urlFlag);
        }
    }

    private Properties parseURL(String urlname, int urlFlag) {
        Properties pro = new Properties();
        File configFile = new File(urlname);
        YamlUtil yamlUtil = new YamlUtil();
        try {
            switch (urlFlag) {
                case 0: // properties
                    FileInputStream fis = new FileInputStream(configFile);
                    pro.load(fis);
                    break;
                case 1: // xml
                    pro.loadFromXML(new FileInputStream(configFile));
                    break;
                case 2: // yml
                    Yaml yml = new Yaml();
                    //HashMap  ret = yml.load(this.getClass().getClassLoader().getResourceAsStream(url.toString()));
                    pro = yamlUtil.toProperties(urlname);
                    break;
                case 3: // json                   //JSON to Map
                    /*String mapJson = JSONObject.toJSONString(map);
                    Map maps = (Map)JSON.parse(mapJson);*/
                    Map<String, String> jsonmap = yamlUtil.getPropertiesMap(urlname);
                    Properties finalPro = pro;
                    jsonmap.forEach((k, v) -> {
                        finalPro.setProperty(k, v);
                    });
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + urlFlag);
            }
            return pro;
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
        return null;
    }


}
