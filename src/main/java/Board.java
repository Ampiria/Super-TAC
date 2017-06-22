import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest_Account on 6/6/2017.
 */
public class Board {
    private List<ArrayList<BoardPositions>> positions = new ArrayList<ArrayList<BoardPositions>>();
    private List<BoardPositions> xSquares = new ArrayList();
    private List<BoardPositions> oSquares = new ArrayList();
    private String[][] gameBoard;
    private boolean boardWon;
    private Player winner;
    private boolean previouslyWon = false;

    public Board(){
        int place = 0;
        for (int i = 0; i < 3; i++){
            positions.add(new ArrayList<BoardPositions>());
            for (int j = 0; j < 3; j++){
                positions.get(i).add(BoardPositions.values()[place]);
                place++;
            }
        }
        boardWon = false;
        gameBoard = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                gameBoard[i][j] = "";
            }
        }
    }


    public List getoSquares() {
        return oSquares;
    }

    public List getxSquares() {
        return xSquares;
    }

    public Player getWinner() {
        return winner;
    }

    public boolean getBoardWon(){
        return boardWon;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setBoardWon(boolean boardWon) {
        this.boardWon = boardWon;
    }

    public List<ArrayList<BoardPositions>> getPositions() {
        return positions;
    }

    public boolean isBoardFull(){
        for(int i = 0; i < 3; i++){
            for (String player : gameBoard[i]) {
                if(player == ""){
                    return false;
                }
            }
        }
        return true;
    }

    public void update(Player player){
        checkForWin(player);
    }

    private boolean checkForWin(Player player){
        if ((checkRowsForWin() || checkColumnsForWin()|| checkDiagonalsForWin()) && !previouslyWon){
            setBoardWon(true);
            setWinner(player);
            previouslyWon = true;
            return true;
        }
        return false;
    }

    private boolean checkRowsForWin(){
        for (int i = 0; i < 3; i++){
            if(checkPosition(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2])){
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin(){
        for (int i = 0; i < 3; i++){
            if(checkPosition(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin(){
        return (checkPosition(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]) ||
                checkPosition(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]));
    }

    private boolean checkPosition(String a, String b, String c){
        return (a != "" && a == b && b == c);
    }
}
