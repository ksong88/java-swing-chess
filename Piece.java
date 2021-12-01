import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Piece extends JLabel {

    Color teamColor;

    public Piece(Color team) {
        teamColor = team;
        setOpaque(true);
        setForeground(team);
        setBackground(null);
        setText(null);
        setFont(new Font("Arial", Font.BOLD, 20));
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void selectPiece() {
        setBackground(new Color(0x49ab81));
    }

    public void deselectPiece() {
        setBackground(null);
    }

    public void setTeamColor(Color team) {
        teamColor = team;
        setForeground(team);
    }

    public boolean isMoveValid(int xMove, int yMove) {
        System.out.printf("Piece method called\n");
        return false;
    };

}
