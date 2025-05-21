package pawg.it.bitsbytesfx;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.control.TaskProgressView;

import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

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
    @FXML
    private ProgressBar serviceProgressBar;
    @FXML
    private TaskProgressView<Task<Void>> taskProgressView;

    private ExecutorService executor;
    private Service<Void> service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        initializeService();

        Utils.animation(regularLabel, Animation.INDEFINITE);
        textField.textProperty().bindBidirectional(regularLabel.textProperty());

        taskProgressView.setGraphicFactory(t -> {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Utils.getIconPath())));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            return imageView;
        });

        nonBlockingButton.setOnAction(event -> {
            processTask();
            processService();
            processTaskProgressView();
        });
    }

    private void initializeService() {
        service = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return Utils.getNewTask();
            }
        };
        service.setExecutor(executor);
        service.setOnScheduled(event -> regularLabel.setText("Non-Blocking button pressed (service)"));
        service.setOnSucceeded(event -> {
            textField.setText("Async service done.");
            Utils.animation(serviceProgressBar, 3);
        });
    }

    private void processTask() {
        Task<Void> waitTask = Utils.getNewTask();
        progressIndicator.setVisible(true);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(waitTask.progressProperty());
        CompletableFuture
                .runAsync(() -> Platform.runLater(() -> regularLabel.setText("Non-Blocking button pressed")), executor)
                .thenRun(waitTask)
                .thenRun(() -> Platform.runLater(() -> {
                    textField.setText("Async task done.");
                    Utils.animation(progressIndicator, 3);
                }));
    }

    private void processService() {
        service.reset();
        serviceProgressBar.setVisible(true);
        serviceProgressBar.progressProperty().unbind();
        serviceProgressBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

    @SuppressWarnings("unchecked")
    private void processTaskProgressView() {
        taskProgressView.setVisible(true);

        List<Task<Void>> tasks = Utils.getTasks(3);
        taskProgressView.getTasks().addAll(tasks);

        CompletableFuture<Void>[] cfs = tasks.stream()
                .map(t -> CompletableFuture.runAsync(t, executor))
                .toArray(CompletableFuture[]::new);
        CompletableFuture<Void> allTaskCf = CompletableFuture.allOf(cfs)
                .whenCompleteAsync((result, throwable) -> Utils.animation(taskProgressView, 3));

        executor.submit(allTaskCf::join);
    }

    @FXML
    protected void onBlockingButtonClick() {
        Task<Void> waitTask = Utils.getNewTask();
        progressIndicator.setVisible(true);
        progressIndicator.progressProperty().unbind();
        progressIndicator.progressProperty().bind(waitTask.progressProperty());
        regularLabel.setText("Blocking button pressed");
        waitTask.run();
    }

    @FXML
    protected void onVboxClicked() {
        regularLabel.setText("VBox was clicked " + Utils.getClickCount(regularLabel.getText()));
        Random random = new Random();
        regularLabel.setTextFill(Color.color(
                random.nextDouble(0.0, 1.0),
                random.nextDouble(0.0, 1.0),
                random.nextDouble(0.0, 1.0)
        ));
    }
}