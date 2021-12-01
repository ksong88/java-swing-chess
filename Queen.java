import java.awt.Color;

public class Queen extends Piece {

    public Queen(Color team) {
        super(team);
        setText("Queen");
    }

    public boolean isMoveValid(int xMove, int yMove) {

        if (Math.abs(xMove) == Math.abs(yMove)) {
            if (Math.abs(xMove) <= 7) {
                return true;
            }
            return false;
        } else {
            if ((Math.abs(xMove) <= 7 && Math.abs(yMove) == 0) || (Math.abs(xMove) == 0 && Math.abs(yMove) <= 7)) {
                return true;
            }
            return false;
        }
    }
}
