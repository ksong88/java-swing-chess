import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.GridLayout;

public class Board extends JFrame implements MouseListener {

    int squareWidth;
    int squareHeight;
    String pos;
    Square currentSquare;
    Color currentTeam = Color.blue;
    Square boardSquares[][];

    public Board(int width, int height, int rows, int columns) {
        super();
        this.squareWidth = width;
        this.squareHeight = height;
        this.setTitle("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width * columns, height * rows);
        this.setResizable(false);
        this.setLayout(new GridLayout(rows, columns));
        this.boardSquares = new Square[rows][columns];

        for (Character i = 'A'; i <= 'H'; i++) {
            for (Integer j = 0; j < 8; j++) {

                if ((i + j) % 2 == 0) {
                    pos = i.toString() + j.toString();
                    boardSquares[j][i - 'A'] = new Square("black", pos);
                    boardSquares[j][i - 'A'].setSize(squareWidth, squareHeight);
                    boardSquares[j][i - 'A'].addMouseListener(this);
                    this.add(boardSquares[j][i - 'A']);

                } else {
                    pos = i.toString() + j.toString();
                    boardSquares[j][i - 'A'] = new Square("white", pos);
                    boardSquares[j][i - 'A'].setSize(squareWidth, squareHeight);
                    boardSquares[j][i - 'A'].addMouseListener(this);
                    this.add(boardSquares[j][i - 'A']);
                }
            }
        }
        this.setVisible(true);
    }

    // if current turn is true, only the current team's pieces can move,
    public boolean isCurrentTurn(Color teamColor, Color currentTeam) {
        if (teamColor == currentTeam) {
            return true;
        }
        return false;
    }

    public boolean isPathClear(Square currentSquare, Square clickedSquare) {

        char currentY = currentSquare.getPosition().charAt(0);
        char currentX = currentSquare.getPosition().charAt(1);
        char destinationY = clickedSquare.getPosition().charAt(0);
        char destinationX = clickedSquare.getPosition().charAt(1);

        int xStart = currentX - '0';
        int xEnd = destinationX - '0';
        int yStart = currentY - 'A';
        int yEnd = destinationY - 'A';

        int xMove = xStart - xEnd;
        int yMove = yStart - yEnd;

        // xMove is negative (going from A to D) 0 - 3 = -3 (down movement)
        // xMove is positive (going from D to A) 3 - 0 = 3 (up movement)

        // yMove is negative (going from 1 to 3) 1 - 3 = -2 (right movement)
        // yMove is positive (going from 3 to 1) 3 - 1 = 2 (left movement)

        // when it's a diagonal move path
        if (Math.abs(xMove) == Math.abs(yMove)) {

            // when both moves are positive
            if (xMove > 0 && yMove > 0) {
                for (int index = 1; index <= Math.abs(xMove); index++) {
                    Square squareInPath = boardSquares[xStart - index][yStart - index];
                    if (squareInPath.currentPiece != null) {
                        return false;
                    }
                }
                return currentSquare.currentPiece.isMoveValid(xMove, yMove);

                // when xmove is positive, ymove is negative
            } else if (xMove > 0 && yMove < 0) {
                for (int index = 1; index <= Math.abs(xMove); index++) {
                    Square squareInPath = boardSquares[xStart - index][yStart + index];
                    if (squareInPath.currentPiece != null) {
                        return false;
                    }
                }
                return currentSquare.currentPiece.isMoveValid(xMove, yMove);

                // when xmove is negative, ymove is positive
            } else if (xMove < 0 && yMove > 0) {
                for (int index = 1; index <= Math.abs(xMove); index++) {
                    Square squareInPath = boardSquares[xStart + index][yStart - index];
                    if (squareInPath.currentPiece != null) {
                        return false;
                    }
                }
                return currentSquare.currentPiece.isMoveValid(xMove, yMove);

                // when xmove is negative, ymove is negative
            } else {
                for (int index = 1; index <= Math.abs(xMove); index++) {
                    Square squareInPath = boardSquares[xStart + index][yStart + index];
                    if (squareInPath.currentPiece != null) {
                        return false;
                    }
                }
                return currentSquare.currentPiece.isMoveValid(xMove, yMove);
            }

            // when its horizontal or vertical move path
        } else if (xMove > 0 && yMove == 0) {

            for (int index = 1; index <= Math.abs(xMove); index++) {
                Square squareInPath = boardSquares[xStart - index][yStart];
                if (squareInPath.currentPiece != null) {
                    return false;
                }
            }
            return currentSquare.currentPiece.isMoveValid(xMove, yMove);

        } else if (xMove < 0 && yMove == 0) {

            for (int index = 1; index <= Math.abs(xMove); index++) {
                Square squareInPath = boardSquares[xStart + index][yStart];
                if (squareInPath.currentPiece != null) {
                    return false;
                }
            }
            return currentSquare.currentPiece.isMoveValid(xMove, yMove);

        } else if (yMove > 0 && xMove == 0) {
            for (int index = 1; index <= Math.abs(yMove); index++) {
                Square squareInPath = boardSquares[xStart][yStart - index];
                if (squareInPath.currentPiece != null) {
                    return false;
                }
                System.out.printf("nextsquareinpath position : %s\n", squareInPath.position);
            }
            return currentSquare.currentPiece.isMoveValid(xMove, yMove);

        } else if (yMove < 0 && xMove == 0) {
            for (int index = 1; index <= Math.abs(yMove); index++) {
                Square squareInPath = boardSquares[xStart][yStart + index];
                if (squareInPath.currentPiece != null) {
                    return false;
                }
            }
            return currentSquare.currentPiece.isMoveValid(xMove, yMove);

            // for knight's moves, the only case where the move is irregular
        } else {
            return currentSquare.currentPiece.isMoveValid(xMove, yMove);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Square square = (Square) e.getComponent();

        System.out.printf("clicked square: %s\n", square.getPosition());

        // current square is empty and clicked square is empty
        // (no selected piece)
        if (currentSquare == null && square.currentPiece == null) {
            currentSquare = null;

            // current square is empty and clicked square has a piece
            // (select piece)
        } else if (currentSquare == null && square.currentPiece != null) {

            if (isCurrentTurn(square.getTeamColor(), currentTeam)) {
                currentSquare = square;
                currentSquare.currentPiece.selectPiece();
            }

            // current square has a piece and clicked square is empty
            // (move to empty square)
        } else if (square.currentPiece == null) {
            if (isPathClear(currentSquare, square)) {
                currentSquare.currentPiece.deselectPiece();
                square.removePiece();
                square.addPiece(currentSquare.currentPiece);
                square.currentPiece = currentSquare.currentPiece;
                if (currentSquare.getTeamColor() == Color.blue) {
                    currentTeam = Color.red;
                } else {
                    currentTeam = Color.blue;
                }
                currentSquare.removePiece();
                currentSquare = null;
            }

            // current square has a piece and clicked square has a piece
        } else {
            // current square is same as clicked square then cancel selection
            // (deselect current piece)
            if (currentSquare.getPosition() == square.getPosition()) {
                currentSquare.currentPiece.deselectPiece();
                currentSquare = null;

                // current square piece team is same as clicked square piece team
                // (invalid move)
            } else if (currentSquare.currentPiece.teamColor == square.getTeamColor()) {
                currentSquare.currentPiece.selectPiece();

                // current square piece team is different from clicked square piece team
                // (replace piece)
            } else {

                if (isPathClear(currentSquare, square)) {
                    currentSquare.currentPiece.deselectPiece();
                    square.removePiece();
                    square.addPiece(currentSquare.currentPiece);
                    square.currentPiece = currentSquare.currentPiece;
                    if (currentSquare.getTeamColor() == Color.blue) {
                        currentTeam = Color.red;
                    } else {
                        currentTeam = Color.blue;
                    }
                    currentSquare.removePiece();
                    currentSquare = null;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
