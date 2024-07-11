package org.example.util;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class ValidateUtil {
    public static void vibrateTextField(TextField textField) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(150), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(250), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(300), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(350), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(400), new KeyValue(textField.translateXProperty(), 0))

        );

        textField.setStyle("-fx-border-color: red;");
        timeline.play();

        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(textField.styleProperty(), "-fx-border-color: #bde0fe;"))
        );

        timeline1.play();
    }
}

