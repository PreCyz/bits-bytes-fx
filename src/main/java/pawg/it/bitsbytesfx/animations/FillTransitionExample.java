package pawg.it.bitsbytesfx.animations;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FillTransitionExample extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle();
        circle.setCenterX(300.0f);
        circle.setCenterY(135.0f);
        circle.setRadius(100.0f);
        circle.setFill(Color.BROWN);
        circle.setStrokeWidth(20);

        FillTransition fillTransition = new FillTransition(Duration.millis(1000));
        fillTransition.setShape(circle);
        fillTransition.setFromValue(Color.BLUEVIOLET);
        fillTransition.setToValue(Color.CORAL);
        fillTransition.setCycleCount(5000);
        fillTransition.setAutoReverse(false);
        fillTransition.play();

        Group root = new Group(circle);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Fill transition example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
