package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class TranslationExample extends Application {
    @Override
    public void start(Stage stage) {
        Translate translate = new Translate();
        translate.setX(300);
        translate.setY(50);
        translate.setZ(100);

        Circle circle = new Circle();
        circle.setCenterX(150.0f);
        circle.setCenterY(135.0f);
        circle.setRadius(100.0f);
        circle.setFill(Color.BROWN);
        circle.setStrokeWidth(20);

        Circle circle2 = new Circle();
        circle2.setCenterX(150.0f);
        circle2.setCenterY(135.0f);
        circle2.setRadius(100.0f);
        circle2.setFill(Color.CADETBLUE);
        circle2.setStrokeWidth(20);

        circle2.getTransforms().addAll(translate);

        Group root = new Group(circle, circle2);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Translation transformation example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
