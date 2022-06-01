package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerPyramid
{
    /**********************************************************************************************
     * pyramid is a pyramid of tile with a base of 6, it's represent the pyramid of the player.
     **********************************************************************************************/
    Tile[][] pyramid;
    /************************************************************************
     * approachableTile is the coordinates of tiles that the player can use.
     ************************************************************************/
    ApproachableTile approachableTile;

    /*****************************************************************************************************************
     * bonusTile is a List of the tile that the player has but not in the pyramid, like the one he get from penalty.
     *****************************************************************************************************************/
    List<Tile> bonusTile;

    /**********************************************************************************************************************************
     * baseTile is a List of the tiles that the player draw at the start of the game, after he created his pyramid it will be empty.
     **********************************************************************************************************************************/
    List<Tile> baseTile;


    /******************************************************************************************************************
     *  This constructor initialise every variable, plus put every tile in the pyramid as empty or Gray.
     *  And add to approachableTile the coordinates of the tile that is above the pyramid.
     *****************************************************************************************************************/
    public PlayerPyramid()
    {
        pyramid = new Tile[6][];
        for (int i = 0; i < 6; i++)
        {
            pyramid[i] = new Tile[6-i];
            for(int j = 0; j < 6-i; j++) pyramid[i][j] = new Tile(Color.GRAY);
        }

        approachableTile = new ApproachableTile();
        approachableTile.add(new Coordinates(5, 0));

        bonusTile = new ArrayList<Tile>();

        baseTile = new ArrayList<Tile>();
    }

    /****************************************************
     * This function add a tile to baseTile.
     * @param tile is the tile that is added to basTile.
     ****************************************************/
    public void addBaseTile(Tile tile)
    {
        baseTile.add(tile);
        updateBaseTile();
    }

    /********************************************
     * This function order the baseTile by color.
     ********************************************/
    public void updateBaseTile()
    {
        baseTile.sort(new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) { return Integer.compare(getAssignedValue(o1), getAssignedValue(o2)); }

            int getAssignedValue(Tile t)
            {
                Tile.ColorEnum c = t.colorEnum;
                if(c==null) c= Tile.ColorEnum.EMPTY;
                switch (c) {
                    case RED:
                        return 4;
                    case GREEN:
                        return 3;
                    case BLUE:
                        return 1;
                    case BLACK:
                        return 0;
                    case YELLOW:
                        return 5;
                    case WHITE:
                        return 6;
                    case BROWN:
                        return 2;
                    default:
                        return Integer.MAX_VALUE;
                }
            }
        });
    }


    /*************************************************************************************************************
     * This function add a tile from baseTile to the pyramid at the coordinates given.
     * @param index of the tile in basTile.
     * @param coordinates of the wanted placements.
     ************************************************************************************************************/
    public void addPyramid(int index, Coordinates coordinates)
    {
        pyramid[coordinates.y][coordinates.x] = baseTile.get(index);
        baseTile.remove(index);
    }

    /**********************************************************************************
     * This function test if the tile at the given coordinate is approachable.
     * @param coordinates of the tile tested
     * @return true if the coordinate contain a approachable tile and false if not.
     **********************************************************************************/
    public boolean isApproachableTile(Coordinates coordinates)
    {
        return approachableTile.contains(coordinates);
    }

    /*******************************************************************************************
     * This function test if the player have remains tile.
     * @return true if approachableTile or bonusTile is not empty and false if both are empty.
     *******************************************************************************************/
    public boolean isEmpty() { return approachableTile.list.size() == 0 || bonusTile.size() == 0; }

    /*************************************************************************************************************************************
     * This function turn the tile at the coordinate to an empty tile and add, if needed, the new approachable tile to approachableTile.
     * @param coordinates of the tile wanted to play.
     * @return the tile at the coordinate given.
     **************************************************************************************************************************************/
    public Tile playPyramid(Coordinates coordinates)
    {
        System.out.println("y="+coordinates.y+" x="+coordinates.x);
        Tile res = pyramid[coordinates.y][coordinates.x];

        pyramid[coordinates.y][coordinates.x] = new Tile(Color.GRAY);
        if(coordinates.y>0 && coordinates.x>0 && coordinates.x<pyramid[coordinates.y].length-1)
        {
            if(pyramid[coordinates.y][coordinates.x-1].isSameColor(new Tile(Color.GRAY))) approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x));
            if(pyramid[coordinates.y][coordinates.x+1].isSameColor(new Tile(Color.GRAY))) approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x+1));
        }
        else if(coordinates.y==5 && coordinates.x==0)
        {
            approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x));
            approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x+1));
        }
        else if(coordinates.y>0 && coordinates.x==0)
        {
            approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x));
            if(pyramid[coordinates.y][coordinates.x+1].isSameColor(new Tile(Color.GRAY))) approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x+1));
        }
        else if(coordinates.y>0 && coordinates.x==pyramid[coordinates.y].length-1)
        {
            if(pyramid[coordinates.y][coordinates.x-1].isSameColor(new Tile(Color.GRAY))) approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x));
            approachableTile.add(new Coordinates(coordinates.y-1,coordinates.x+1));
        }

        approachableTile.remove(coordinates);

        return res;
    }

    /*************************************************************************************************************************************
     * This function turn the tile in the bonusTile to an empty tile.
     * @param index of the tile wanted to play.
     * @return the wanted tile.
     **************************************************************************************************************************************/
    public Tile playBonusTile(int index)
    {
        Tile res = bonusTile.get(index);

        bonusTile.remove(index);

        return res;
    }

    /***********************************
     * This function ad a bonus tile.
     * @param t teh tile that is added.
     ***********************************/
    public void addBonusTile(Tile t) { bonusTile.add(t); }

    /******************************************
     * This function is a getter to pyramid.
     * @return pyramid.
     ******************************************/
    public Tile[][] getPyramid() { return pyramid; }

    /*************************************************
     * This function is a getter to approachableTile.
     * @return approachableTile.
     *************************************************/
    public ApproachableTile getApproachableTile() { return approachableTile; }

    /*******************************************
     * This function is a getter to bonusTile.
     * @return bonusTile.
     *******************************************/
    public List<Tile> getBonusTile() { return bonusTile; }

    /******************************************
     * This function is a getter to baseTile.
     * @return baseTile.
     *****************************************/
    public List<Tile> getBaseTile() { return baseTile; }

}
