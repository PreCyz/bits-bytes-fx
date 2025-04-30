package pawg.it.bitsbytesfx.other;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.*;

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

        Collection<Node> nodes = new ArrayList<>(createImageViews(colorAdjust));
        nodes.addAll(createSlidersAndLabels(colorAdjust));

        Group root = new Group(nodes.toArray(Node[]::new));

        Scene scene = new Scene(root, 600, 300);

        stage.setTitle("Color adjust effect example");
        stage.setScene(scene);
        stage.show();
    }

    private Collection<ImageView> createImageViews(ColorAdjust colorAdjust) {
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

        return List.of(imageView, originalImageView);
    }

    private Collection<Node> createSlidersAndLabels(final ColorAdjust colorAdjust) {
        final Slider contrastSlider = createSlider(colorAdjust.getContrast());
        contrastSlider.setLayoutX(400);
        colorAdjust.contrastProperty().bind(contrastSlider.valueProperty());
//        contrastSlider.valueProperty().bind(colorAdjust.contrastProperty());
//        contrastSlider.valueProperty().bindBidirectional(colorAdjust.contrastProperty());
        /*contrastSlider.valueProperty().addListener(
                (observable, oldValue, newValue) -> colorAdjust.setContrast(newValue.doubleValue())
        );*/

        final Slider hueSlider = createSlider(colorAdjust.getHue());
        colorAdjust.hueProperty().bind(hueSlider.valueProperty());
        hueSlider.setLayoutX(450);

        final Slider brightnessSlider = createSlider(colorAdjust.getBrightness());
        colorAdjust.brightnessProperty().bind(brightnessSlider.valueProperty());
        brightnessSlider.setLayoutX(500);

        final Slider saturationSlider = createSlider(colorAdjust.getSaturation());
        colorAdjust.saturationProperty().bind(saturationSlider.valueProperty());
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
