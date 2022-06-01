package player;

import controller.K3;
import game.Coordinates;
import game.PlayerPyramid;
import game.Pyramid;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AIRandom extends Player
{
    /*********************
     * pyramid is the K3.
     *********************/
    Pyramid pyramid;

    /************************************************
     * numberPlayer is the number of the player in the K3 players.
     ************************************************/
    int numberPlayer;

    /**********************************************************
     * This pyramid is the K3, the one the AI need to play on.
     *********************************************************/
    K3 k3;

    /********************************************************
     * This constructor initialise every variable.
     * @param name is the name of the player.
     * @param k3 is the controller.
     * @param numberPlayer is the number of the player, in players.
     ********************************************************/
    public AIRandom(String name, K3 k3, int numberPlayer)
    {
        super(name);

        pyramid = k3.getPyramid();

        this.k3 = k3;

        this.numberPlayer = numberPlayer;
    }

    /*****************************************************************
     * This function say if the player is an AI.
     * @return boolean true if the player is an AI and false if not.
     *****************************************************************/
    @Override
    public boolean isAi(){ return true; }

    /*******************************************************************
     * This function generate a random pyramid and create it.
     *******************************************************************/
    @Override
    public void createPyramid()
    {
        Random r = new Random();
        List<Coordinates> coordinates = new ArrayList<Coordinates>();
        int indexCoordinate, indexTile;

        for(int i=0; i < 6;i++)
        {
            for(int j=0;j<6-i;j++) { coordinates.add(new Coordinates(i,j)); }
        }

        while(!k3.getPlayersPyramid()[numberPlayer].getBaseTile().isEmpty())
        {
            indexTile = r.nextInt(k3.getPlayersPyramid()[numberPlayer].getBaseTile().size());

            indexCoordinate = r.nextInt(coordinates.size());

            k3.getPlayersPyramid()[numberPlayer].addPyramid(indexTile,coordinates.get(indexCoordinate));

            coordinates.remove(indexCoordinate);

            K3.getUi().updateIU();
        }
    }

    /***************************************
     * This function play a tile on the K3.
     **************************************/
    @Override
    public void playPyramid()
    {
        Random r = new Random();

        boolean fromPyramid;

        fromPyramid = r.nextInt()%2 == 0;

        if(fromPyramid)
        {
            for(Coordinates coordinatesPlayer: k3.getPlayersPyramid()[numberPlayer].getApproachableTile().getList())
            {
                for(Coordinates coordinatesPyramid: k3.getPyramid().getApproachableTile().getList())
                {
                    if(k3.getPyramid().isOk(k3.getPlayersPyramid()[numberPlayer].getPyramid()[coordinatesPlayer.y][coordinatesPlayer.x], coordinatesPyramid))
                    {
                        k3.coordinatesMemory(coordinatesPlayer);
                        k3.addPyramid(coordinatesPyramid);
                        return;
                    }
                }
            }

            for(int indexPlayer = 0; indexPlayer < k3.getPlayersPyramid()[numberPlayer].getBonusTile().size();indexPlayer++)
            {
                for(Coordinates coordinatesPyramid: k3.getPyramid().getApproachableTile().getList())
                {
                    if(k3.getPyramid().isOk(k3.getPlayersPyramid()[numberPlayer].getBonusTile().get(indexPlayer), coordinatesPyramid))
                    {
                        k3.indexMemoryParTwo(indexPlayer);
                        k3.addPyramid(coordinatesPyramid);
                        return;
                    }
                }
            }
        }
        else
        {
            for(int indexPlayer = 0; indexPlayer < k3.getPlayersPyramid()[numberPlayer].getBonusTile().size();indexPlayer++)
            {
                for(Coordinates coordinatesPyramid: k3.getPyramid().getApproachableTile().getList())
                {
                    if(k3.getPyramid().isOk(k3.getPlayersPyramid()[numberPlayer].getBonusTile().get(indexPlayer), coordinatesPyramid))
                    {
                        k3.indexMemoryParTwo(indexPlayer);
                        k3.addPyramid(coordinatesPyramid);
                        return;
                    }
                }
            }

            for(Coordinates coordinatesPlayer: k3.getPlayersPyramid()[numberPlayer].getApproachableTile().getList())
            {
                for(Coordinates coordinatesPyramid: k3.getPyramid().getApproachableTile().getList())
                {
                    if(k3.getPyramid().isOk(k3.getPlayersPyramid()[numberPlayer].getPyramid()[coordinatesPlayer.y][coordinatesPlayer.x], coordinatesPyramid))
                    {
                        k3.coordinatesMemory(coordinatesPlayer);
                        k3.addPyramid(coordinatesPyramid);
                        return;
                    }
                }
            }
        }
    }

    /**********************************
     * This function play the penalty.
     *********************************/
    @Override
    public void penalty()
    {
        Random r = new Random();

        boolean fromPyramid;

        fromPyramid = r.nextInt()%2 == 0;

        if(fromPyramid || k3.getPlayersPyramid()[k3.getActivePlayer()].getBonusTile().size()==0) k3.penaltyPyramid(k3.getPlayersPyramid()[k3.getActivePlayer()].getApproachableTile().getList().get(r.nextInt(k3.getPlayersPyramid()[k3.getActivePlayer()].getApproachableTile().getList().size())));
        else k3.penaltyBonusTile(r.nextInt(k3.getPlayersPyramid()[k3.getActivePlayer()].getBonusTile().size()));
    }

    /***************************************************
     * This function play the part when the K3 is full.
     **************************************************/
    @Override
    public void playSidePyramid()
    {
        Random r = new Random();

        boolean fromPyramid;

        fromPyramid = r.nextInt()%2 == 0;

        if(fromPyramid)
        {
            for(Coordinates coordinatesPlayer: k3.getPlayersPyramid()[numberPlayer].getApproachableTile().getList())
            {
                    if(k3.getPyramid().isOkSidePyramid(k3.getPlayersPyramid()[numberPlayer].getPyramid()[coordinatesPlayer.y][coordinatesPlayer.x]))
                    {
                        k3.addSidePyramidPyramid(coordinatesPlayer);
                        return;
                    }
            }

            for(int indexPlayer = 0; indexPlayer < k3.getPlayersPyramid()[numberPlayer].getBonusTile().size();indexPlayer++)
            {
                if(k3.getPyramid().isOkSidePyramid(k3.getPlayersPyramid()[numberPlayer].getBonusTile().get(indexPlayer)))
                {
                    k3.addSidePyramidBonusTile(indexPlayer);
                    return;
                }
            }
        }
        else
        {
            for(int indexPlayer = 0; indexPlayer < k3.getPlayersPyramid()[numberPlayer].getBonusTile().size();indexPlayer++)
            {
                if(k3.getPyramid().isOkSidePyramid(k3.getPlayersPyramid()[numberPlayer].getBonusTile().get(indexPlayer)))
                {
                    k3.addSidePyramidBonusTile(indexPlayer);
                    return;
                }
            }

            for(Coordinates coordinatesPlayer: k3.getPlayersPyramid()[numberPlayer].getApproachableTile().getList())
            {
                if(k3.getPyramid().isOkSidePyramid(k3.getPlayersPyramid()[numberPlayer].getPyramid()[coordinatesPlayer.y][coordinatesPlayer.x]))
                {
                    k3.addSidePyramidPyramid(coordinatesPlayer);
                    return;
                }
            }
        }
    }

}
