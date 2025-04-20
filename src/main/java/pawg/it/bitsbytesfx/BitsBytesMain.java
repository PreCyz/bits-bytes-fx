package pawg.it.bitsbytesfx;

import atlantafx.base.theme.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class BitsBytesMain extends Application {

    private static String THEME = "default";

    public static void main(String[] args) {
        if (args.length > 0) {
            THEME = args[0];
        }
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        getTheme().ifPresent(theme -> Application.setUserAgentStylesheet(theme.getUserAgentStylesheet()));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/pizza.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(BitsBytesMain.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bits & Bytes");
        stage.setScene(scene);
        stage.show();
    }

    Optional<Theme> getTheme() {
        return Optional.ofNullable(switch (THEME) {
            case "primer" -> new PrimerLight();
            case "primerDark" -> new PrimerDark();
            case "nord" -> new NordLight();
            case "nordDark" -> new NordDark();
            case "dracula" -> new Dracula();
            case "cupertino" -> new CupertinoLight();
            case "cupertinoDark" -> new CupertinoDark();
            default -> null;
        });
    }
}