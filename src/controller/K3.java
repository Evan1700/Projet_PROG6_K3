package controller;

import game.*;
import player.*;
import view.UI;

import java.awt.*;
import java.util.*;
import java.util.List;

import java.sql.Timestamp;
import java.time.Duration;

/*******************************
 * @author LEONARD VALENTIN
 * @author PEREIRA EVAN
 * @author PRUVOT AXEL
 * @author KONE ZEINABOU BISSI
 * @author MAHAMAT TAHIR ALI
 * @version 0.3
 ******************************/
public class K3
{
    /********************************************************************************
     * We just define the color brown because it does not have a shortcut for it.
     *******************************************************************************/
    private static final Color BROWN = new Color(116,78,59);

    /**************************************************************************************************************
     * tiles is the a list of 9 tile from each color. It represents the bag with all the tiles in the real game.
     **************************************************************************************************************/
    private static List<Tile> tiles;

    /************************************************************************************************************************************************************************
     * allPlayer is a list of player that contains every new player, and that start with the 3 types of AI for the player 1 follow by the 3 types of AI for the player 2.
     *************************************************************************************************************************************************************************/
    private static List<Player> allPlayer;
    /**********************************************************************************
     * players is a list of 2 players that contains the 2 players designated to play.
     *********************************************************************************/
    private static Player[] players;
    /**************************************************************************************
     * activePlayer is an integer that contains the index of the player that need to play.
     **************************************************************************************/
    private static int activePlayer;
    /******************************************************************
     * playersPyramid contains the two pyramids of the player playing.
     ******************************************************************/
    private static PlayerPyramid[] playersPyramid;

    /*****************************************************************************
     * playerTime contains the two time that player took to make their pyramid.
     *****************************************************************************/
    private static long[] playerTime;
    /*****************************************************************************
     * startTime contains the start time of the building pyramid of the players.
     *****************************************************************************/
    private static Timestamp startTime;
    /************************************************************************
     * endTime contains the end time of the building pyramid of the players.
     ************************************************************************/
    private static Timestamp endTime;

    /********************************************************************************
     * isFinish is a boolean that is true if one of the player can not play anymore.
     ********************************************************************************/
    private static boolean isFinish;

    /************************************
     * ui contains the user interface.
     ************************************/
    private static UI ui;

    /************************************************************************************
     * index is an int used to keep in the memory the choice of the player on one panel.
     ************************************************************************************/
    private static int index;

    /****************************************************************************************
     * coordinate is an int used to keep in the memory the choice of the player on one panel.
     *****************************************************************************************/
    private static Coordinates coordinate;

    /*********************
     * pyramid is the K3.
     *********************/
    private static Pyramid pyramid;

    /*****************************************************
     * colorblind indicates if colorblind mode is enabled.
     *****************************************************/
    private static int colorblind;

    /******************************************************************
     * sidePyramidIndex is where the next tile goes on the sidePyramid.
     ******************************************************************/
    private int sidePyramidIndex;


    //--------------------------INITIALISATION--------------------------

    /*************************************************
     * This constructor initialise all the variables.
     ************************************************/
    public K3()
    {
        tiles = new ArrayList<>();

        players = new Player[2];
        playerTime = new long[2];

        allPlayer = new ArrayList<>();

        activePlayer = 0;
        colorblind = 0;
        playersPyramid = new PlayerPyramid[2];
        playersPyramid[0] = new PlayerPyramid();
        playersPyramid[1] = new PlayerPyramid();

        pyramid = new Pyramid();

        coordinate = new Coordinates(-1,-1);

        ui = new UI(this);

        sidePyramidIndex = 0;

        if(allPlayer.size() == 0) { addAi(); }
    }

    //-------------------------------GAME-------------------------------

