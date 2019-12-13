package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BadValueController{

    @FXML
    private Button closeButton;

    @FXML
    void handleCloseClick() {//closes ui
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
