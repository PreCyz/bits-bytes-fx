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