package game;

import java.awt.*;
import java.util.List;

public class Pyramid
{
        /***********************************************************************************
         * pyramid is a pyramid of tile with a base of 9, it's represent the K3.
         ***********************************************************************************/
        Tile[][] pyramid;
        /**********************************************************************************
         * approachableTile is the coordinates of empty tiles that the player can play on.
         **********************************************************************************/
        ApproachableTile approachableTile;

        /*****************************************************************************************************************************************************
         * sidePyramid is a list of 10 tiles. It represent the case when the K3 is full and the player need to add tile a the left or the right of the K3.
         ******************************************************************************************************************************************************/
        Tile[][] sidePyramid;
        /*********************************************************************************************************************
         * isLeft is a boolean to know at which side the player wanted to add his tile. If true it's left, if false is right.
         **********************************************************************************************************************/
        boolean isLeft;
        /*****************************************************************************************************************************************
         * sidePyramidIndex is a integer that contains the index where the next tile need to be placed. It's also the high of the sidePyramid.
         *****************************************************************************************************************************************/
        int sidePyramidIndex;

        /**************************************************************************************************************************
         *  This constructor initialise every variable, plus put every tile in the pyramid and the sidePyramid as empty or Gray.
         *  And add to approachableTile the coordinates of the tile that is above the pyramid.
         *  Also put the sidePyramidIndex to 0, and temporary isLeft to true.
         ***************************************************************************************************************************/
        public Pyramid()
        {
                pyramid = new Tile[9][];
                for (int i = 0; i < 9; i++)
                {
                        pyramid[i] = new Tile[9-i];
                        for(int j = 0; j < 9-i; j++) pyramid[i][j] = new Tile(Color.GRAY);
                }

                approachableTile = new ApproachableTile();
                for (int i = 0; i < 8; i++) { approachableTile.add(new Coordinates(1,i)); }

                sidePyramid = new Tile[10][];
                for (int i = 0; i < 10; i++)
                {
                        sidePyramid[i] = new Tile[2];
                        for(int j = 0; j < 2; j++) sidePyramid[i][j] = new Tile(Color.GRAY);
                }

                isLeft = true;
                sidePyramidIndex = 0;
        }

        /******************************************************************************************
         * This function place the 9 first tiles from a list of tile at the bottom of the pyramid.
         * @param tiles is a list of 9 tiles that as at least 4 different color in it.
         ******************************************************************************************/
        public void initPyramid(List<Tile> tiles)
        {
                for(int i = 0; i<9;i++) pyramid[0][i] = tiles.get(i);
                for(int i = 0; i<8;i++) approachableTile.add(new Coordinates(1,i));
        }

        /**********************************************************************************
         * This function test if the given tile can be placed at the wanted coordinate.
         * @param tile that is tested a the coordinate.
         * @param coordinates the coordinate to test the tile.
         * @return true if the tile can be placed at the coordinate and false if not.
         ***********************************************************************/
        public boolean isOk(Tile tile, Coordinates coordinates)
        {
                if(!pyramid[coordinates.y][coordinates.x].isSameColor(new Tile(Color.GRAY))) return false;

                if(tile.isSameColor(new Tile(Color.WHITE))) return true;

                if(tile.isSameColor(new Tile(new Color(116,78,59))) && !pyramid[coordinates.y-1][coordinates.x].isSameColor(new Tile(Color.GRAY)) && !pyramid[coordinates.y-1][coordinates.x +1].isSameColor(new Tile(Color.GRAY))) return true;

                if(!tile.isSameColor(new Tile(Color.GRAY)) && (pyramid[coordinates.y-1][coordinates.x].isSameColor(tile) || pyramid[coordinates.y-1][coordinates.x +1].isSameColor(tile) || pyramid[coordinates.y-1][coordinates.x].isSameColor(new Tile(new Color(116,78,59))) || pyramid[coordinates.y-1][coordinates.x +1].isSameColor(new Tile(new Color(116,78,59))))) return true;

                return false;
        }

