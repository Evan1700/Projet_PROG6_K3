package game;

public class Coordinates
{

    /*****************************************************
     * y is the height of the coordinates in the pyramid
     *****************************************************/
    public int y;
    /*************************************
     * x is the width of the coordinates.
     *************************************/
    public int x;

    /************************************************************
     * This constructor only define y and x.
     * @param y is the height of the coordinates.
     * @param x is the width of the coordinates.
     *          (0,0) start at the bottom left of the pyramid
     ***********************************************************/
    public Coordinates(int y, int x)
    {
        this.y = y;
        this.x = x;
    }

    /***********************************************************************
     * This function compare two coordinate.
     * @param coordinates to compare with
     * @return true if the two coordinates are the same and false if not
     *********************************************************************/
    public boolean isEquals(Coordinates coordinates){ return y == coordinates.y && x == coordinates.x; }
}
