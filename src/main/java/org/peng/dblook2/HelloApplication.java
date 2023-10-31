package org.peng.dblook2;

/**
 * 清晨，明亮的露珠从草叶尖上滑落下来，照旧落入小河。
 * 明天，人们不会发现什么。
 * 只有河里的鹅卵石在无休止的说，我看见了，我看见了。
 * 但，没有人知道。
 */

import javafx.application.Application;
import org.peng.dblook2.util.Common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class HelloApplication {
    static org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(HelloApplication.class);

    public static void main(String[] args) {
        String poetry = """
                          清晨，明亮的露珠从草叶尖上滑落下来，照旧落入小河。
                          明天，人们不会发现什么。
                          只有河里的鹅卵石在无休止的说，我看见了，我看见了。
                          但，没有人知道。
                          
                          千秋无绝色, 悦目是佳人; 
                          倾国倾城貌, 惊为天下人.
                """;
        System.out.println(poetry);

        // 检查配置文件是否存在
        String userdir = Common.userdir;
        java.io.File userdirfile = new File(userdir);
        java.io.File dblook_config = new File(Common.dblook_conf);
        java.io.File dblook_file = new File(Common.dblook_file);
        if (!userdirfile.exists()) {
            try {
                //userdirfile.createNewFile();

                userdirfile.mkdir();
                LOG.debug("创建目录: " + userdirfile.getAbsolutePath());
                //java.io.File dblook_config = new File(Common.dblook_conf);
                dblook_config.createNewFile();
                writeProperties(dblook_config);
                LOG.debug("创建文件: " + dblook_config.getAbsolutePath());
                //java.io.File dblook_file = new File(Common.dblook_file);

                dblook_file.createNewFile();
                LOG.debug("创建文件: " + dblook_file.getAbsolutePath());
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
        // 如果配置文件存在，判断文件是否有内容
        if ( dblook_config.length() == 0){
            writeProperties(dblook_config);
        }


        Application.launch(dblook.class);
    }

    static  private void writeProperties(File proFile){
        try {
            java.io.BufferedOutputStream bo = new java.io.BufferedOutputStream(new java.io.FileOutputStream(proFile));
            java.io.OutputStreamWriter oswrite = new OutputStreamWriter(bo);
            String projectfile = """
                    project = project
                    project.driver = driver
                    project.url = dburl
                    project.database = database
                    project.username = usename
                    project.password = password
                    project.characterEncoding = utf-8
                    project.InitialSize = 1
                    project.MaxActive= 1
                    project.MaxWait = 1
                    project.MinIdle = 1
                    """;
            oswrite.write(projectfile);
            oswrite.close();
            bo.close();

        } catch (FileNotFoundException  e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}