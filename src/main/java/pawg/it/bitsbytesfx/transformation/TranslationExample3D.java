package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class TranslationExample3D extends Application {
    @Override
    public void start(Stage stage) {
        Translate translate = new Translate();
        translate.setX(200);
        translate.setY(150);
        translate.setZ(100);

        Cylinder cy1 = new Cylinder(50, 100);
        Cylinder cy2 = new Cylinder(50, 100);
        cy2.getTransforms().addAll(translate);

        Group root = new Group(cy1, cy2);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Translation transformation example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
