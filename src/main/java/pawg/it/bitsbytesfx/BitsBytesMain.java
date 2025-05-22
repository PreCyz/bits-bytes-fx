package pawg.it.bitsbytesfx;

import atlantafx.base.theme.CupertinoDark;
import atlantafx.base.theme.CupertinoLight;
import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import atlantafx.base.theme.Theme;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Optional<Theme> themeOpt = getTheme();
        stage.getIcons().add(Utils.readImage("/img/pizza.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(BitsBytesMain.class.getResource("main.fxml"));
        if (themeOpt.isPresent()) {
            Application.setUserAgentStylesheet(themeOpt.get().getUserAgentStylesheet());
            stage.setScene(new Scene(fxmlLoader.load(), 280, 535));
        } else {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        stage.setTitle("Bits & Bytes");
        stage.setOnCloseRequest(event -> System.exit(0));
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