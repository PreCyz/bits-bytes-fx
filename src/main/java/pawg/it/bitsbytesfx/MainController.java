package pawg.it.bitsbytesfx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainController implements Initializable {
    @FXML
    public VBox vbox;
    @FXML
    private Label regularLabel;
    @FXML
    private Button nonBlockingButton;
    @FXML
    private TextField textField;
    @FXML
    private ProgressIndicator progressIndicator;

    private ExecutorService executor;
    private Service<Void> service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        initializeService();

        animation(regularLabel, Animation.INDEFINITE);
        textField.textProperty().bindBidirectional(regularLabel.textProperty());

        nonBlockingButton.setOnAction(event -> {
            progressIndicator.setVisible(true);
            progressIndicator.progressProperty().unbind();

            progressIndicator.progressProperty().bind(service.progressProperty());
            service.start();

            /*Task<Void> waitTask = getNewTask();
            progressIndicator.progressProperty().bind(waitTask.progressProperty());
            CompletableFuture
                    .runAsync(() -> Platform.runLater(() -> regularLabel.setText("Non-Blocking button pressed")), executor)
                    .thenRun(waitTask)
                    .thenRun(() -> Platform.runLater(() -> {
                        textField.setText("Async task done.");
                        animation(progressIndicator, 3);
                    }));*/
        });
    }

    private void initializeService() {
        service = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return getNewTask();
            }
        };
        service.setExecutor(executor);
        service.setOnScheduled(event -> regularLabel.setText("Non-Blocking button pressed"));
        service.setOnSucceeded(event -> {
            textField.setText("Async service done.");
            animation(progressIndicator, 3);
        });
    }

    private void animation(Control control, int cycleCount) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), evt -> control.setOpacity(0.8)),
                new KeyFrame(Duration.seconds(0.2), evt -> control.setOpacity(0.6)),
                new KeyFrame(Duration.seconds(0.3), evt -> control.setOpacity(0.4)),
                new KeyFrame(Duration.seconds(0.4), evt -> control.setOpacity(0.2)),
                new KeyFrame(Duration.seconds(0.5), evt -> control.setOpacity(0.1)),
                new KeyFrame(Duration.seconds(0.6), evt -> control.setOpacity(0.2)),
                new KeyFrame(Duration.seconds(0.7), evt -> control.setOpacity(0.4)),
                new KeyFrame(Duration.seconds(0.8), evt -> control.setOpacity(0.6)),
                new KeyFrame(Duration.seconds(0.9), evt -> control.setOpacity(0.8)),
                new KeyFrame(Duration.seconds(1), evt -> control.setOpacity(1)));
        timeline.setCycleCount(cycleCount);
        timeline.play();
        timeline.setOnFinished(evt -> Platform.runLater(() -> control.setVisible(false)));
    }

    Task<Void> getNewTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                try {
                    int i = 0;
                    do {
                        Thread.sleep(50);
                        i++;
                        updateMessage("Progress: %d seconds".formatted(i));
                        updateProgress(i, 100d);
                        /// show Manipulating an active scene graph from the call method throws runtime exceptions.
//                        regularLabel.setText("error " + i);
                    } while (i < 100);
                    updateMessage("All good!!!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
    }

    @FXML
    protected void onBlockingButtonClick() {
        Task<Void> waitTask = getNewTask();
        progressIndicator.setVisible(true);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(waitTask.progressProperty());
        regularLabel.setText("Blocking button pressed");
        waitTask.run();
    }

    @FXML
    protected void onVboxClicked() {
        regularLabel.setText("VBox was clicked " + getClickCount());
        Random random = new Random();
        regularLabel.setTextFill(Color.color(
                random.nextDouble(0.0, 1.0),
                random.nextDouble(0.0, 1.0),
                random.nextDouble(0.0, 1.0)
        ));
    }

    private int getClickCount() {
        int clickCount = 0;
        Pattern digit = Pattern.compile("\\d+");

        Matcher matcher = digit.matcher(regularLabel.getText());
        if (matcher.find()) {
            clickCount = Integer.parseInt(matcher.group());
        }
        return clickCount + 1;
    }
}