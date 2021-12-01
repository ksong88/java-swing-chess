import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;

public class Square extends JPanel {

    String colorOFSquare;
    Piece currentPiece = null;
    String position;

    public Square(String color, String pos) {

        setOpaque(true);
        setLayout(new BorderLayout());

        if (color == "black") {
            setBackground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
        }

        colorOFSquare = color;
        position = pos;

        setUpPieces();
    }

    public void setUpPieces() {

        switch (position.charAt(0)) {
        case 'A':
            if (position.charAt(1) == '0' || position.charAt(1) == '7') {
                currentPiece = new Rook(Color.red);
            } else if (position.charAt(1) == '1' || position.charAt(1) == '6') {
                currentPiece = new Knight(Color.red);
            } else if (position.charAt(1) == '2' || position.charAt(1) == '5') {
                currentPiece = new Bishop(Color.red);
            } else if (position.charAt(1) == '3') {
                currentPiece = new King(Color.red);
            } else {
                currentPiece = new Queen(Color.red);
            }
            add(currentPiece);
            break;

        case 'B':
            currentPiece = new Pawn(Color.red);
            add(currentPiece);
            break;

        case 'G':
            currentPiece = new Pawn(Color.blue);
            add(currentPiece);
            break;

        case 'H':
            if (position.charAt(1) == '0' || position.charAt(1) == '7') {
                currentPiece = new Rook(Color.blue);
            } else if (position.charAt(1) == '1' || position.charAt(1) == '6') {
                currentPiece = new Knight(Color.blue);
            } else if (position.charAt(1) == '2' || position.charAt(1) == '5') {
                currentPiece = new Bishop(Color.blue);
            } else if (position.charAt(1) == '3') {
                currentPiece = new King(Color.blue);
            } else {
                currentPiece = new Queen(Color.blue);
            }
            add(currentPiece);
            break;

        default:
            Piece piece = new Piece(null);
            add(piece);
            currentPiece = null;
            break;
        }
    }

    public void addPiece(Piece piece) {
        this.add(piece);
        currentPiece = null;
    }

    public void removePiece() {
        this.removeAll();
        Piece piece = new Piece(null);
        add(piece);
        this.revalidate();
        this.repaint();
        currentPiece = null;
    }

    public String getPieceName() {
        return currentPiece.getText();
    }

    public Color getTeamColor() {
        return currentPiece.getForeground();
    }

    public String getPosition() {
        return position;
    }

}
