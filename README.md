--add-modules=javafx.swing,javafx.graphics,javafx.fxml,javafx.media,javafx.web --add-reads javafx.graphics=ALL-UNNAMED 
--add-opens javafx.controls/com.sun.javafx.charts=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED 
--add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED
--add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
--add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED

For unit tests these VM arguments must be set:
```jvm
-ea --add-modules=javafx.graphics,javafx.fxml --add-reads javafx.graphics=ALL-UNNAMED --add-opens javafx.controls/com.sun.javafx.charts=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.application=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED
```

## For the presentation show:
1. Class with `main`
   1. Themes
   2. Window icon
   3. Loading main.xml
   4. Exit event

2. MainController
   1. @FXML + SceneBuilder
   2. Loading controller
   3. Initialization
   4. Blocking UI
   5. Property binding
   6. Executors