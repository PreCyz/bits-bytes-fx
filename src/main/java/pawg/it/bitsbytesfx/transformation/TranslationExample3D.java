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
        Cylinder cy1 = new Cylinder(50, 100);
        Cylinder cy2 = new Cylinder(50, 100);

        //Creating the translation transformation
        Translate translate = new Translate();

        //Setting the X,Y,Z coordinates to apply the translation
        translate.setX(200);
        translate.setY(150);
        translate.setZ(100);

        //Adding transformation to circle2
        cy2.getTransforms().addAll(translate);

        //Creating a Group object
        Group root = new Group(cy1,cy2);

        //Creating a scene object
        Scene scene = new Scene(root, 400, 300);

        //Setting title to the Stage
        stage.setTitle("Translation transformation example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
