package tictactoe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultController extends Controller {
    @FXML
    Label label;
    private String ans;

    @FXML
    void onClicked(Event e){
        Button b = (Button) e.getSource();
        ans = b.getText();
        Stage stage = (Stage) b.getScene().getWindow();
        stage.close();
    }

    String getAns() {
        return ans;
    }

    void setWinner(char winner) {
        if (winner == ' ')
            label.setText("Draw");
        else
            label.setText(winner + " Wins");
    }
}