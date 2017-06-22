import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

/**
 * Created by Guest_Account on 6/6/2017.
 */
public class SuperTicTacToe extends Scene{
    private int currentX, currentY, previousX, previousY;
    private boolean playAnywhere;
    private Player turn;
    private Board[][] boards;
    private ImageView playerTurn;
    private Board scoreboard;
    private boolean gameOver;
    private ImageView nextTurn;
    private Stage stage;

    /**
     *
     * @param root
     * @param list
     * @param screenWidth
     * @param screenHeight
     * @param stage
     * @throws FileNotFoundException
     *
     * Constructor
     * Creates all gui elements and provides functionality for the spaces on the board
     */
    public SuperTicTacToe(Parent root, final ObservableList list, final double screenWidth, final double screenHeight, Stage stage) throws FileNotFoundException {
        super(root);
        this.stage = stage;
        final Image board = new Image(getClass().getResourceAsStream("tac.png"));
        final ImageView mainBoard = new ImageView(board);
        mainBoard.setX(screenWidth * 0.2);
        mainBoard.setY(0);
        mainBoard.setFitWidth(screenWidth * 0.6);
        mainBoard.setFitHeight(screenHeight);
        list.add(mainBoard);

        playAnywhere = true;
        turn = Player.X;
        scoreboard = new Board();
        gameOver = false;
        boards = new Board[3][3];

        ImageView boardLabel = new ImageView(new Image(getClass().getResourceAsStream("turn.jpg")));
        boardLabel.setX(screenWidth * 0.85);
        boardLabel.setY(0);
        boardLabel.setFitWidth(screenWidth * 0.15);
        boardLabel.setFitHeight(screenWidth * 0.15);
        list.add(boardLabel);

        ImageView playerBorder = new ImageView(new Image(getClass().getResourceAsStream("player turn.jpg")));
        playerBorder.setX(0);
        playerBorder.setY(0);
        playerBorder.setFitWidth(screenWidth * 0.15);
        playerBorder.setFitHeight(screenWidth * 0.15);
        list.add(playerBorder);
        changePlayerTurnImage(screenWidth);
        list.add(playerTurn);

        Label score = new Label("Scoreboard");
        score.setLayoutX(screenWidth * 0.06);
        score.setLayoutY(screenWidth * 0.23);
        score.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        list.add(score);

        ImageView scoreBoard = new ImageView(board);
        ImageView scoreBorder = new ImageView(new Image(getClass().getResourceAsStream("scoreboard.jpg")));
        scoreBorder.setX(0);
        scoreBorder.setY(screenWidth * 0.23);
        scoreBorder.setFitWidth(screenWidth * 0.18);
        scoreBorder.setFitHeight(screenWidth * 0.18);
        list.add(scoreBorder);
        scoreBoard.setX(screenWidth * 0.026);
        scoreBoard.setY(screenWidth * 0.27);
        scoreBoard.setFitWidth(screenWidth * 0.125);
        scoreBoard.setFitHeight(screenWidth * 0.125);
        list.add(scoreBoard);

        //Draw the miniature boards in the main board
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                ImageView newView = new ImageView(board);
                newView.setX(screenWidth * 0.20 + j * screenWidth * 0.2);
                newView.setY(screenHeight * 0.0175 + screenHeight * 0.332 * i);
                newView.setFitWidth(screenWidth * 0.2);
                newView.setFitHeight(screenHeight * 0.3);
                list.add(newView);
                boards[i][j] = new Board();
            }
        }

        //Create the buttons and user interaction
        for(int h = 0; h < 3; h++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        final Button button = new Button();
                        button.setLayoutX(screenWidth * 0.21 + k * screenWidth * 0.0645 + i * screenWidth * 0.202);
                        button.setLayoutY(screenHeight * 0.03 + j * screenHeight * 0.1 + h * screenHeight * 0.33);
                        button.setPrefWidth(screenWidth * 0.05);
                        button.setPrefHeight(screenHeight * 0.08);
                        button.setStyle("-fx-background-color: transparent");
                        list.add(button);

                        //Adding the sprites
                        final int finalK = k;
                        final int finalJ = j;
                        final int finalH = h;
                        final int finalI = i;
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent event) {
                                currentX = finalI;
                                currentY = finalH;
                                addSprite(screenWidth, finalK, finalI, screenHeight, finalJ, finalH, list);
                                if (!gameOver){
                                    list.remove(playerTurn);
                                    changePlayerTurnImage(screenWidth);
                                    list.add(playerTurn);
                                    if (!playAnywhere){
                                        list.remove(nextTurn);
                                    }
                                    changeNextTurn(screenWidth);
                                    list.add(nextTurn);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private void addSprite(final double screenWidth, int k, int i, final double screenHeight, int j, int h, final ObservableList list){
        Board currBoard = boards[previousY][previousX];
        if (currBoard.isBoardFull()){
            playAnywhere = true;
        }
        if (playAnywhere || (currentX == previousX && currentY == previousY)){
            ImageView sprite = new ImageView(turn.getImage(screenWidth, screenHeight));
            sprite.setX(screenWidth * 0.22 + k * screenWidth * 0.0625 + i * screenWidth * 0.2);
            sprite.setY(screenHeight * 0.045 + j * screenHeight * 0.1 + h * screenHeight * 0.33);
            sprite.setFitWidth(screenWidth * 0.2/6.75);
            sprite.setFitHeight(screenHeight * 0.3/6);
            list.add(sprite);
            currBoard.getGameBoard()[j][k] = turn.getCharacter();
            playAnywhere = false;
            previousX = k;
            previousY = j;
            currBoard.update(turn);
            if (currBoard.getBoardWon()){
                ImageView boardWinner = new ImageView(currBoard.getWinner().getImage(screenWidth, screenWidth));
                boardWinner.setX(screenWidth * 0.035 + screenWidth * 0.04 * i);
                boardWinner.setY(screenWidth * 0.2755 + screenWidth * 0.0425 * h);
                list.add(boardWinner);
                scoreboard.getGameBoard()[h][i] = currBoard.getWinner().getCharacter();
                scoreboard.update(turn);
                if (scoreboard.getBoardWon()){
                    list.clear();
                    ImageView victory = new ImageView(scoreboard.getWinner().getVictory(screenWidth, screenHeight));
                    victory.setX(0);
                    victory.setY(0);
                    list.add(victory);
                    gameOver = true;
                    final Group newGroup = new Group();
                    SuperTicTacToe.this.setOnMouseReleased(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            stage.close();
                            Stage newStage = new Stage();
                            try {
                                newStage.setScene(new TitleScreen(newGroup, screenWidth, screenHeight, newGroup.getChildren(), newStage));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            newStage.setFullScreen(true);
                            newStage.show();
                        }
                    });
                }
            }
            switchPlayer();
        }
    }

    private void changeNextTurn(double screenWidth){
        nextTurn = new ImageView(boards[0][0].getPositions().get(previousY).get(previousX).getAbbreviation());
        nextTurn.setX(screenWidth * 0.88);
        nextTurn.setY(screenWidth * 0.03);
        nextTurn.setFitWidth(screenWidth * 0.1);
        nextTurn.setFitHeight(screenWidth * 0.1);
    }

    private void changePlayerTurnImage(double screenWidth){
        playerTurn = new ImageView(turn.getImage(screenWidth * 3 , screenWidth * 3));
        playerTurn.setX(screenWidth * 0.03);
        playerTurn.setY(screenWidth * 0.0425);
    }

    private void switchPlayer(){
        if (turn == Player.X){
            turn = Player.O;
        }else {
            turn = Player.X;
        }
    }
}
