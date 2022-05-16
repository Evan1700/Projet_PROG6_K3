import java.util.*;

public class Controller
{
    Player[] playersActif;
    List<Player> allPlayer;
    Game game;
    IU iu;

    public Controller()
    {
        playersActif = new Player[2];
        allPlayer = new ArrayList<Player>();
    }

    public void addIA()
    {
        allPlayer.add(new IARandom("IA Aléatoire J1", game));
        allPlayer.add(new IAHalfRandom("IA Semi-Aleatoire J1", game));
        allPlayer.add(new IAHard("IA Difficile J1", game));
        allPlayer.add(new IARandom("IA Aléatoire J2", game));
        allPlayer.add(new IAHalfRandom("IA Semi-Aleatoire J2", game));
        allPlayer.add(new IAHard("IA Difficile J2", game));
    }

    public int alreadyExist(String name)
    {
        for(int i=0;i<allPlayer.size();i++)
        {
            if(allPlayer.get(i).getName().equals(name)) return i;
        }
        return -1;
    }

    void initGame()
    {
        if((iu.nameP1TextField.getText().equals("") && !iu.iaCheckBoxP1.isSelected()) || (iu.nameP2TextField.getText().equals("") && !iu.iaCheckBoxP2.isSelected()))
        {
            iu.errorPlayerName();
            return;
        }
        
        if(iu.iaCheckBoxP1.isSelected())
        {
            String typeIaStringP1 = (String) iu.typeIaComboBoxP1.getSelectedItem();
            switch (Objects.requireNonNull(typeIaStringP1))
            {
                case "IA Aléatoire":
                    playersActif[0] = allPlayer.get(0);
                    break;
                case "IA Semi-Aleatoire":
                    playersActif[0] = allPlayer.get(1);
                    break;
                case "IA Difficile":
                    playersActif[0] = allPlayer.get(2);
                    break;
                default:
                    break;
            }
        }
        else
        {
            int index = alreadyExist(iu.nameP1TextField.getText());
            if(index == -1)
            {
                allPlayer.add(new RealPlayer(iu.nameP1TextField.getText(), game));
                playersActif[0] = allPlayer.get(allPlayer.size()-1);
            }
            else
            {
                playersActif[0] = allPlayer.get(index);
            }
        }

        if(iu.iaCheckBoxP2.isSelected())
        {
            String typeIaStringP2 = (String) iu.typeIaComboBoxP2.getSelectedItem();
            switch (Objects.requireNonNull(typeIaStringP2))
            {
                case "IA Aléatoire":
                    playersActif[1] = allPlayer.get(3);
                    break;
                case "IA Semi-Aleatoire":
                    playersActif[1] = allPlayer.get(4);
                    break;
                case "IA Difficile":
                    playersActif[1] = allPlayer.get(5);
                    break;
                default:
                    break;
            }
        }
        else
        {
            int index = alreadyExist(iu.nameP2TextField.getText());
            if(index == -1)
            {
                allPlayer.add(new RealPlayer(iu.nameP2TextField.getText(), game));
                playersActif[1] = allPlayer.get(allPlayer.size()-1);
            }
            else
            {
                playersActif[1] = allPlayer.get(index);
            }
        }

        game.addPlayers(playersActif[0], playersActif[1]);

        //iu.updateIU();
        //iu.showGameDisplay();
    }

    void addIUandGame(IU iu, Game game)
    {
        this.iu = iu;
        this.game = game;
    }

    public static void main (String[] args)
    {

        Controller controller = new Controller();
        controller.addIA();
        Game game = new Game();
        IU iu = new IU(game, controller);
        controller.addIUandGame(iu, game);

        return;

    }
}
