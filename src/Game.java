import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {
    final int WHITE = 6;
    final int JOKER = 0;
    final int YELLOW = 1;
    final int BLACK = 2;
    final int RED = 3;
    final int BLUE = 4;
    final int GREEN = 5;

    List<Integer> normalTile;
    List<int[]> pyramid;
    Player[] players;

    int actifPlayer;

    public Game()
    {
        normalTile = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++)
        {
            normalTile.add(YELLOW);
            normalTile.add(BLACK);
            normalTile.add(RED);
            normalTile.add(BLUE);
            normalTile.add(GREEN);
        }

        players = new Player[2];

        pyramid = new ArrayList<int[]>();

        Random r = new Random();
        actifPlayer = r.nextInt(2);

    }

    public void initPyramid()
    {
        Random r = new Random();
        int indexNormalTile, indexPyramid;
        int variety = 0;

        int[] colors = new int[5];
        for(int i = 0;i<5;i++) colors[i] = 0;

        pyramid.add(new int[9]);
        for(int i = 0;i<9;i++)
        {
            indexNormalTile = r.nextInt(normalTile.size());
            pyramid.get(0)[i] = normalTile.get(indexNormalTile);
            normalTile.remove(indexNormalTile);
            if(colors[pyramid.get(0)[i]-1]==0)
            {
                variety++;
            }
            colors[pyramid.get(0)[i]-1]++;
        }

        while(variety<4)
        {
            indexNormalTile = r.nextInt(normalTile.size());
            if(colors[normalTile.get(indexNormalTile)-1] == 0)
            {
                indexPyramid = r.nextInt(9);
                if (colors[pyramid.get(0)[indexPyramid]-1]>1)
                {
                    variety++;
                    colors[pyramid.get(0)[indexPyramid]-1]--;
                    normalTile.add(colors[pyramid.get(0)[indexPyramid]-1]);
                    pyramid.get(0)[indexPyramid] = normalTile.get(indexNormalTile);
                    normalTile.remove(indexNormalTile);
                    colors[pyramid.get(0)[indexPyramid]-1]++;
                }
            }
        }

        for(int i =8;i>0;i--) pyramid.add(new int[i]);
    }

    public void initPlayerPyramid()
    {
        while(!players[0].isBaseTileEmpty() || !players[1].isBaseTileEmpty())
        {
            players[actifPlayer].createMyPyramid();
            actifPlayer = (actifPlayer+1)%2;
        }
    }

    public void nextRound()
    {
        printPyramid();
        System.out.println("Tour de "+ players[actifPlayer].getName());
        players[actifPlayer].placeTile();
        if(!players[actifPlayer].canPlay()) endGame();
    }

    public void endGame()
    {
        System.out.println("Perdant " + players[actifPlayer].getName());
        players[actifPlayer].addLose();
        System.out.println("Gagnant" + players[(actifPlayer+1)%2].getName());
        players[(actifPlayer+1)%2].addWin();
    }

    public void addPlayers(Player p1, Player p2)
    {
        players[0] = p1;
        players[1] = p2;
    }

    public void generateBaseTile()
    {
        Random r = new Random();
        int index;

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<3;j++)
            {
                index = r.nextInt(normalTile.size());
                players[actifPlayer].addBaseTile(normalTile.get(index));
                normalTile.remove(index);
            }
            actifPlayer = (actifPlayer+1)%2;
        }

        for(int i=0;i<2;i++)
        {
            for(int j=0;j<2;j++)
            {
                players[actifPlayer].addBaseTile(WHITE);
                players[actifPlayer].addBaseTile(JOKER);

                index = r.nextInt(normalTile.size());
                players[actifPlayer].addBaseTile(normalTile.get(index));
                normalTile.remove(index);
            }
            actifPlayer = (actifPlayer+1)%2;
        }
    }

    public void printPyramid()
    {
        int rows = 9, coef = 1;

        for(int i = 0; i < rows; i++)
        {
            for(int space = 1; space < rows - i; ++space) System.out.print("  ");

            for(int j = 0; j <= i; j++)
            {
                if (pyramid.size()-1<8-i)
                    coef = -1;
                else
                    coef = pyramid.get(8-i)[j];

                System.out.printf("%4d", coef);
            }

            System.out.println();
        }
    }

    public void penalty(int height, int width)
    {
        if(!(pyramid.get(height-1)[width]==pyramid.get(height-1)[width+1])) return;

        Scanner scanner = new Scanner(System.in);
        players[(actifPlayer+1)%2].printMyPyramid();
        players[(actifPlayer+1)%2].printTileReady();
        players[(actifPlayer+1)%2].printBonusTileReady();

        System.out.println(players[actifPlayer].getName()+" Choisir bonus=0 ou rdy=1");
        int entry = scanner.nextInt();
        if(entry == 0)
        {
            System.out.println(players[actifPlayer].getName()+" Choisir la tile avec index");
            entry = scanner.nextInt();
            players[actifPlayer].addBonusTileReady(players[(actifPlayer+1)%2].getBonusTileReady(entry));
            players[(actifPlayer+1)%2].removeBonusTileReady(entry);
        }
        else
        {
            System.out.println(players[actifPlayer].getName()+" Choisir la tile avec index");
            entry = scanner.nextInt();
            players[actifPlayer].addBonusTileReady(players[(actifPlayer+1)%2].getTileReady(entry));
            players[(actifPlayer+1)%2].removeTileReady(entry);
        }

    }

    public boolean addPyramid(int c, int height, int width)
    {
        if(c == -1) return true;
        if(pyramid.get(height)[width]==-1 || pyramid.get(height-1)[width]==-1 || pyramid.get(height-1)[width+1]==-1 || (pyramid.get(height-1)[width]!=c && pyramid.get(height-1)[width+1]!=c && c!=0)) return false;

        pyramid.get(height)[width] = c;
        actifPlayer = (actifPlayer+1)%2;

        return true;
    }

}