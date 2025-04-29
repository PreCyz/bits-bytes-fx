package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class RotationExample3D2 extends Application {
    @Override
    public void start(Stage stage) {

        Box box1 = new Box();
        box1.setWidth(150.0);
        box1.setHeight(150.0);
        box1.setDepth(150.0);

        Box box2 = new Box();
        box2.setWidth(150.0);
        box2.setHeight(150.0);
        box2.setDepth(150.0);

        Translate translate = new Translate();
        translate.setX(200);
        translate.setY(150);
        translate.setZ(25);

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(30);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        box2.getTransforms().addAll(translate, rxBox, ryBox, rzBox);

        Group root = new Group(box1, box2);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Transformation of a Box");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
