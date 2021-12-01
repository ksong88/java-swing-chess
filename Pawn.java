import java.awt.Color;

public class Pawn extends Piece {
    int moveCount = 0;

    public Pawn(Color team) {
        super(team);
        setText("Pawn");
    }

    public boolean isMoveValid(int xMove, int yMove) {

        // prevent moving backwards
        if (this.teamColor == Color.red) {
            if (moveCount == 0) {
                if (xMove == 0 && yMove >= -2 && yMove < 0) {
                    moveCount++;
                    System.out.printf("valid move for pawn\n");
                    return true;
                } else {
                    System.out.printf("invalid move for pawn\n");
                    return false;
                }
            } else if (xMove == 0 && yMove == -1) {
                moveCount++;
                System.out.printf("valid move for pawn\n");
                return true;
            } else {
                System.out.printf("invalid move for pawn\n");
                return false;
            }

        } else {
            if (moveCount == 0) {
                if (xMove == 0 && (yMove <= 2 && yMove > 0)) {
                    moveCount++;
                    System.out.printf("valid move for pawn\n");
                    return true;
                } else {
                    System.out.printf("invalid move for pawn\n");
                    return false;
                }
            } else if (xMove == 0 && yMove == 1) {
                System.out.printf("valid move for pawn\n");
                moveCount++;
                return true;
            } else {
                System.out.printf("invalid move for pawn\n");
                return false;
            }

        }

    }
}
