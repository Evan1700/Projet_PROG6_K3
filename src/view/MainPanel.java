package view;

import controller.K3;
import game.Coordinates;
import game.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel implements MouseListener {

    /*******************************************************************
     * k3 is the controller, we use it to access different information.
     *******************************************************************/
    K3 k3;
    /***********************************
     * width is the width of the Panel.
     ***********************************/
    int width;
    /**************************************
     * height is the height of the Panel.
     *************************************/
    int height;
    /****************************************************
     * squareSize is the size of the sides of the tiles.
     ****************************************************/
    int squareSize;
    /********************************************************
     * activePlayer is the player that need to do an action.
     ********************************************************/
    public int activePlayer;

    /*****************************************************
     * startX remember the start of each line of the K3.
     ****************************************************/
    public int[] startX;

    /**************************************************
     * startX remember the top of each line of the K3.
     **************************************************/
    public int[] startY;

    /***************************************
     * part is the active part of the game.
     ***************************************/
    public int part;

    /************************************************
     * This constructor initialise all the variable.
     * @param k3 is the controller.
     ************************************************/
    public MainPanel(K3 k3)
    {
        this.k3 = k3;
        activePlayer = k3.getActivePlayer();
        addMouseListener(this);
        width = 428;
        height = 720;
        squareSize = width/11;
        startX = new int[9];
        startY = new int[9];
        part = 0;
    }

    /********************************
     * Increase the part of the game.
     ********************************/
    public void nextPart() { part = part+1; }

    /**********************************
     * Decrease the part of the game.
     *********************************/
    public void previousPart(){ part = part-1; }

    /**********************************
     * Reset the part of the game.
     *********************************/
    public void resetPart(){ part = 0; }

    /************************************
     * Draw symbols for colorblind mode.
     * @param g is used to draw.
     * @param i is the height in the player pyramid.
     * @param j is the width in the player pyramid.
     * @param x is the width of the square in the screen.
     * @param y is the height of the square in the screen.
     ************************************/
    public void drawColorblind(Graphics g , int x , int y , int i , int j){

        if (k3.getPyramid().getPyramid()[i][j].getColorSwing() == Color.GREEN ){
            g.drawRect(x+5,y+5,squareSize-15, squareSize-15);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y + 6, squareSize - 11, squareSize - 11);
        }
        else if (k3.getPyramid().getPyramid()[i][j].getColorSwing() == Color.RED ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+5,y+squareSize-5,y+5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+6,y+squareSize-5+1,y+6},3);
        }

        else if (k3.getPyramid().getPyramid()[i][j].getColorSwing() == Color.YELLOW ){
            g.drawOval(x+3, y+3, squareSize-10, squareSize-10);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+4, squareSize-10+1, squareSize-10+1);
        }

        else if (k3.getPyramid().getPyramid()[i][j].getColorSwing() == Color.WHITE ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+(squareSize)/2,x+squareSize-5},new int[] {y+(squareSize)/2,y+5,y+squareSize-5,y+(squareSize)/2},4);;
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+(squareSize)/2+1,y+5+1,y+squareSize-5+1,y+(squareSize)/2+1},4);
        }

        else if (k3.getPyramid().getPyramid()[i][j].getColorSwing() == Color.BLUE ){
            g.drawRect(x+5, y+15, squareSize-13, squareSize-30);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y+14, squareSize - 12, squareSize - 29);
        }

        else if (k3.getPyramid().getPyramid()[i][j].isSameColor(new Tile(new Color(116,78,59))) ) {
            g.drawOval(x+3, y+12, squareSize-10, squareSize-25);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+13, squareSize-10+1, squareSize-25+1);
        }

        else if (k3.getPyramid().getPyramid()[i][j].isSameColor(new Tile(Color.BLACK)) ) {
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+squareSize-5,y+5,y+squareSize-5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+squareSize-5+1,y+5+1,y+squareSize-5+1},3);
        }


    }

    /************************************************************************************************************
     * This function is use to draw on the panel, here we draw the K3.
     * @param g is used to draw.
     ************************************************************************************************************/
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        activePlayer = k3.getActivePlayer();

        char[] player = new char[k3.getPlayers()[activePlayer].getName().length()];

        for(int i = 0; i < k3.getPlayers()[activePlayer].getName().length();i++) player[i] = k3.getPlayers()[activePlayer].getName().charAt(i);

        g.drawChars(player,0,k3.getPlayers()[activePlayer].getName().length(), 200, 30);


        int y = 720 - (height/6) - squareSize/2;
        int x = squareSize;


        for(int i=0; i < 9;i++)
        {
            startX[i] = x;
            for(int j=0;j<9-i;j++)
            {
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPyramid().getPyramid()[i][j].getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);

                if (k3.isColorblind() == 1) drawColorblind(g,x,y,i,j);

                x = x + squareSize;
            }
            startY[i] = y;
            x = x-(squareSize*(9-i))+(squareSize/2);
            y = y - squareSize;
        }

        if(k3.getPyramid().isLeft() && k3.getPyramid().getSidePyramidIndex()>0)
        {

            for(int i=0; i < 9;i++)
            {
                x = startX[i]-squareSize;
                y = startY[i];
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPyramid().getSidePyramid()[i][0].getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);
            }
        }
        else if(!k3.getPyramid().isLeft() && k3.getPyramid().getSidePyramidIndex()>0)
        {

            for(int i=0; i < 9;i++)
            {
                x = startX[i]-squareSize;
                y = startY[i];
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPyramid().getSidePyramid()[i][1].getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);
            }
        }

    }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /***********************************************************************************************
     * This function is used to remember the choice of the player at every step in the game.
     * @param e is the mouseEvent use to access information about the mouse.
     ***********************************************************************************************/
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(part == 0)
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseY >= startY[1] && mouseY <= startY[1] + squareSize && mouseX >= startX[1] && mouseX <= startX[1] + squareSize * 8 - 1) {
                System.out.println("(" + (mouseX - startX[1]) / squareSize + ", " + 1 + ")");
                k3.addPyramid(new Coordinates(1, (mouseX - startX[1]) / squareSize));
            } else if (mouseY >= startY[2] && mouseY <= startY[2] + squareSize && mouseX >= startX[2] && mouseX <= startX[2] + squareSize * 7 - 1) {
                System.out.println("(" + (mouseX - squareSize - (squareSize / 2)) / squareSize + ", " + 2 + ")");
                k3.addPyramid(new Coordinates(2, (mouseX - startX[2]) / squareSize));
            } else if (mouseY >= startY[3] && mouseY <= startY[3] + squareSize && mouseX >= startX[3] && mouseX <= startX[3] + squareSize * 6 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2) / squareSize + ", " + 2 + ")");
                k3.addPyramid(new Coordinates(3, (mouseX - startX[3]) / squareSize));
            } else if (mouseY >= startY[4] && mouseY <= startY[4] + squareSize && mouseX >= startX[4] && mouseX <= startX[4] + squareSize * 5 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2 - (squareSize / 2)) / squareSize + ", " + 3 + ")");
                k3.addPyramid(new Coordinates(4, (mouseX - startX[4]) / squareSize));
            } else if (mouseY >= startY[5] && mouseY <= startY[5] + squareSize && mouseX >= startX[5] && mouseX <= startX[5] + squareSize * 4 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3) / squareSize + ", " + 4 + ")");
                k3.addPyramid(new Coordinates(5, (mouseX - startX[5]) / squareSize));
            } else if (mouseY >= startY[6] && mouseY <= startY[6] + squareSize && mouseX >= startX[6] && mouseX <= startX[6] + squareSize * 3 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.addPyramid(new Coordinates(6, (mouseX - startX[6]) / squareSize));
            } else if (mouseY >= startY[7] && mouseY <= startY[7] + squareSize && mouseX >= startX[7] && mouseX <= startX[7] + squareSize * 2 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.addPyramid(new Coordinates(7, (mouseX - startX[7]) / squareSize));
            } else if (mouseY >= startY[8] && mouseY <= startY[8] + squareSize && mouseX >= startX[8] && mouseX <= startX[8] + squareSize - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.addPyramid(new Coordinates(8, (mouseX - startX[8]) / squareSize));
            } else {
                k3.resetIndex();
                k3.resetCoordinates();
            }
        }
    }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
