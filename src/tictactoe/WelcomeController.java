package tictactoe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeController extends Controller {

    @FXML
    void onClicked(Event e) throws Exception{
        Button b = (Button) e.getSource();
        Game game = new Game(b.getId());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("board.fxml"));
        Parent node = loader.load();
        BoardController boardController = loader.getController();
        Scene boardScene = new Scene(node, 450, 450);
        boardController.setGame(game);
        Stage stage = (Stage) b.getScene().getWindow();
        stage.setTitle("Tic Tac Toe  -- " + game.getTurn() + "' turn.");
        stage.setScene(boardScene);
    }
}
