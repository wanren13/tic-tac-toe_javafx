package tictactoe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController extends Controller {

    @FXML
    void onClicked(Event e) throws Exception{
        Button b = (Button) e.getSource();
        Game game = new Game(b.getId());

        Scene boardScene = SceneManager.getNewScene("board");
        BoardController boardController = (BoardController) boardScene.getUserData();
        boardController.setGame(game);
        Stage stage = (Stage) b.getScene().getWindow();
        stage.setTitle("Tic Tac Toe  -- " + game.getTurn() + "' turn.");
        stage.setScene(boardScene);
    }
}
