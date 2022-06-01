package game;

import java.awt.*;

public class Tile
{
    /********************************************************************************
     * We just define the color brown because it does not have a shortcut for it.
     *******************************************************************************/
    final Color BROWN = new Color(116,78,59);

    /*******************************************************
     *ColorEnum is used to compare two tiles in a quick way.
     *******************************************************/
    public enum ColorEnum
    {
        EMPTY,
        WHITE,
        BROWN,
        BLACK,
        GREEN,
        YELLOW,
        RED,
        BLUE;
    }

    /*************************************************************
     * colorEnum is the color link with the colorSwing.
     *************************************************************/
    ColorEnum colorEnum;
    /************************************************************
     * colorSwing is the real color to get it easily in the UI.
     ************************************************************/
    Color colorSwing;

    /*********************************************************************************************
     * This constructor take a Color keep it in colorSwing and associated it with an ColorEnum.
     * In our game GColor.GRAY represent an empty tile.
     * @param color is a Color object, that represent the color of the tile
     *********************************************************************************************/
    public Tile(Color color)
    {
        if(color == Color.BLACK) colorSwing = new Color(69, 69, 69);
        else if(color == Color.GRAY) colorSwing = new Color(173, 173, 173);
        else colorSwing = color;

        if(color == Color.GRAY) colorEnum = ColorEnum.EMPTY;
        else if(color == Color.WHITE) colorEnum = ColorEnum.WHITE;
        else if(color == BROWN) colorEnum = ColorEnum.BROWN;
        else if(color == Color.BLACK) colorEnum = ColorEnum.BLACK;
        else if(color == Color.GREEN) colorEnum = ColorEnum.GREEN;
        else if(color == Color.YELLOW) colorEnum = ColorEnum.YELLOW;
        else if(color == Color.RED) colorEnum = ColorEnum.RED;
        else if(color == Color.BLUE) colorEnum = ColorEnum.BLUE;
    }

    /***********************************************************************
     * This function compare two tiles with each other.
     * @param tile is the tile to compare to.
     * @return true if the two tiles are the same color and false if not.
     **********************************************************************/
    public boolean isSameColor(Tile tile){ return this.colorEnum == tile.colorEnum; }

    /****************************************************
     * This function return the colorSwing of the tile.
     * @return a Color object of Swing
     ***************************************************/
    public Color getColorSwing(){ return colorSwing; }
}
