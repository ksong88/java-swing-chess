import java.awt.Color;

public class Rook extends Piece {

    public Rook(Color team) {
        super(team);
        setText("Rook");
    }

    public boolean isMoveValid(int xMove, int yMove) {

        if ((Math.abs(xMove) <= 7 && Math.abs(yMove) == 0) || (Math.abs(xMove) == 0 && Math.abs(yMove) <= 7)) {
            return true;
        } else {
            return false;
        }
    }
}
