package pawg.it.bitsbytesfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

@ExtendWith(ApplicationExtension.class)
class BitsBytesMainTest {

    @Start
    private void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pizza.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(BitsBytesMain.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bits & Bytes");
        stage.setScene(scene);
        stage.show();
    }

    private <T extends Node> T getNode(Set<T> nodes, String id) {
        final LinkedList<T> result = nodes.stream()
                .filter(cb -> id.equals(cb.getId()))
                .collect(toCollection(LinkedList::new));
        return Optional.of(result)
                .map(LinkedList::getFirst)
                .orElseThrow(NoSuchElementException::new);
    }

    @Test
    void shouldContainControls_withTexts(FxRobot robot) {
        final Set<Button> buttons = robot.lookup(".button").queryAllAs(Button.class);
        final Set<Label> labels = robot.lookup(".label").queryAllAs(Label.class);
        final Set<ProgressIndicator> progressIndicators = robot.lookup("#progressIndicator").queryAllAs(ProgressIndicator.class);

        Button nonBlockingButton = getNode(buttons, "nonBlockingButton");
        Assertions.assertThat(nonBlockingButton).hasText("Non-blocking");

        Button blockingButton = buttons.stream().filter(b -> "Blocking".equals(b.getText())).findFirst().get();
        Assertions.assertThat(blockingButton).hasText("Blocking");

        Label label = getNode(labels, "regularLabel");
        Assertions.assertThat(label).hasText("Watch here");

        ProgressIndicator progressIndicator = getNode(progressIndicators, "progressIndicator");
        Assertions.assertThat(progressIndicator).isNotNull();
    }

    @Test
    void whenNonBlockingButtonIsClicked_thenLabelTextChanges(FxRobot robot) {
        // given:
        final Set<Button> buttons = robot.lookup(".button").queryAllAs(Button.class);
        Button nonBlockingButton = getNode(buttons, "nonBlockingButton");

        // when:
        robot.clickOn(nonBlockingButton);

        // then:
        final Set<Label> labels = robot.lookup(".label").queryAllAs(Label.class);
        Label label = getNode(labels, "regularLabel");
        Assertions.assertThat(label).hasText("Non-Blocking button pressed");
    }

    @Test
    void whenBlockingButtonIsClicked_thenLabelTextChanges(FxRobot robot) {
        // given:
        final Set<Button> buttons = robot.lookup(".button").queryAllAs(Button.class);
        Button blockingButton = buttons.stream().filter(b -> "Blocking".equals(b.getText())).findFirst().get();

        // when:
        robot.clickOn(blockingButton);

        // then:
        final Set<Label> labels = robot.lookup(".label").queryAllAs(Label.class);
        Label label = getNode(labels, "regularLabel");
        Assertions.assertThat(label).hasText("Blocking button pressed");
    }

}