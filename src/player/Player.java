package player;

import game.PlayerPyramid;

public class Player
{
    /********************************************************
     * victoryNumber is the number of victory of the player.
     ********************************************************/
    int victoryNumber;
    /***************************************************
     * loseNumber is the number of lose of the player.
     ***************************************************/
    int loseNumber;
    /********************************************
     * name is the string to define the player.
     ********************************************/
    String name;

    /************************************************************************************
     * This constructor define the name and put the victoryNumber and loseNumber at 0.
     * @param name is the name given to the player.
     ************************************************************************************/
    public Player(String name)
    {
        victoryNumber = 0;
        loseNumber = 0;
        this.name = name;
    }

    /**************************************
     * This function is a getter to name.
     * @return name.
     *************************************/
    public String getName(){ return name; }

    /******************************************
     * This function add a lose to the player.
     ******************************************/
    public void addLose() { loseNumber++; }

    /*********************************************
     * This function add a victory to the player.
     ********************************************/
    public void addWin() { victoryNumber++; }

    /*****************************************************************
     * This function say if the player is an AI.
     * @return boolean true if the player is an AI and false if not.
     *****************************************************************/
    public boolean isAi(){ return false; }

    /*********************************************
     * This function is unused for a real player.
     ********************************************/
    public void createPyramid() { }

    /*********************************************
     * This function is unused for a real player.
     ********************************************/
    public void playPyramid() { }

    /*********************************************
     * This function is unused for a real player.
     ********************************************/
    public void penalty() { }

    /*********************************************
     * This function is unused for a real player.
     ********************************************/
    public void playSidePyramid() { }
}
