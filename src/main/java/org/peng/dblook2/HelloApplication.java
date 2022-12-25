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

import java.io.IOException;

public class HelloApplication  {


    public static void main(String[] args) {
        String poetry =  """
                          清晨，明亮的露珠从草叶尖上滑落下来，照旧落入小河。
                          明天，人们不会发现什么。
                          只有河里的鹅卵石在无休止的说，我看见了，我看见了。
                          但，没有人知道。
                          
                          千秋无绝色, 悦目是佳人; 
                          倾国倾城貌, 惊为天下人.
                """;
        System.out.println(poetry);
        Application.launch(dblook.class);
    }
}