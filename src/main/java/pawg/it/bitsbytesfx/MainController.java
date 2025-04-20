package pawg.it.bitsbytesfx;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class MainController implements Initializable {
    @FXML
    private Label regularLabel;
    @FXML
    private Button nonBlockingButton;
    @FXML
    private TextField textField;
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    protected void onBlockingButtonClick() {
        Task<Void> waitTask = getNewTask();
        progressIndicator.setVisible(true);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(waitTask.progressProperty());
        regularLabel.setText("Blocking button pressed");
        waitTask.run();
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
                    } while (i < 100);
                    updateMessage("All good!!!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animation(regularLabel, Animation.INDEFINITE);
        textField.textProperty().bindBidirectional(regularLabel.textProperty());
        nonBlockingButton.setOnAction(event -> {
            Task<Void> waitTask = getNewTask();
            progressIndicator.setVisible(true);
            progressIndicator.progressProperty().unbind();
            progressIndicator.progressProperty().bind(waitTask.progressProperty());
            CompletableFuture
                    .runAsync(() -> Platform.runLater(() -> regularLabel.setText("Non-Blocking button pressed")))
                    .thenRun(waitTask)
                    .thenRun(() -> Platform.runLater(() -> {
                        textField.setText("Async task done.");
                        animation(progressIndicator, 3);
                    }));
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
}