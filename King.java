import java.awt.Color;

public class King extends Piece {

    public King(Color team) {
        super(team);
        setText("King");
    }

    public boolean isMoveValid(int xMove, int yMove) {

        if (Math.abs(xMove) < 2 && Math.abs(yMove) < 2) {
            return true;
        } else {
            return false;
        }
    }
}
