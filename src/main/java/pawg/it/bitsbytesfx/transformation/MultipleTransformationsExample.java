package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MultipleTransformationsExample extends Application {
    @Override
    public void start(Stage stage) {

        Rotate rotate = new Rotate();
        rotate.setAngle(20);
        rotate.setPivotX(150);
        rotate.setPivotY(225);

        Scale scale = new Scale();
        scale.setX(1.5);
        scale.setY(1.5);
        scale.setPivotX(300);
        scale.setPivotY(135);

        Translate translate = new Translate();
        translate.setX(250);
        translate.setY(0);
        translate.setZ(0);

        Rectangle rectangle = new Rectangle(50, 50, 100, 75);
        rectangle.setFill(Color.BURLYWOOD);
        rectangle.setStroke(Color.BLACK);

        rectangle.getTransforms().addAll(rotate, scale, translate);

        Group root = new Group(rectangle);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Multiple transformations");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
