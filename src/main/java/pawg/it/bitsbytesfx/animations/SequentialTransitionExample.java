package pawg.it.bitsbytesfx.animations;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SequentialTransitionExample extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle();
        circle.setCenterX(150.0f);
        circle.setCenterY(135.0f);
        circle.setRadius(100.0f);
        circle.setFill(Color.BROWN);
        circle.setStrokeWidth(20);

        Path path = new Path(
                new MoveTo(100, 150),
                new CubicCurveTo(400, 40, 175, 250, 500, 150)
        );

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setNode(circle);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(circle);
        translateTransition.setByX(-400);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);

        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(2000));
        scaleTransition.setNode(circle);
        scaleTransition.setByY(-0.4);
        scaleTransition.setByX(-0.4);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);

        ScaleTransition scaleTransitionDown = new ScaleTransition();
        scaleTransitionDown.setDuration(Duration.millis(2000));
        scaleTransitionDown.setNode(circle);
        scaleTransitionDown.setByY(0.4);
        scaleTransitionDown.setByX(0.4);
        scaleTransitionDown.setCycleCount(1);
        scaleTransitionDown.setAutoReverse(false);

        SequentialTransition sequentialTransition = new SequentialTransition(
                circle,
                pathTransition, translateTransition, scaleTransition, scaleTransitionDown
        );
        sequentialTransition.setCycleCount(1000);
        sequentialTransition.play();

        Group root = new Group(circle);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Sequential transition example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
