package org.peng.dblook2;

/**
 * 清晨，明亮的露珠从草叶尖上滑落下来，照旧落入小河。
 * 明天，人们不会发现什么。
 * 只有河里的鹅卵石在无休止的说，我看见了，我看见了。
 * 但，没有人知道。
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
        if (!userdirfile.exists()) {
            try {
                //userdirfile.createNewFile();

                userdirfile.mkdir();
                LOG.debug("创建目录: " + userdirfile.getAbsolutePath());
                java.io.File dblook_config = new File(Common.dblook_conf);
                dblook_config.createNewFile();
                LOG.debug("创建文件: " + dblook_config.getAbsolutePath());
                java.io.File dblook_file = new File(Common.dblook_file);
                dblook_file.createNewFile();
                LOG.debug("创建文件: " + dblook_file.getAbsolutePath());
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }


        Application.launch(dblook.class);
    }
}