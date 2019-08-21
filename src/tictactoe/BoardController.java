package tictactoe;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController extends Controller implements Initializable {

    @FXML
    private GridPane gridPane;
    private Stage stage;
    private Game game;
    private ImageView[][] imageViews = new ImageView[3][3];

    @FXML
    void onClicked(Event e) throws Exception{
        ImageView imageView = (ImageView) e.getSource();
        Integer x = GridPane.getRowIndex(imageView);
        Integer y = GridPane.getColumnIndex(imageView);
        if (game.isValid(x, y)) {
            game.move(x, y);
            setImage(imageView, game.getTurn());
            game.switchTurn();
            if (game.getMode().equalsIgnoreCase("computer")) {
                int[] pos = game.computerMove();
                if (pos != null) {
                    x = pos[0]; y = pos[1];
                    setImage(imageViews[x][y], game.getTurn());
                    game.switchTurn();
                }
            }

            stage = (Stage) gridPane.getScene().getWindow();
            stage.setTitle("Tic Tac Toe  -- " + game.getTurn() + "' turn.");
        }
        game.printBoard();
        if (game.isEnd() || game.isWin()) {
            Stage popup = new Stage();
            popup.initStyle(StageStyle.UNDECORATED);
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.setTitle("Conform");
            Scene resultScene = SceneManager.getScene("result", 300, 250);
            ResultController resultController = (ResultController) resultScene.getUserData();
            resultController.setWinner(game.getWinner());
            popup.setScene(resultScene);
            popup.showAndWait();
            String ans = resultController.getAns();
            if (ans.equalsIgnoreCase("play again")) {
                Scene welcomeScene = SceneManager.getScene("welcome");
                stage.setScene(welcomeScene);
            }
            else stage.close();
        }
    }

    void setGame(Game game) {
        this.game = game;
    }

    void setImage(ImageView imageView, char c) {
        String path_o = "resources/images/o.png";
        String path_x = "resources/images/x.png";
        File file;
        if (c == 'X')
            file = new File(path_x);
        else
            file = new File(path_o);

        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                int x = GridPane.getRowIndex(node);
                int y = GridPane.getColumnIndex(node);
                imageViews[x][y] = (ImageView) node;
            }
        }
    }
}