        /******************************************************************************************************
         * This function add a tile to a coordinate in the pyramid.
         * @param tile is the tile that need to be placed.
         * @param coordinates is the wanted coordinate to placed the given tile.
         ******************************************************************************************************/
        public void add(Tile tile, Coordinates coordinates)
        {
                pyramid[coordinates.y][coordinates.x] = tile;

                if(pyramid[coordinates.y].length-1 == coordinates.x)
                {
                        if(!pyramid[coordinates.y][coordinates.x-1].isSameColor(new Tile(Color.GRAY)) && pyramid[coordinates.y+1][coordinates.x-1].isSameColor(new Tile(Color.GRAY)))
                        {
                                approachableTile.add(new Coordinates(coordinates.y+1,coordinates.x-1));
                        }
                        approachableTile.remove(coordinates);
                        return;
                }

                if(coordinates.x == 0)
                {
                        if(!pyramid[coordinates.y][coordinates.x+1].isSameColor(new Tile(Color.GRAY)) && pyramid[coordinates.y+1][coordinates.x].isSameColor(new Tile(Color.GRAY)))
                        {
                                approachableTile.add(new Coordinates(coordinates.y+1,coordinates.x));
                        }
                        approachableTile.remove(coordinates);
                        return;
                }

                if(!pyramid[coordinates.y][coordinates.x+1].isSameColor(new Tile(Color.GRAY)) && pyramid[coordinates.y+1][coordinates.x].isSameColor(new Tile(Color.GRAY)))
                {
                        approachableTile.add(new Coordinates(coordinates.y+1,coordinates.x));
                }
                if(!pyramid[coordinates.y][coordinates.x-1].isSameColor(new Tile(Color.GRAY)) && pyramid[coordinates.y+1][coordinates.x-1].isSameColor(new Tile(Color.GRAY)))
                {
                        approachableTile.add(new Coordinates(coordinates.y+1,coordinates.x-1));
                }
                approachableTile.remove(coordinates);
        }

        /**************************************************************
         * This function is the first time when the pyramid is full.
         * @param isLeft is a boolean to represent the side of the pyramid.
         ***************************************************************/
        public void initSidePyramid(boolean isLeft)
        {
                this.isLeft = isLeft;
                if(isLeft)
                {
                        for(int i = 0; i < 9; i++) sidePyramid[i][1] = pyramid[i][0];
                        for(int i = 0; i < 9; i++) sidePyramid[i][0] = new Tile(Color.GRAY);
                }
                else
                {
                        for(int i = 0; i < 9; i++) sidePyramid[i][0] = pyramid[i][pyramid[i].length-1];
                        for(int i = 0; i < 9; i++) sidePyramid[i][1] = new Tile(Color.GRAY);
                }
        }

        /******************************************************************************************************
         * This function add a tile to the sidePyramid if the pyramid is full.
         * @param tile is the tile that need to be placed.
         ******************************************************************************************************/
        public void addSidePyramid(Tile tile)
        {
                if(isLeft) sidePyramid[sidePyramidIndex][0] = tile;
                else sidePyramid[sidePyramidIndex][1] = tile;
                sidePyramidIndex++;
        }

        public boolean isOkSidePyramid(Tile tile)
        {
                if(sidePyramidIndex == 0) return true;
                else if (sidePyramid[sidePyramidIndex - 1][0].isSameColor(tile) || sidePyramid[sidePyramidIndex - 1][1].isSameColor(tile) || sidePyramid[sidePyramidIndex - 1][0].isSameColor(new Tile(new Color(116, 78, 59))) || sidePyramid[sidePyramidIndex - 1][1].isSameColor(new Tile(new Color(116, 78, 59))))
                                return true;
                return false;
        }

        /*************************************************
         * This function is a getter to pyramid.
         * @return This function is a getter to pyramid.
         ************************************************/
        public Tile[][] getPyramid() { return pyramid; }

        /*************************************************
         * This function is a getter to approachableTile.
         * @return approachableTile.
         *************************************************/
        public ApproachableTile getApproachableTile() { return approachableTile; }

        /*********************************************
         * This function is a getter to sidePyramid.
         * @return sidePyramid.
         *********************************************/
        public Tile[][] getSidePyramid() { return sidePyramid; }

        /*****************************************
         * This function is a getter to isLeft.
         * @return isLeft.
         ****************************************/
        public boolean isLeft() { return isLeft; }

        /**************************************************
         * This function is a getter to sidePyramidIndex.
         * @return sidePyramidIndex.
         *************************************************/
        public int getSidePyramidIndex() { return sidePyramidIndex; }
}
