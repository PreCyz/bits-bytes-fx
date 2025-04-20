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
        Polygon pentagon1 = new Polygon();

        //Adding coordinates to the pentagon
        pentagon1.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
        });
        //Setting the fill color for the pentagon
        pentagon1.setFill(Color.ORANGE);

        pentagon1.setStroke(Color.BLACK);
        Polygon pentagon2 = new Polygon();

        //Adding coordinates to the pentagon
        pentagon2.getPoints().addAll(new Double[]{
                200.0, 50.0,
                400.0, 50.0,
                450.0, 150.0,
                400.0, 250.0,
                200.0, 250.0,
        });
        //Setting the fill color for the pentagon
        pentagon2.setFill(Color.TRANSPARENT);
        pentagon2.setStroke(Color.BLACK);

        //Creating shear transformation
        Shear shear = new Shear();

        //Setting the pivot points
        shear.setPivotX(200);
        shear.setPivotY(250);

        //Setting the dimensions for the shear
        shear.setX(0.0);
        shear.setY(0.5);

        //Adding the transformation to the polygon
        pentagon2.getTransforms().addAll(shear);

        //Creating a Group object
        Group root = new Group(pentagon1, pentagon2);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Shearing Example ");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
