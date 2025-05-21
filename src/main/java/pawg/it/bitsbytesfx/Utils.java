package pawg.it.bitsbytesfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Control;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

final class Utils {
    private Utils() {}

    static int getClickCount(String text) {
        int clickCount = 0;
        Pattern digit = Pattern.compile("\\d+");

        Matcher matcher = digit.matcher(text);
        if (matcher.find()) {
            clickCount = Integer.parseInt(matcher.group());
        }
        return clickCount + 1;
    }

    static String getIconPath() {
        return switch (new Random().nextInt(4)) {
            case 0 -> "/img/minion_red.png";
            case 1 -> "/img/minion_dancing.png";
            case 2 -> "/img/minion_girl.png";
            default ->  "/img/pizza.png";
        };
    }

    static void animation(Control control, int cycleCount) {
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

    static Task<Void> getNewTask() {
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

    static List<Task<Void>> getTasks(int number) {
        return Stream.generate(Utils::getNewTask).limit(number).toList();
    }
}
