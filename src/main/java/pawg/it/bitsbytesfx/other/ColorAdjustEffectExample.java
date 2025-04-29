package pawg.it.bitsbytesfx.other;

import java.util.Objects;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ColorAdjustEffectExample extends Application {
    @Override
    public void start(Stage stage) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.4);
        colorAdjust.setHue(-0.05);
        colorAdjust.setBrightness(0.9);
        colorAdjust.setSaturation(0.8);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pizza.png")));
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(70);
        imageView.setFitHeight(200);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setEffect(colorAdjust);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Color adjust effect example");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
