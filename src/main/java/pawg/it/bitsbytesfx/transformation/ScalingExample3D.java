package pawg.it.bitsbytesfx.transformation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ScalingExample3D extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing Sphere1
        Sphere sphere1 = new Sphere(100);
        sphere1.setTranslateX(200);
        sphere1.setTranslateY(150);

        //Drawing Circle2
        Sphere sphere2 = new Sphere(100);
        sphere2.setTranslateX(200);
        sphere2.setTranslateY(150);

        //Creating the scale transformation
        Scale scale = new Scale();

        //Setting the dimensions for the transformation
        scale.setX(0.5);
        scale.setY(0.5);

        //Setting the pivot point for the transformation
        scale.setPivotX(300);
        scale.setPivotY(150);

        //Adding the scale transformation to circle1
        sphere1.getTransforms().addAll(scale);

        //Creating a Group object
        Group root = new Group(sphere1, sphere2);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Scaling transformation example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
