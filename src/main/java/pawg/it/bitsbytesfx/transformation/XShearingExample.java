package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class XShearingExample extends Application {
    @Override
    public void start(Stage stage) {
        Polygon hexagon1 = new Polygon();
        hexagon1.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
        });
        hexagon1.setFill(Color.BLUE);
        hexagon1.setStroke(Color.BLACK);

        Shear shear = new Shear();
        shear.setPivotX(200);
        shear.setPivotY(250);
        shear.setX(0.5);
        shear.setY(0.0);

        Polygon hexagon2 = new Polygon();
        hexagon2.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
                150.0, 150.0,
        });
        hexagon2.setFill(Color.TRANSPARENT);
        hexagon2.setStroke(Color.BLACK);

        hexagon2.getTransforms().addAll(shear);

        Group root = new Group(hexagon1, hexagon2);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Shearing Example ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
