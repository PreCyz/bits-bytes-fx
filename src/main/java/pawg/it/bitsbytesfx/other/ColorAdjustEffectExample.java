package pawg.it.bitsbytesfx.other;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ColorAdjustEffectExample extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.6);
        colorAdjust.setHue(-0.05);
        colorAdjust.setBrightness(0.09);
        colorAdjust.setSaturation(-0.8);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pizza.png")));
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(70);
        imageView.setFitHeight(200);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setEffect(colorAdjust);

        ImageView originalImageView = new ImageView(image);
        originalImageView.setX(50);
        originalImageView.setY(1);
        originalImageView.setFitHeight(100);
        originalImageView.setFitWidth(200);
        originalImageView.setPreserveRatio(true);

        Collection<Node> nodes = createSlidersAndLabels(colorAdjust);

        Group root = new Group(imageView, originalImageView);
        root.getChildren().addAll(nodes);

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Color adjust effect example");
        stage.setScene(scene);
        stage.show();
    }

    private Collection<Node> createSlidersAndLabels(final ColorAdjust colorAdjust) {
        final Slider contrastSlider = createSlider(colorAdjust.getContrast());
        contrastSlider.setLayoutX(400);
        contrastSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> colorAdjust.setContrast(newValue.doubleValue())
        );

        final Slider hueSlider = createSlider(colorAdjust.getHue());
        hueSlider.valueProperty().bindBidirectional(colorAdjust.hueProperty());
        hueSlider.setLayoutX(450);

        final Slider brightnessSlider = createSlider(colorAdjust.getBrightness());
        brightnessSlider.valueProperty().bindBidirectional(colorAdjust.brightnessProperty());
        brightnessSlider.setLayoutX(500);

        final Slider saturationSlider = createSlider(colorAdjust.getSaturation());
        saturationSlider.valueProperty().bindBidirectional(colorAdjust.saturationProperty());
        saturationSlider.setLayoutX(550);

        return List.of(
                contrastSlider, createLabel(contrastSlider, "C"),
                hueSlider, createLabel(hueSlider, "H"),
                brightnessSlider, createLabel(brightnessSlider, "B"),
                saturationSlider, createLabel(saturationSlider, "S")
        );
    }

    private Slider createSlider(double initValue) {
        Slider slider = new Slider(-1, 1, initValue);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setBlockIncrement(0.01);
        slider.setOrientation(Orientation.VERTICAL);
        slider.setMajorTickUnit(0.5);
        slider.setLayoutY(10);
        return slider;
    }

    private Label createLabel(Slider slider, String text) {
        Label label = new Label();
        label.setText(String.format("%f", slider.getValue()));
        label.textProperty().bind(Bindings.format("%s: %.2f", text, slider.valueProperty()));
        label.setLabelFor(slider);
        label.setLayoutX(slider.getLayoutX() - 5);
        label.setLayoutY(155);
        return label;
    }
}
