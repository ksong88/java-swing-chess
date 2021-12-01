import java.awt.Color;

public class Knight extends Piece {

    public Knight(Color team) {
        super(team);
        setText("Knight");
    }

    public boolean isMoveValid(int xMove, int yMove) {

        if ((Math.abs(xMove) == 1 && Math.abs(yMove) == 2) || (Math.abs(xMove) == 2 && Math.abs(yMove) == 1)) {
            return true;
        } else {
            return false;
        }
    }
}
