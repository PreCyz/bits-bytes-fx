package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Shear;
import javafx.stage.Stage;

public class YShearingExample extends Application {
    @Override
    public void start(Stage stage) {
        Shear shear = new Shear();
        shear.setPivotX(200);
        shear.setPivotY(250);
        shear.setX(0.0);
        shear.setY(0.5);

        Polygon pentagon1 = new Polygon();
        pentagon1.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
        });
        pentagon1.setFill(Color.ORANGE);
        pentagon1.setStroke(Color.BLACK);

        Polygon pentagon2 = new Polygon();
        pentagon2.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
        });
        pentagon2.setFill(Color.TRANSPARENT);
        pentagon2.setStroke(Color.BLACK);
        pentagon2.getTransforms().addAll(shear);

        Group root = new Group(pentagon1, pentagon2);
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Shearing Example ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
