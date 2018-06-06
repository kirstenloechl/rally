package menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    Label taskLabel;

    static final int SIGNUP_WIDTH = 320;
    static final int SIGNUP_HEIGHT = 300;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Unneeded

    }

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {

        Parent loginPageParent =  FXMLLoader.load(getClass().getResource("/fxml/FXMLDocument.fxml"));
        Scene loginPageScene = new Scene(loginPageParent, SIGNUP_WIDTH, SIGNUP_HEIGHT);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		appStage.setX((screenBounds.getWidth() - SIGNUP_WIDTH) / 2);
		appStage.setY((screenBounds.getHeight() - SIGNUP_HEIGHT) / 2);

        appStage.hide();
        appStage.setScene(loginPageScene);
        appStage.show();

    }

    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
    	Parent signupPageParent =  FXMLLoader.load(getClass().getResource("/fxml/FXMLInsertPage.fxml"));
        Scene signupPageScene = new Scene(signupPageParent);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        appStage.setX((screenBounds.getWidth() - SIGNUP_WIDTH) / 2);
      	appStage.setY((screenBounds.getHeight() - SIGNUP_HEIGHT) / 2);

      	appStage.hide();
        appStage.setScene(signupPageScene);
        appStage.show();
    }

}
