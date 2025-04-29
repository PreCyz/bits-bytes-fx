package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class RotationExample extends Application {
    @Override
    public void start(Stage stage) {
        Rectangle rectangle1 = new Rectangle(150, 75, 200, 150);
        rectangle1.setFill(Color.BLUE);
        rectangle1.setStroke(Color.BLACK);

        Rotate rotate = new Rotate();
        rotate.setAngle(20);
        rotate.setPivotX(150);
        rotate.setPivotY(225);

        Rectangle rectangle2 = new Rectangle(150, 75, 200, 150);
        rectangle2.setFill(Color.BURLYWOOD);
        rectangle2.setStroke(Color.BLACK);

        rectangle2.getTransforms().addAll(rotate);

        Group root = new Group(rectangle1, rectangle2);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Rotation transformation example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
