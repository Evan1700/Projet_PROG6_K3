import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerPanel extends JPanel implements MouseListener {
    Controller controller;
    Game game;
    int playerNumber;
    int width,height;
    int squareSize;

    public PlayerPanel(Controller controller)
    {
        this.controller = controller;
        this.game = controller.game;
        playerNumber = game.actifPlayer;
        addMouseListener(this);
        width = this.getWidth();
        height = this.getHeight();
        int widthRect = width/8;
        int heightRect = height*(2/3);
        squareSize = Math.min(widthRect,heightRect);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int x = squareSize;
        int y = height*(1/6);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6-i; j++) {
                x = x + squareSize;

                g.setColor(Color.Black);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(game.playersPyramid[playerNumber][i][j]);
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);
            }
            x = ((7-i)*squareSize) + (squareSize/2);
            y = y+squareSize;
        }

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(!((mouseX>squareSize*7||mouseX<squareSize) && (mouseY<(1/6)*height || mouseY>((1/6)*height)+squareSize))) controller.play((int)(mouseX-squareSize)/6*squareSize, 0, playerNumber);
        if(!((mouseX>squareSize*7-(squareSize/2)||mouseX<squareSize+(squareSize/2)) && (mouseY<(1/6)*height+squareSize || mouseY>((1/6)*height)+2*squareSize))) controller.play((int)(mouseX-(1.5*squareSize))/5*squareSize, 1, playerNumber);
        if(!((mouseX>squareSize*7-2*(squareSize/2)||mouseX<squareSize+2*(squareSize/2)) && (mouseY<(1/6)*height+2*squareSize || mouseY>((1/6)*height)+3*squareSize))) controller.play((int)(mouseX-(2*squareSize))/4*squareSize, 2, playerNumber);
        if(!((mouseX>squareSize*7-3*(squareSize/2)||mouseX<squareSize+3*(squareSize/2)) && (mouseY<(1/6)*height+3*squareSize || mouseY>((1/6)*height)+4*squareSize))) controller.play((int)(mouseX-(2.5*squareSize))/3*squareSize, 3, playerNumber);
        if(!((mouseX>squareSize*7-4*(squareSize/2)||mouseX<squareSize+4*(squareSize/2)) && (mouseY<(1/6)*height+4*squareSize || mouseY>((1/6)*height)+5*squareSize))) controller.play((int)(mouseX-(3*squareSize))/2*squareSize, 4, playerNumber);
        if(!((mouseX>squareSize*7-5*(squareSize/2)||mouseX<squareSize+5*(squareSize/2)) && (mouseY<(1/6)*height+5*squareSize || mouseY>((1/6)*height)+6*squareSize))) controller.play((int)(mouseX-(3.5*squareSize))/squareSize, 5, playerNumber);

        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
