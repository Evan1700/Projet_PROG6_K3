package game;

import java.util.ArrayList;
import java.util.List;

public class ApproachableTile
{
    /*************************************************
     * list is the list of all the approachable tile.
     *************************************************/
    List<Coordinates> list;

    /************************************
     * This constructor initialise list.
     ************************************/
    public ApproachableTile() { list = new ArrayList<>(); }

    /************************************************
     * This function add a Coordinate to list.
     * @param coordinates is the Coordinate to add.
     ***********************************************/
    public void add(Coordinates coordinates){ list.add(coordinates); }

    /***********************************************
     * This function remove a Coordinate to list.
     * @param coordinates is the Coordinate to remove.
     *************************************************/
    public void remove(Coordinates coordinates)
    {
        int i= 0;

        while(i<list.size())
        {
            if(list.get(i).isEquals(coordinates)) list.remove(i);
            i++;
        }
    }

    /*******************************************************************
     * This function check if a given coordinate is approachable.
     * @param coordinates is the Coordinate to check.
     * @return true if the coordinates approachable, and false if not.
     *******************************************************************/
    public boolean contains(Coordinates coordinates)
    {
        int i= 0;

        while(i<list.size())
        {
            if(list.get(i).isEquals(coordinates)) return true;
            i++;
        }

        return false;
    }

    /*************************************
     * This function is a getter to list.
     * @return list.
     *************************************/
    public List<Coordinates> getList() { return list; }
}
