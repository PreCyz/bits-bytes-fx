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
        Scale scale = new Scale();
        scale.setX(0.5);
        scale.setY(0.5);
        scale.setPivotX(300);
        scale.setPivotY(150);

        Sphere sphere1 = new Sphere(100);
        sphere1.setTranslateX(200);
        sphere1.setTranslateY(150);

        sphere1.getTransforms().addAll(scale);

        Sphere sphere2 = new Sphere(100);
        sphere2.setTranslateX(200);
        sphere2.setTranslateY(150);

        Group root = new Group(sphere1, sphere2);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Scaling transformation example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
