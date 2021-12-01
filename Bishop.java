import java.awt.Color;

public class Bishop extends Piece {

    public Bishop(Color team) {
        super(team);
        setText("Bishop");
    }

    public boolean isMoveValid(int xMove, int yMove) {
        if (Math.abs(xMove) == Math.abs(yMove)) {
            if (Math.abs(xMove) <= 7) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
