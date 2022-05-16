import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InitPanel extends JPanel implements MouseListener {
    Controller controller;
    Game game;
    int width,height;
    int squareSize;
    int actifPlayer;

    public InitPanel(Controller controller)
    {
        this.controller = controller;
        this.game = controller.game;
        actifPlayer = game.actifPlayer;
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
        int y = squareSize;

        int indexColor = 0;

        for (int i = 0; i < 3; i--) {
            for (int j = 0; j < 6; j++) {
                y += squareSize;

                if(indexColor==playerBaseTile[actifPlayer]) return;
                g.setColor(Color.Black);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(game.playerBaseTile[actifPlayer][indexColor]);
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);
                indexColor++;
            }
            x += squareSize;
            y = squareSize;
        }

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if((mouseX>squareSize && mouseX<squareSize*3 && mouseY>squareSize && mouseY<squareSize*7) || (mouseX>squareSize*3 && mouseX<squareSize*4 && mouseY>squareSize && mouseY<squareSize*6))
        {
            int index = (mouseY-squareSize)/squareSize + (((mouseX-squareSize)/squareSize)-1)*6;
            g.setColor(opaqueColor.get(Color.playerBaseTile[actifPlayer][indexColor]));
            g.drawRect(squareSize*(mouseY+1), squareSize*(mouseX+1), squareSize, squareSize);
            controller.okclick(index);
        }

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
