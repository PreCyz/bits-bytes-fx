package pawg.it.bitsbytesfx;

import static org.testfx.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** CORTEX GENERATED THIS*/

@ExtendWith(ApplicationExtension.class)
class CortexGeneratedTest {

    /**
     * Set up the application before each test
     */
    @Start
    private void start(Stage stage) throws IOException {
        new BitsBytesMain().start(stage);
    }

    @Test
    void shouldInitializeWithCorrectDefaultValues(FxRobot robot) {
        // Verify label initial text
        Label regularLabel = robot.lookup("#regularLabel").queryAs(Label.class);
        assertThat(regularLabel).hasText("Watch here");

        // Verify text field initial text
        TextField textField = robot.lookup("#textField").queryAs(TextField.class);
        assertThat(textField).hasText("This is label value");

        // Verify progress indicator initial state
        ProgressIndicator progressIndicator = robot.lookup("#progressIndicator").queryAs(ProgressIndicator.class);
        assertThat(progressIndicator.getProgress()).isEqualTo(0.0);
    }

    @Test
    void whenVboxIsClicked_thenLabelTextUpdates(FxRobot robot) {
        // Given: initial state
        VBox vbox = robot.lookup("#vbox").queryAs(VBox.class);
        Label regularLabel = robot.lookup("#regularLabel").queryAs(Label.class);

        // When: VBox is clicked
        robot.clickOn(vbox);

        // Then: label text is updated with click count
        assertThat(regularLabel).hasText("VBox was clicked 1");

        // When: VBox is clicked again
        robot.clickOn(vbox);

        // Then: label text is updated with incremented click count
        assertThat(regularLabel).hasText("VBox was clicked 2");
    }

    @Test
    void whenBlockingButtonIsClicked_thenLabelAndProgressIndicatorUpdate(FxRobot robot) {
        // Given: initial state
        Button blockingButton = robot.lookup("Blocking").queryButton();
        Label regularLabel = robot.lookup("#regularLabel").queryAs(Label.class);
        ProgressIndicator progressIndicator = robot.lookup("#progressIndicator").queryAs(ProgressIndicator.class);

        // When: blocking button is clicked
        robot.clickOn(blockingButton);

        // Then: label text should change
        assertThat(regularLabel).hasText("Blocking button pressed");

        // And: progress indicator should be visible and complete (after task finishes)
        robot.sleep(5500); // Wait for task to complete
        assertThat(progressIndicator.isVisible()).isTrue();
        assertThat(progressIndicator.getProgress()).isEqualTo(1.0);
    }

    @Test
    void whenNonBlockingButtonIsClicked_thenLabelAndProgressIndicatorUpdate(FxRobot robot) {
        // Given: initial state
        Button nonBlockingButton = robot.lookup("#nonBlockingButton").queryButton();
        Label regularLabel = robot.lookup("#regularLabel").queryAs(Label.class);
        TextField textField = robot.lookup("#textField").queryAs(TextField.class);

        // When: non-blocking button is clicked
        robot.clickOn(nonBlockingButton);

        // Then: label text should change immediately
        assertThat(regularLabel).hasText("Non-Blocking button pressed");

        // And: text field should update after task completes
        robot.sleep(5500); // Wait for task to complete
        assertThat(textField).hasText("Async task done.");
    }

    @Test
    void textFieldShouldBeBoundToLabelText(FxRobot robot) {
        // Given: initial state
        Label regularLabel = robot.lookup("#regularLabel").queryAs(Label.class);
        TextField textField = robot.lookup("#textField").queryAs(TextField.class);

        // When: text field text is changed
        robot.clickOn(textField).eraseText(textField.getText().length()).write("New text value");

        // Then: label text should update
        assertThat(regularLabel).hasText("New text value");

        // And when: label text is changed by clicking VBox
        robot.clickOn("#vbox");

        // Then: text field should update with new label text
        assertThat(textField).hasText("VBox was clicked 1");
    }

    @Test
    void progressIndicatorShouldBeVisibleDuringTaskExecution(FxRobot robot) {
        // Given: initial state
        ProgressIndicator progressIndicator = robot.lookup("#progressIndicator").queryAs(ProgressIndicator.class);
        Button nonBlockingButton = robot.lookup("#nonBlockingButton").queryButton();

        // When: non-blocking button is clicked
        robot.clickOn(nonBlockingButton);

        // Then: progress indicator should be visible and updating
        assertThat(progressIndicator.isVisible()).isTrue();

        // Check progress is greater than 0 but less than 1
        robot.sleep(1000); // Wait a bit for progress to start
        double progress = progressIndicator.getProgress();
        assertThat(progress).isGreaterThan(0.0).isLessThan(1.0);
    }

    public void a(BigInteger number) {
        Optional<BigInteger> number1 = Optional.ofNullable(number);
    }

    @Test
    void name() {
    }
}