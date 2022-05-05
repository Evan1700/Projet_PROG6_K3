import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller
{



    public static void main (String[] args)
    {
        Controller controller = new Controller();



        Game game = new Game();
        Player[] playersActif = new Player[2];
        playersActif[0] = new RealPlayer("Joueur 1", game);
        playersActif[1] = new RealPlayer("Joueur 2", game);
        game.initPyramid();
        game.addPlayers(playersActif[0], playersActif[1]);
        game.generateBaseTile();

        game.initPlayerPyramid();


        //game.printPyramid();
        //playersActif[0].printTileReady();
        //playersActif[0].printBonusTileReady();

        while(true)
        {
            game.nextRound();
        }


    }
}
