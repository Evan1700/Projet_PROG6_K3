package view;

import controller.K3;
import game.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InitPanel extends JPanel implements MouseListener {
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

    /************************************************
     * This constructor initialise all the variable.
     * @param k3 is the controller.
     ************************************************/
    public InitPanel(K3 k3)
    {
        this.k3 = k3;
        activePlayer = k3.getActivePlayer();
        addMouseListener(this);
        width = 428;
        height = 720;
        squareSize = width/11;
    }

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

    /************************************
     * Draw symbols for colorblind mode.
     * @param g is used to draw.
     * @param x is the width of the square in the screen.
     * @param y is the height of the square in the screen.
     * @param indexColor is the index in the base tile.
     ************************************/
    public void drawBaseColorblind(Graphics g , int x , int y, int indexColor){

        if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing() == Color.GREEN ){
            g.drawRect(x+5,y+5,squareSize-15, squareSize-15);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y + 6, squareSize - 11, squareSize - 11);
        }
        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing() == Color.RED ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+5,y+squareSize-5,y+5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+6,y+squareSize-5+1,y+6},3);
        }

        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing() == Color.YELLOW ){
            g.drawOval(x+3, y+3, squareSize-10, squareSize-10);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+4, squareSize-10+1, squareSize-10+1);
        }

        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing() == Color.WHITE ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+(squareSize)/2,x+squareSize-5},new int[] {y+(squareSize)/2,y+5,y+squareSize-5,y+(squareSize)/2},4);;
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+(squareSize)/2+1,y+5+1,y+squareSize-5+1,y+(squareSize)/2+1},4);
        }

        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing() == Color.BLUE ){
            g.drawRect(x+5, y+15, squareSize-13, squareSize-30);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y+14, squareSize - 12, squareSize - 29);
        }

        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).isSameColor(new Tile(new Color(116,78,59))) ) {
            g.drawOval(x+3, y+12, squareSize-10, squareSize-25);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+13, squareSize-10+1, squareSize-25+1);
        }

        else if (k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).isSameColor(new Tile(Color.BLACK)) ) {
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+squareSize-5,y+5,y+squareSize-5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+squareSize-5+1,y+5+1,y+squareSize-5+1},3);
        }

    }

    /************************************************************************************************************
     * This function is use to draw on the panel, here we draw the baseTile of the player creating his pyramid.
     * @param g is used to draw.
     ************************************************************************************************************/
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        char[] player = new char[k3.getPlayers()[activePlayer].getName().length()];

        for(int i = 0; i < k3.getPlayers()[activePlayer].getName().length();i++) player[i] = k3.getPlayers()[activePlayer].getName().charAt(i);

        g.drawChars(player,0,k3.getPlayers()[activePlayer].getName().length(), 300, 30);

        int x = squareSize + squareSize/2;
        int y = squareSize*2;

        int indexColor = 0;

        for (int i = 0; i < 5; i++)
        {
            if(i==3)
            {
                if((k3.getPlayersPyramid()[activePlayer].getBaseTile().size()-indexColor)==3) x = squareSize*2+squareSize/2+ squareSize/2;
                else if((k3.getPlayersPyramid()[activePlayer].getBaseTile().size()-indexColor)==2)x = squareSize*3+ squareSize/2;
                else x = squareSize*3+squareSize/2+ squareSize/2;
            }
            for (int j = 0; j < 6; j++)
            {
                x += squareSize;
                if (k3.getPlayersPyramid()[activePlayer].getBaseTile().size() <= indexColor) break;
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPlayersPyramid()[activePlayer].getBaseTile().get(indexColor).getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);
                if (k3.isColorblind() == 1) drawBaseColorblind(g,x,y,indexColor);
                indexColor++;
            }
            y += squareSize;
            x = squareSize+ squareSize/2;
        }

        y = 720 - (height/6) - squareSize/2;
        x = squareSize;

        for(int i=0; i < 9;i++)
        {
            for(int j=0;j<9-i;j++)
            {
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPyramid().getPyramid()[i][j].getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);

                if (k3.isColorblind() == 1) drawColorblind(g,x,y,i,j);


                x = x + squareSize;
            }
            x = x-(squareSize*(9-i))+(squareSize/2);
            y = y - squareSize;
        }

    }

    /***********************************************************************************************
     * This function is used to remember the choice of the tile to place when the mouse is pressed.
     * @param e is the mouseEvent use to access information about the mouse.
     ***********************************************************************************************/
    @Override
    public void mousePressed(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if((mouseX>squareSize*2+(squareSize/2) && mouseX<squareSize*8+(squareSize/2) && mouseY>squareSize*2 && mouseY<squareSize*5))
        {
            int index = ((mouseY-squareSize*2)/squareSize)*6 + ((mouseX-squareSize*2+(squareSize/2))/squareSize);
            if(k3.getPlayersPyramid()[activePlayer].getBaseTile().size()>=index)
            {
                System.out.println(index);
                k3.indexMemoryParOne(index);
            }
        }
        else if((k3.getPlayersPyramid()[activePlayer].getBaseTile().size()-18)==3 && (mouseX>(squareSize*4) && mouseX<(squareSize*7)) && (mouseY>squareSize*5 && mouseY<squareSize*6))
        {
            int index = ((mouseY-squareSize*2)/squareSize)*6 + ((mouseX-(squareSize*3))/squareSize);
            System.out.println(index);
            if(k3.getPlayersPyramid()[activePlayer].getBaseTile().size()>=index)
            {
                System.out.println(index);
                k3.indexMemoryParOne(index);
            }
        }
        else if((k3.getPlayersPyramid()[activePlayer].getBaseTile().size()-18)==2 && (mouseX>squareSize*4+(squareSize/2) && mouseX<squareSize*6+(squareSize/2)) && (mouseY>squareSize*5 && mouseY<squareSize*6))
        {
            int index = ((mouseY-squareSize*2)/squareSize)*6 + ((mouseX-(squareSize*4)+(squareSize/2))/squareSize);
            System.out.println(index);
            if(k3.getPlayersPyramid()[activePlayer].getBaseTile().size()>=index)
            {
                System.out.println(index);
                k3.indexMemoryParOne(index);
            }
        }
        else if((k3.getPlayersPyramid()[activePlayer].getBaseTile().size()-18)==1 && (mouseX>squareSize*5 && mouseX<squareSize*6 && (mouseY>squareSize*5 && mouseY<squareSize*6)))
        {
            int index = ((mouseY-squareSize*2)/squareSize)*6 + 1;
            if(k3.getPlayersPyramid()[activePlayer].getBaseTile().size()>=index)
            {
                System.out.println(index);
                k3.indexMemoryParOne(index);
            }
        }
        else
        {
            k3.resetIndex();
        }
        return;
    }

    /*******************************************************************
     * This function change the player that need to create his pyramid.
     ******************************************************************/
    public void changePlayer()
    {
        activePlayer = (activePlayer+1)%2;
        this.repaint();
    }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseClicked(MouseEvent e) { }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseReleased(MouseEvent e) { }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseEntered(MouseEvent e) { }

    /***************************
     * This function is unused.
     * @param e is not used.
     ***************************/
    @Override
    public void mouseExited(MouseEvent e) { }
}