    /*****************************************************************************************************************************************
     * This function test if a name has already been used.
     * @param name the name to test.
     * @return an integer that is the index of the name if it's already exists in allPlayer or -1 if the player does not exist in allPlayer.
     *****************************************************************************************************************************************/
    public static int alreadyExist(String name)
    {
        for(int i=0;i<allPlayer.size();i++)
        {
            if(allPlayer.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    //-------------------------------GAME-------------------------------
                 //--------------INITIALISATION----------------

    /*************************************************************************************
     * This function sets the game according to the parameter chosen by the player(s).
     *************************************************************************************/
    public static void initGame()
    {
        index = -1;

        if((UI.getNameP1TextField().getText().equals("") && !UI.getAiCheckBoxP1().isSelected()) || (UI.getNameP2TextField().getText().equals("") && !UI.getAiCheckBoxP2().isSelected()))
        {
            UI.errorPlayerName();
            return;
        }

        if(UI.getAiCheckBoxP1().isSelected())
        {
            String typeIaStringP1 = (String) UI.getTypeAiComboBoxP1().getSelectedItem();
            switch (Objects.requireNonNull(typeIaStringP1))
            {
                case "IA Aléatoire":
                    players[0] = allPlayer.get(0);
                    break;
                case "IA Semi-Aleatoire":
                    players[0] = allPlayer.get(1);
                    break;
                case "IA Difficile":
                    players[0] = allPlayer.get(2);
                    break;
                default:
                    break;
            }
        }
        else
        {
            int index = alreadyExist(UI.getNameP1TextField().getText());
            if(index == -1)
            {
                allPlayer.add(new Player(UI.getNameP1TextField().getText()));
                players[0] = allPlayer.get(allPlayer.size()-1);
            }
            else
            {
                 players[0] = allPlayer.get(index);
            }
        }

        if(UI.getAiCheckBoxP2().isSelected())
        {
            String typeIaStringP2 = (String) UI.getTypeAiComboBoxP2().getSelectedItem();
            switch (Objects.requireNonNull(typeIaStringP2))
            {
                case "IA Aléatoire":
                    players[1] = allPlayer.get(1);
                    break;
                case "IA Semi-Aleatoire":
                    players[1] = allPlayer.get(4);
                    break;
                case "IA Difficile":
                    players[1] = allPlayer.get(5);
                    break;
                default:
                    break;
            }
        }
        else
        {
            int index = alreadyExist(UI.getNameP2TextField().getText());
            if(index == -1)
            {
                allPlayer.add(new Player(UI.getNameP2TextField().getText()));
                players[1] = allPlayer.get(allPlayer.size()-1);
            }
            else
            {
                players[1] = allPlayer.get(index);
            }
        }

        addPlayers(players[0], players[1]);

        playerTime[0] = 0;
        playerTime[1] = 0;

        initTiles();

        initBaseTilePlayer();

        ui.initPanel();
        ui.initGamePanel();

        ui.showGamePanel();
        ui.updateIU();

        if(players[activePlayer].isAi()) firstPartAi();
    }

    /*****************************************************************
     * This function put in allPlayer the AI for both Player 1 and 2.
     * Except if the game is load.
     *****************************************************************/
    public void addAi()
    {
        allPlayer.add(new AIRandom("IA Aléatoire Joueurs 1",this,0));
        allPlayer.add(new AIRandom("IA Aléatoire Joueurs 2",this,1));
    }

    /**********************************************************************
     * This function placed the two given players in activePlayer.
     * @param p1 is the player placed at the first index of activePlayer.
     * @param p2 is the player placed at the second index of activePlayer.
     **********************************************************************/
    public static void addPlayers(Player p1, Player p2)
    {
        players[0] = p1;
        players[1] = p2;
    }

    /*****************************************************************
     * This function fill tiles with 9 tiles from each usual color.
     *****************************************************************/
    public static void initTiles()
    {
        for (int i = 0; i < 9; i++)
        {
            tiles.add(new Tile(Color.YELLOW));
            tiles.add(new Tile(Color.BLACK));
            tiles.add(new Tile(Color.RED));
            tiles.add(new Tile(Color.BLUE));
            tiles.add(new Tile(Color.GREEN));
        }
        generateK3Base();
    }

    /************************************************************************
     * This function generate 9 random tile with at least 4 different color.
     * These tile will be the base of the K3.
     ************************************************************************/
    public static void generateK3Base()
    {
        List<Tile> k3base = new ArrayList<Tile>();
        List<Tile> uniqueTiles = new ArrayList<Tile>();
        Random r = new Random();
        int index;

        while(uniqueTiles.size()<4)
        {
            while(k3base.size()<9)
            {
                index = r.nextInt(tiles.size());
                k3base.add(tiles.get(index));
                tiles.remove(index);
            }

            for(Tile t : k3base)
            {
                if(!uniqueTiles.contains(t)) uniqueTiles.add(t);
            }

            if(uniqueTiles.size()<4)
            {
                while(k3base.size()>0)
                {
                    tiles.add(k3base.get(0));
                    k3base.remove(0);
                }
            }
        }

        pyramid.initPyramid(k3base);
    }

    /**********************************************************************************************************************
     * This function generate 3 by 3 tiles for each players for 5 time each, then 2 by 2 for each players for 1 time each.
     * Finally add 2 of each special tile in the baseTile of each playersPyramid.
     ***********************************************************************************************************************/
    public static void initBaseTilePlayer()
    {
        Random r = new Random();
        int index;

        for(int i = 0; i < 10;i++)
        {
            for(int j =0;j<3;j++)
            {
                index = r.nextInt(tiles.size());
                playersPyramid[activePlayer].addBaseTile(tiles.get(index));
                tiles.remove(index);
            }
            activePlayer = (activePlayer +1)%2;
        }

        for(int i = 0; i < 2;i++)
        {
            for(int j =0;j<2;j++)
            {
                index = r.nextInt(tiles.size());
                playersPyramid[activePlayer].addBaseTile(tiles.get(index));
                tiles.remove(index);
            }
            activePlayer = (activePlayer +1)%2;
        }

        playersPyramid[0].addBaseTile(new Tile(Color.WHITE));
        playersPyramid[0].addBaseTile(new Tile(Color.WHITE));
        playersPyramid[0].addBaseTile(new Tile(BROWN));
        playersPyramid[0].addBaseTile(new Tile(BROWN));

        playersPyramid[1].addBaseTile(new Tile(Color.WHITE));
        playersPyramid[1].addBaseTile(new Tile(Color.WHITE));
        playersPyramid[1].addBaseTile(new Tile(BROWN));
        playersPyramid[1].addBaseTile(new Tile(BROWN));

    }

                 //----------------FIRSTPART-----------------

    /****************************************************************
     * This function start the timer for the building of 1 pyramid.
     ***************************************************************/
    public void startTimer() { startTime = new Timestamp(System.currentTimeMillis()); }

    /******************************************************
     * This function end the timer for building 1 pyramid.
     * And add the timer to playerTime.
     ********************************************************/
    public static void endTime()
    {
        if(!playersPyramid[activePlayer].getBaseTile().isEmpty()) return;

        endTime = new Timestamp(System.currentTimeMillis());

        if(players[activePlayer].isAi())
        {
            playerTime[activePlayer] =  100000;
            ui.getTimeLabel(activePlayer).setText("Temps : " + 100000 + "s");
        }
        else
        {
            playerTime[activePlayer] = endTime.getTime() - startTime.getTime() ;
            Duration elapsedDuration = Duration.between(startTime.toInstant(), endTime.toInstant());
            ui.getTimeLabel(activePlayer).setText("Temps : " + Double.toString((double) elapsedDuration.toNanos()/Math.pow(10,9)) + "s");
        }

        activePlayer = (activePlayer+1)%2;
        ui.changeFinishButton();
        ui.getInitPanel().changePlayer();
        ui.updateIU();

        if (activePlayer == 0)
        {
            startSecondPart();
            return;
        }

        if(players[activePlayer].isAi()) firstPartAi();
    }

    /******************************************************
     * This function set index.
     * @param index is the index the player want to play.
     ******************************************************/
    public void indexMemoryParOne(int index)
    {
        K3.index = index-1;
        resetCoordinates();
    }

    /*****************************
     * This function reset index.
     *****************************/
    public void resetIndex() { index = -1; }

    /**********************************************************************************************************************
     * This function add the tile from their baseTile at the index set by indexMemory to the coordinate in the pyramid.
     * @param coordinates is the coordinate to place the tile wanted in the pyramid.
     **********************************************************************************************************************/
    public void addPlayerPyramid(Coordinates coordinates)
    {
        if(index == -1 && !(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x].isSameColor(new Tile(Color.GRAY))))
        {
            System.out.println("test");
            removePlayerPyramid(coordinates);
            playersPyramid[activePlayer].updateBaseTile();
            return;
        }
        if(index == -1 && playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x].isSameColor(new Tile(Color.GRAY))) return;
        if(!playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x].isSameColor(new Tile(Color.GRAY)) && index != -1)
        {
            playersPyramid[activePlayer].addBaseTile(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x]);

        }

