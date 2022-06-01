package view;

import controller.K3;
import game.Coordinates;
import game.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerPanel extends JPanel implements MouseListener {

    /*******************************************************************
     * k3 is the controller, we use it to access different information.
     ********************************************************************/
    K3 k3;
    /*************************************************************
     * player Number is the index where the player is in players.
     ************************************************************/
    int playerNumber;
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

    /*******************************************************************************************
     * started a boolean used to know when the player placed his first tile to start the timer.
     *****************************************************************************************/
    boolean started;

    /**************************************************************
     * startX remember the start of each line of the player pyramid.
     ***************************************************************/
    int[] startX;

    /**************************************************************
     * startX remember the top of each line of the player pyramid.
     **************************************************************/
    int[] startY;

    /***************************************
     * part is the active part of the game.
     ***************************************/
    int part;

    /************************************************
     * This constructor initialise all the variable.
     * @param k3 is the controller.
     * @param playerNumber is the number of the player in players.
     ************************************************/
    public PlayerPanel(K3 k3, int playerNumber)
    {
        this.k3 = k3;
        this.playerNumber = playerNumber;
        addMouseListener(this);
        width = 426;
        height = 720;
        squareSize = width/11;
        started = true;
        startX = new int[6];
        startY = new int[6];
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

        if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing() == Color.GREEN ){
            g.drawRect(x+5,y+5,squareSize-15, squareSize-15);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y + 6, squareSize - 11, squareSize - 11);
        }
        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing() == Color.RED ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+5,y+squareSize-5,y+5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+6,y+squareSize-5+1,y+6},3);
        }

        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing() == Color.YELLOW ){
            g.drawOval(x+3, y+3, squareSize-10, squareSize-10);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+4, squareSize-10+1, squareSize-10+1);
        }

        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing() == Color.WHITE ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+(squareSize)/2,x+squareSize-5},new int[] {y+(squareSize)/2,y+5,y+squareSize-5,y+(squareSize)/2},4);;
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+(squareSize)/2+1,y+5+1,y+squareSize-5+1,y+(squareSize)/2+1},4);
        }

        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing() == Color.BLUE ){
            g.drawRect(x+5, y+15, squareSize-13, squareSize-30);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y+14, squareSize - 12, squareSize - 29);
        }

        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].isSameColor(new Tile(new Color(116,78,59))) ) {
            g.drawOval(x+3, y+12, squareSize-10, squareSize-25);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+13, squareSize-10+1, squareSize-25+1);
        }

        else if (k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].isSameColor(new Tile(Color.BLACK)) ) {
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
     * @param tile is the tile draw.
     ************************************/
    public void drawBonusTilesColorblind(Graphics g , int x , int y , Tile tile){

        if (tile.getColorSwing() == Color.GREEN ){
            g.drawRect(x+5,y+5,squareSize-15, squareSize-15);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y + 6, squareSize - 11, squareSize - 11);
        }
        else if (tile.getColorSwing() == Color.RED ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+5,y+squareSize-5,y+5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+6,y+squareSize-5+1,y+6},3);
        }

        else if (tile.getColorSwing() == Color.YELLOW ){
            g.drawOval(x+3, y+3, squareSize-10, squareSize-10);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+4, squareSize-10+1, squareSize-10+1);
        }

        else if (tile.getColorSwing() == Color.WHITE ){
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+(squareSize)/2,x+squareSize-5},new int[] {y+(squareSize)/2,y+5,y+squareSize-5,y+(squareSize)/2},4);;
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+(squareSize)/2+1,y+5+1,y+squareSize-5+1,y+(squareSize)/2+1},4);
        }

        else if (tile.getColorSwing() == Color.BLUE ){
            g.drawRect(x+5, y+15, squareSize-13, squareSize-30);
            g.setColor(Color.BLACK);
            g.fillRect(x + 6, y+14, squareSize - 12, squareSize - 29);
        }

        else if (tile.isSameColor(new Tile(new Color(116,78,59))) ) {
            g.drawOval(x+3, y+12, squareSize-10, squareSize-25);
            g.setColor(Color.BLACK);
            g.fillOval(x+4, y+13, squareSize-10+1, squareSize-25+1);
        }

        else if (tile.isSameColor(new Tile(Color.BLACK)) ) {
            g.drawPolygon(new int[] {x+5,x+(squareSize)/2,x+squareSize-5},new int[] {y+squareSize-5,y+5,y+squareSize-5},3);
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[] {x+6,x+(squareSize)/2+1,x+squareSize-5+1},new int[] {y+squareSize-5+1,y+5+1,y+squareSize-5+1},3);
        }


    }

    /***************************************************************************************
     * This function is use to draw on the panel, here we draw the pyramid of the player.
     * @param g is used to draw.
     ***************************************************************************************/
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(!k3.canPlay() && part>0)
        {
            k3.endGame();
            return;
        }

        char[] player = new char[k3.getPlayers()[playerNumber].getName().length()];

        for(int i = 0; i < k3.getPlayers()[playerNumber].getName().length();i++) player[i] = k3.getPlayers()[playerNumber].getName().charAt(i);

        g.drawChars(player,0,k3.getPlayers()[playerNumber].getName().length(), 300, 20);

        int x;
        int y = 720 - (height/6) - squareSize*3;

        for(int i=0; i < 6;i++)
        {
            x = ((2*squareSize) + (squareSize/2)) + (i*(squareSize/2));
            startX[i] = x;
            for(int j=0;j<6-i;j++)
            {
                g.setColor(Color.BLACK);
                g.drawRect(x, y, squareSize, squareSize);
                g.setColor(k3.getPlayersPyramid()[playerNumber].getPyramid()[i][j].getColorSwing());
                g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);

                if (k3.isColorblind() == 1) drawColorblind(g,x,y,i,j);

                x = x + squareSize;
            }
            startY[i] = y;
            y = y - squareSize;
        }
        char[] labelBonusTile = new char[12];
        String labelBonusTileString = "Bonus Tile :";
        for(int i =0;i<labelBonusTileString.length();i++) labelBonusTile[i] = labelBonusTileString.charAt(i);
        g.setColor(Color.BLACK);
        g.drawChars(labelBonusTile,0,12, squareSize/2, 720 - (height/6) - squareSize*2 + squareSize/2);

        g.setColor(Color.BLACK);
        g.drawRect(squareSize/2, 725 - (height/6) - squareSize*2 + squareSize/2, squareSize*10, squareSize*3);

        x = squareSize;
        y =720 - squareSize*4;

        for(Tile tile : k3.getPlayersPyramid()[playerNumber].getBonusTile())
        {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, squareSize, squareSize);
            g.setColor(tile.getColorSwing());
            g.fillRect(x + 1, y + 1, squareSize - 1, squareSize - 1);

            if (k3.isColorblind() == 1) drawBonusTilesColorblind(g,x,y,tile);

            x = x+squareSize;
            if(x-(squareSize*8) == squareSize)
            {
                y = y +squareSize;
                x = squareSize;
            }
        }

        x = squareSize/4;
        y =40;

        g.setColor(Color.BLACK);
        g.drawRect(x, y, 412-squareSize/4, 230);
        g.setColor(new Color(9, 16, 106));
        g.fillRect(x + 1, y + 1, 411-squareSize/4, 229);

        if(k3.getPlayers()[playerNumber].isAi() && part == 1) k3.AiPartTwo();
        if(k3.getPlayers()[(playerNumber+1)%2].isAi() && part == 2) k3.AiPartThree();
        if(k3.getPlayers()[playerNumber].isAi() && part == 3) k3.AiPartFour();
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
            if(!(k3.getActivePlayer() == playerNumber)) return;

            if (started) {
                k3.startTimer();
                started = false;
            }

            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseY >= startY[0] && mouseY <= startY[0] + squareSize && mouseX >= startX[0] && mouseX <= startX[0] + squareSize * 6 - 1) {
                System.out.println("(" + (mouseX - startX[0]) / squareSize + ", " + 0 + ")");
                k3.addPlayerPyramid(new Coordinates(0, (mouseX - startX[0]) / squareSize));
            } else if (mouseY >= startY[1] && mouseY <= startY[1] + squareSize && mouseX >= startX[1] && mouseX <= startX[1] + squareSize * 5 - 1) {
                System.out.println("(" + (mouseX - squareSize - (squareSize / 2)) / squareSize + ", " + 1 + ")");
                k3.addPlayerPyramid(new Coordinates(1, (mouseX - startX[1]) / squareSize));
            } else if (mouseY >= startY[2] && mouseY <= startY[2] + squareSize && mouseX >= startX[2] && mouseX <= startX[2] + squareSize * 4 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2) / squareSize + ", " + 2 + ")");
                k3.addPlayerPyramid(new Coordinates(2, (mouseX - startX[2]) / squareSize));
            } else if (mouseY >= startY[3] && mouseY <= startY[3] + squareSize && mouseX >= startX[3] && mouseX <= startX[3] + squareSize * 3 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2 - (squareSize / 2)) / squareSize + ", " + 3 + ")");
                k3.addPlayerPyramid(new Coordinates(3, (mouseX - startX[3]) / squareSize));
            } else if (mouseY >= startY[4] && mouseY <= startY[4] + squareSize && mouseX >= startX[4] && mouseX <= startX[4] + squareSize * 2 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3) / squareSize + ", " + 4 + ")");
                k3.addPlayerPyramid(new Coordinates(4, (mouseX - startX[4]) / squareSize));
            } else if (mouseY >= startY[5] && mouseY <= startY[5] + squareSize && mouseX >= startX[5] && mouseX <= startX[5] + squareSize - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.addPlayerPyramid(new Coordinates(5, (mouseX - startX[5]) / squareSize));
            } else {
                k3.resetIndex();
                k3.resetCoordinates();
            }
        }
        else if(part==1)
        {
            if(!(k3.getActivePlayer() == playerNumber)) return;

            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseY >= startY[0] && mouseY <= startY[0] + squareSize && mouseX >= startX[0] && mouseX <= startX[0] + squareSize * 6 - 1) {
                System.out.println("(" + (mouseX - startX[0]) / squareSize + ", " + 0 + ")");
                k3.coordinatesMemory(new Coordinates(0, (mouseX - startX[0]) / squareSize));
            } else if (mouseY >= startY[1] && mouseY <= startY[1] + squareSize && mouseX >= startX[1] && mouseX <= startX[1] + squareSize * 5 - 1) {
                System.out.println("(" + (mouseX - squareSize - (squareSize / 2)) / squareSize + ", " + 1 + ")");
                k3.coordinatesMemory(new Coordinates(1, (mouseX - startX[1]) / squareSize));
            } else if (mouseY >= startY[2] && mouseY <= startY[2] + squareSize && mouseX >= startX[2] && mouseX <= startX[2] + squareSize * 4 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2) / squareSize + ", " + 2 + ")");
                k3.coordinatesMemory(new Coordinates(2, (mouseX - startX[2]) / squareSize));
            } else if (mouseY >= startY[3] && mouseY <= startY[3] + squareSize && mouseX >= startX[3] && mouseX <= startX[3] + squareSize * 3 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2 - (squareSize / 2)) / squareSize + ", " + 3 + ")");
                k3.coordinatesMemory(new Coordinates(3, (mouseX - startX[3]) / squareSize));
            } else if (mouseY >= startY[4] && mouseY <= startY[4] + squareSize && mouseX >= startX[4] && mouseX <= startX[4] + squareSize * 2 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3) / squareSize + ", " + 4 + ")");
                k3.coordinatesMemory(new Coordinates(4, (mouseX - startX[4]) / squareSize));
            } else if (mouseY >= startY[5] && mouseY <= startY[5] + squareSize && mouseX >= startX[5] && mouseX <= startX[5] + squareSize - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.coordinatesMemory(new Coordinates(5, (mouseX - startX[5]) / squareSize));
            } else if(mouseY >= 720 - squareSize*4 && mouseY <= 720 - squareSize*3 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ ((mouseX ) / squareSize));
                if(((mouseX - squareSize) / squareSize) < k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.indexMemoryParTwo(((mouseX - squareSize) / squareSize));
            } else if(mouseY >= 720 - squareSize*3 && mouseY <= 720 - squareSize*2 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ (((mouseX ) / squareSize)+8));
                if(((mouseX - squareSize) / squareSize) < k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.indexMemoryParTwo(((mouseX - squareSize) / squareSize)+8);
            } else {
                k3.resetIndex();
                k3.resetCoordinates();
            }
        }
        else if(part==2)
        {
            if(!(k3.getActivePlayer() == playerNumber)) return;

            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseY >= startY[0] && mouseY <= startY[0] + squareSize && mouseX >= startX[0] && mouseX <= startX[0] + squareSize * 6 - 1) {
                System.out.println("(" + (mouseX - squareSize) / squareSize + ", " + 0 + ")");
                k3.penaltyPyramid(new Coordinates(0, (mouseX - startX[0]) / squareSize));
            } else if (mouseY >= startY[1] && mouseY <= startY[1] + squareSize && mouseX >= startX[1] && mouseX <= startX[1] + squareSize * 5 - 1) {
                System.out.println("(" + (mouseX - squareSize - (squareSize / 2)) / squareSize + ", " + 1 + ")");
                k3.penaltyPyramid(new Coordinates(1, (mouseX - startX[1]) / squareSize));
            } else if (mouseY >= startY[2] && mouseY <= startY[2] + squareSize && mouseX >= startX[2] && mouseX <= startX[2] + squareSize * 4 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2) / squareSize + ", " + 2 + ")");
                k3.penaltyPyramid(new Coordinates(2, (mouseX - startX[2]) / squareSize));
            } else if (mouseY >= startY[3] && mouseY <= startY[3] + squareSize && mouseX >= startX[3] && mouseX <= startX[3] + squareSize * 3 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2 - (squareSize / 2)) / squareSize + ", " + 3 + ")");
                k3.penaltyPyramid(new Coordinates(3, (mouseX - startX[3]) / squareSize));
            } else if (mouseY >= startY[4] && mouseY <= startY[4] + squareSize && mouseX >= startX[4] && mouseX <= startX[4] + squareSize * 2 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3) / squareSize + ", " + 4 + ")");
                k3.penaltyPyramid(new Coordinates(4, (mouseX - startX[4]) / squareSize));
            } else if (mouseY >= startY[5] && mouseY <= startY[5] + squareSize && mouseX >= startX[5] && mouseX <= startX[5] + squareSize - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.penaltyPyramid(new Coordinates(5, (mouseX - startX[5]) / squareSize));
            } else if(mouseY >= 720 - squareSize*4 && mouseY <= 720 - squareSize*3 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ ((mouseX ) / squareSize));
                if(((mouseX - squareSize) / squareSize) <= k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.penaltyBonusTile(((mouseX - squareSize) / squareSize));
            } else if(mouseY >= 720 - squareSize*3 && mouseY <= 720 - squareSize*2 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ (((mouseX ) / squareSize)+8));
                if(((mouseX - squareSize) / squareSize) <= k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.penaltyBonusTile(((mouseX - squareSize) / squareSize)+8);
            } else {
                k3.resetIndex();
                k3.resetCoordinates();
            }
        }
        else
        {
            if(!(k3.getActivePlayer() == playerNumber)) return;

            System.out.println("test");

            int mouseX = e.getX();
            int mouseY = e.getY();

            if (mouseY >= startY[0] && mouseY <= startY[0] + squareSize && mouseX >= startX[0] && mouseX <= startX[0] + squareSize * 6 - 1) {
                System.out.println("(" + (mouseX - startX[0]) / squareSize + ", " + 0 + ")");
                k3.addSidePyramidPyramid(new Coordinates(0, (mouseX - startX[0]) / squareSize));
            } else if (mouseY >= startY[1] && mouseY <= startY[1] + squareSize && mouseX >= startX[1] && mouseX <= startX[1] + squareSize * 5 - 1) {
                System.out.println("(" + (mouseX - squareSize - (squareSize / 2)) / squareSize + ", " + 1 + ")");
                k3.addSidePyramidPyramid(new Coordinates(1, (mouseX - startX[1]) / squareSize));
            } else if (mouseY >= startY[2] && mouseY <= startY[2] + squareSize && mouseX >= startX[2] && mouseX <= startX[2] + squareSize * 4 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2) / squareSize + ", " + 2 + ")");
                k3.addSidePyramidPyramid(new Coordinates(2, (mouseX - startX[2]) / squareSize));
            } else if (mouseY >= startY[3] && mouseY <= startY[3] + squareSize && mouseX >= startX[3] && mouseX <= startX[3] + squareSize * 3 - 1) {
                System.out.println("(" + (mouseX - squareSize * 2 - (squareSize / 2)) / squareSize + ", " + 3 + ")");
                k3.addSidePyramidPyramid(new Coordinates(3, (mouseX - startX[3]) / squareSize));
            } else if (mouseY >= startY[4] && mouseY <= startY[4] + squareSize && mouseX >= startX[4] && mouseX <= startX[4] + squareSize * 2 - 1) {
                System.out.println("(" + (mouseX - squareSize * 3) / squareSize + ", " + 4 + ")");
                k3.addSidePyramidPyramid(new Coordinates(4, (mouseX - startX[4]) / squareSize));
            } else if (mouseY >= startY[5] && mouseY <= startY[5] + squareSize && mouseX >= startX[5] && mouseX <= startX[5] + squareSize - 1) {
                System.out.println("(" + (mouseX - squareSize * 3 - (squareSize / 2)) / squareSize + ", " + 5 + ")");
                k3.addSidePyramidPyramid(new Coordinates(5, (mouseX - startX[5]) / squareSize));
            } else if(mouseY >= 720 - squareSize*4 && mouseY <= 720 - squareSize*3 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ ((mouseX ) / squareSize));
                if(((mouseX - squareSize) / squareSize) < k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.addSidePyramidBonusTile(((mouseX - squareSize) / squareSize));
            } else if(mouseY >= 720 - squareSize*3 && mouseY <= 720 - squareSize*2 && mouseX >= squareSize && mouseX <= squareSize*9){
                System.out.println("Index bonusTile "+ (((mouseX ) / squareSize)+8));
                if(((mouseX - squareSize) / squareSize) < k3.getPlayersPyramid()[playerNumber].getBonusTile().size()) k3.addSidePyramidBonusTile(((mouseX - squareSize) / squareSize)+8);
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