        playersPyramid[activePlayer].addPyramid(index, coordinates);
        playersPyramid[activePlayer].updateBaseTile();

        index = -1;

        ui.updateIU();
    }

    /*********************************************************************************************
     * This function remove a tile from a pyramid of the player to place it back to his baseTile.
     * @param coordinates the coordinate of the tile to remove.
     *********************************************************************************************/
    public void removePlayerPyramid(Coordinates coordinates)
    {
        playersPyramid[activePlayer].addBaseTile(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x]);
        playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x] = new Tile(Color.GRAY);
        ui.updateIU();
    }

    /***********************************************************************
     * This function calculate the pyramid and create it for an AI player.
     **********************************************************************/
    public static void firstPartAi()
    {
        players[activePlayer].createPyramid();
        endTime();
    }

                 //-----------------SECONDPART------------------

    /******************************************************
     * This function initialise the next part of the game.
     ******************************************************/
    public static void startSecondPart()
    {
        ui.getPanelP1().nextPart();
        ui.getPanelP2().nextPart();
        ui.removeFinishButon();
        ui.showMainPanel();
        if(playerTime[0]>playerTime[1]) activePlayer = 1;
        else activePlayer = 0;

        ui.updateIU();
    }

    /************************************************************************
     * This function play for the AI when it need to place a tile on the K3.
     ***********************************************************************/
    public void AiPartTwo() { players[activePlayer].playPyramid(); }

    /************************************************************************
     * This function play for the AI when it need to take a tile of the opponents.
     ***********************************************************************/
    public void AiPartThree() { players[(activePlayer+1)%2].penalty(); }

    /************************************************************************
     * This function play for the AI when it need to place a tile on the side pyramid.
     ***********************************************************************/
    public void AiPartFour() { players[activePlayer].playSidePyramid(); }

    /******************************************************
     * This function set index.
     * @param coordinates is the index the player want to play.
     ******************************************************/
    public void coordinatesMemory(Coordinates coordinates)
    {
        if(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x].isSameColor(new Tile(Color.WHITE)) && playersPyramid[activePlayer].isApproachableTile(coordinates))
        {
            playersPyramid[activePlayer].playPyramid(coordinates);
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
            return;
        }
        coordinate.x = coordinates.x;
        coordinate.y = coordinates.y;
        resetIndex();
        ui.updateIU();
    }


    /******************************************************
     * This function set index.
     * @param index is the index the player want to play.
     ******************************************************/
    public void indexMemoryParTwo(int index)
    {
        if(playersPyramid[activePlayer].getBonusTile().get(index).isSameColor(new Tile(Color.WHITE)))
        {
            playersPyramid[activePlayer].playBonusTile(index);
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
            return;
        }
        K3.index = index;
        resetCoordinates();
        ui.updateIU();
    }

    /*****************************
     * This function reset index.
     *****************************/
    public void resetCoordinates()
    {
        coordinate.x = -1 ;
        coordinate.y = -1;
    }

    /*********************************************************
     * This function add to the K3 the wanted Tile.
     * @param coordinates is the coordinate to place the tile.
     **********************************************************/
    public void addPyramid(Coordinates coordinates)
    {

        System.out.println("index: "+index);
        System.out.println("coordonée pyramid: "+coordinates.x+" "+coordinates.y);
        System.out.println("coordonée player: "+coordinate.x+" "+coordinate.y);

        /*for(int i = 0;i<9;i++) pyramid.getPyramid()[i][0] = new Tile(new Color(116,78,59));
        initSidePyramid();*/

        if(!coordinate.isEquals(new Coordinates(-1, -1)) && index == -1)
        {
            System.out.println(pyramid.isOk(playersPyramid[activePlayer].getPyramid()[coordinate.y][coordinate.x],coordinates));
            System.out.println(playersPyramid[activePlayer].isApproachableTile(coordinate));
            if(pyramid.isOk(playersPyramid[activePlayer].getPyramid()[coordinate.y][coordinate.x],coordinates) && playersPyramid[activePlayer].isApproachableTile(coordinate))
            {
                pyramid.add(playersPyramid[activePlayer].playPyramid(coordinate),coordinates);
                activePlayer = (activePlayer+1)%2;
                if(coordinates.isEquals(new Coordinates(0,9)))
                {
                    initSidePyramid();
                    return;
                }
            }
        }
        else if(coordinate.isEquals(new Coordinates(-1, -1)) && index != -1)
        {
            System.out.println(pyramid.isOk(playersPyramid[activePlayer].getBonusTile().get(index),coordinates));
            if(pyramid.isOk(playersPyramid[activePlayer].getBonusTile().get(index),coordinates))
            {
                pyramid.add(playersPyramid[activePlayer].playBonusTile(index),coordinates);
                activePlayer = (activePlayer+1)%2;
                if(coordinates.isEquals(new Coordinates(0,9)))
                {
                    initSidePyramid();
                    return;
                }
            }
        }

        if(pyramid.getPyramid()[coordinates.y-1][coordinates.x].isSameColor(pyramid.getPyramid()[coordinates.y-1][coordinates.x+1]) && (!coordinate.isEquals(new Coordinates(-1, -1)) || index != -1))
        {
            activePlayer = (activePlayer+1)%2;
            ui.getPanelP2().nextPart();
            ui.getPanelP1().nextPart();
            ui.getMainPanel().nextPart();
        }

        resetCoordinates();
        resetIndex();
        ui.updateIU();
    }

    /***************************************************************************
     * This function handle the case when a player have a penalty.
     * @param coordinates is the coordinate of the tile the other player want.
     ***************************************************************************/
    public void penaltyPyramid(Coordinates coordinates)
    {
        if(playersPyramid[activePlayer].getApproachableTile().contains(coordinates))
        {
            playersPyramid[(activePlayer + 1) % 2].addBonusTile(playersPyramid[activePlayer].playPyramid(coordinates));
            endPenalty();
        }
    }

    /***************************************************************************
     * This function handle the case when a player have a penalty.
     * @param index is the coordinate of the tile the other player want.
     ***************************************************************************/
    public void penaltyBonusTile(int index)
    {
        playersPyramid[(activePlayer+1)%2].addBonusTile(playersPyramid[activePlayer].playBonusTile(index));
        endPenalty();
    }

    /**********************************************************
     * This function put back the game into the state to play.
     **********************************************************/
    public void endPenalty()
    {
        ui.getPanelP2().previousPart();
        ui.getPanelP1().previousPart();
        ui.getMainPanel().previousPart();
        activePlayer = (activePlayer+1)%2;
        ui.updateIU();
    }

    /*****************************************************************
     * This function test if the active player can play.
     * @return true if the player can play or false if he can't play.
     *****************************************************************/
    public boolean canPlay()
    {
        if(sidePyramidIndex == 0)
        {
            for (Coordinates approachablePlayer : playersPyramid[activePlayer].getApproachableTile().getList()) {
                for (Coordinates approachablePyramid : pyramid.getApproachableTile().getList()) {
                    if (this.getPyramid().isOk(playersPyramid[activePlayer].getPyramid()[approachablePlayer.y][approachablePlayer.x], approachablePyramid))
                        return true;
                }
            }
            for (Tile approachablePlayer : playersPyramid[activePlayer].getBonusTile()) {
                for (Coordinates approachablePyramid : pyramid.getApproachableTile().getList()) {
                    if (this.getPyramid().isOk(approachablePlayer, approachablePyramid))
                        return true;
                }
            }
        }
        else
        {
            for (Coordinates approachablePlayer : playersPyramid[activePlayer].getApproachableTile().getList())
            {
                    if (this.getPyramid().isOkSidePyramid(playersPyramid[activePlayer].getPyramid()[approachablePlayer.y][approachablePlayer.x])) return true;
            }
            for (Tile approachablePlayer : playersPyramid[activePlayer].getBonusTile()) {
                    if (this.getPyramid().isOkSidePyramid(approachablePlayer))
                        return true;
            }
        }
        return false;
    }

    /*************************************
     * This function enable colorblind.
     *************************************/
    public void setColorblind(){ colorblind = 1; }

    /*************************************
     * This function disable colorblind.
     *************************************/
    public void disableColorblind(){ colorblind = 0; }

                 //-----------------THIRDPART------------------

    /****************************************************************
     * This function initialise the side pyramid if the K3 is full.
     ****************************************************************/
    public void initSidePyramid()
    {
        pyramid.initSidePyramid(ui.isLeft()==0);
        ui.getPanelP2().nextPart();
        ui.getPanelP1().nextPart();
        ui.getPanelP2().nextPart();
        ui.getPanelP1().nextPart();
        ui.getMainPanel().nextPart();
    }

    /*********************************************************************************************
     * This function add to the side pyramid when the K3 is full, from the pyramid of the player.
     * @param coordinates is the coordinate of the wanted played tile in the pyramid.
     *********************************************************************************************/
    public void addSidePyramidPyramid(Coordinates coordinates)
    {
        if(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x].isSameColor(new Tile(Color.WHITE)))
        {
            playersPyramid[activePlayer].playPyramid(coordinates);
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
            return;
        }

        if(pyramid.isOkSidePyramid(playersPyramid[activePlayer].getPyramid()[coordinates.y][coordinates.x]))
        {
            pyramid.addSidePyramid(playersPyramid[activePlayer].playPyramid(coordinates));
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
        }
    }

    /*********************************************************************************************
     * This function add to the side pyramid when the K3 is full, from the bonus tile of the player.
     * @param index is the index of the wanted played tile in bonus tile.
     *********************************************************************************************/
    public void addSidePyramidBonusTile(int index)
    {
        if(playersPyramid[activePlayer].getBonusTile().get(index).isSameColor(new Tile(Color.WHITE)))
        {
            playersPyramid[activePlayer].playBonusTile(index);
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
            return;
        }

        if(pyramid.isOkSidePyramid(playersPyramid[activePlayer].getBonusTile().get(index)))
        {
            pyramid.addSidePyramid(playersPyramid[activePlayer].playBonusTile(index));
            activePlayer = (activePlayer+1)%2;
            ui.updateIU();
        }
    }


                 //---------------------END---------------------

    /************************************************
     * This function Stop the game and reset it.
     * Add to both player a lose or a victory.
     * And display the parameter menu again.
     ***********************************************/
    public void endGame()
    {
        players[activePlayer].addLose();
        players[(activePlayer+1)%2].addWin();
        ui.showIntroPanel();
        ui.endGamePopUp(players[(activePlayer+1)%2].getName());
        //ui.updateIU();
        resetGame();
    }

    //-------------------------------RESET------------------------------

    /****************************************************
     * This function reset the game when a game stopped.
     *****************************************************/
    public void resetGame()
    {
        pyramid = new Pyramid();
        playersPyramid = new PlayerPyramid[2];
        playersPyramid[0] = new PlayerPyramid();
        playersPyramid[1] = new PlayerPyramid();
        playerTime = new long[2];
        ui.getMainPanel().resetPart();
        ui.getPanelP1().resetPart();
        ui.getPanelP2().resetPart();
    }

    //-------------------------------GETTER------------------------------

    /*********************************************
     * This function is a getter to allPlayers.
     * @return allPlayers.
     *********************************************/
    public List<Player> getAllPlayer() { return allPlayer; }

    /*********************************************
     * This function is a getter to ui.
     * @return ui.
     *********************************************/
    public static UI getUi() { return ui; }

    /******************************************
     * This function is a getter to players.
     * @return players.
     *****************************************/
    public Player[] getPlayers() { return players; }

    /***********************************************
     * This function is a getter to activePlayer.
     * @return activePlayer.
     **********************************************/
    public int getActivePlayer() { return activePlayer; }

    /************************************************
     * This function is a getter to playersPyramid.
     * @return playersPyramid.
     ************************************************/
    public PlayerPyramid[] getPlayersPyramid() { return playersPyramid; }

    /*******************************************
     * This function is a getter to playerTime.
     * @return playerTime.
     *******************************************/
    public long[] getPlayerTime() { return playerTime; }

    /*****************************************
     * This function is a getter to isFinish.
     * @return isFinish.
     ******************************************/
    public boolean isFinish() { return isFinish; }

    /****************************************
     * This function is a getter to pyramid.
     * @return pyramid
     ***************************************/
    public Pyramid getPyramid(){ return pyramid; }

    /****************************************
     * This function is a getter to colorblind.
     * @return colorblind
     ***************************************/
    public int isColorblind(){return colorblind;}

    //-------------------------------MAIN--------------------------------

    /*****************************************
     * This is the main.
     * @param args not used in this program.
     ******************************************/
    public static void main(String[] args) { K3 k3 = new K3(); }

}