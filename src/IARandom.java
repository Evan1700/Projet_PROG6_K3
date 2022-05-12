import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class IARandom implements  Player
{
    final String name;
    Game game;
    Integer victory, lose, gamePlayed;
    List<Integer> baseTile;
    List<int[]> pyramid;
    List<int[]> tileReady;
    List<Integer> bonusTileReady;
    int lastOffHeight, lastOffWidth;


    public IARandom(String name, Game game)
    {
        this.game = game;
        this.name = name;
        victory = 0;
        lose =0;
        gamePlayed = 0;
        baseTile = new ArrayList<Integer>();
        pyramid = new ArrayList<int[]>();
        for(int i=6;i>0;i--) pyramid.add(new int[i]);
        tileReady = new ArrayList<int[]>();
        bonusTileReady = new ArrayList<Integer>();
    }

    @Override
    public void setTileReady()
    {
        if(lastOffHeight == 0) return;

        if(lastOffWidth==0 && lastOffHeight == pyramid.size()-1)
        {
            tileReady.add(new int[2]);
            tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
            tileReady.get(tileReady.size()-1)[1] = lastOffWidth;
            tileReady.add(new int[2]);
            tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
            tileReady.get(tileReady.size()-1)[1] = 1;
        }
        else if(lastOffWidth==0)
        {
            tileReady.add(new int[2]);
            tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
            tileReady.get(tileReady.size()-1)[1] = lastOffWidth;
            if(pyramid.get(lastOffHeight)[lastOffWidth+1]==-1)
            {
                tileReady.add(new int[2]);
                tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
                tileReady.get(tileReady.size()-1)[1] = 1;
            }
        }
        else if(lastOffWidth==pyramid.get(lastOffHeight).length-1)
        {
            tileReady.add(new int[2]);
            tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
            tileReady.get(tileReady.size()-1)[1] = lastOffWidth+1;
            if(pyramid.get(lastOffHeight)[lastOffWidth-1]==-1)
            {
                tileReady.add(new int[2]);
                tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
                tileReady.get(tileReady.size()-1)[1] = lastOffWidth;
            }
        }
        else
        {
            if(pyramid.get(lastOffHeight)[lastOffWidth+1]==-1)
            {
                tileReady.add(new int[2]);
                tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
                tileReady.get(tileReady.size()-1)[1] = lastOffWidth+1;
            }
            if(pyramid.get(lastOffHeight)[lastOffWidth-1]==-1)
            {
                tileReady.add(new int[2]);
                tileReady.get(tileReady.size()-1)[0] = lastOffHeight-1;
                tileReady.get(tileReady.size()-1)[1] = lastOffWidth;
            }
        }

    }

    @Override
    public void addBaseTile(int color) { baseTile.add(color); }

    @Override
    public void addBonusTileReady(int color) { bonusTileReady.add(color); }

    @Override
    public int getBonusTileReady(int index) { return bonusTileReady.get(index); }

    @Override
    public void removeBonusTileReady(int index) { bonusTileReady.remove(index); }

    @Override
    public int getTileReady(int index) { return pyramid.get(tileReady.get(index)[0])[tileReady.get(index)[0]]; }

    @Override
    public void removeTileReady(int index) { tileReady.remove(index);}

    @Override
    public void printTileReady()
    {
        StringBuilder res = new StringBuilder();

        for (int[] ints : tileReady) res.append(pyramid.get(ints[0])[ints[1]]).append(" ");

        System.out.println(res);
    }

    @Override
    public void printBonusTileReady()
    {
        StringBuilder res = new StringBuilder();

        for (int ints : bonusTileReady) res.append(ints).append(" ");

        System.out.println(res);
    }

    @Override
    public void printBaseTile()
    {
        StringBuilder res = new StringBuilder();

        for (int ints : baseTile) res.append(ints).append(" ");

        System.out.println(res);
    }

    @Override
    public boolean isBaseTileEmpty() { return baseTile.isEmpty(); }

    @Override
    public void createMyPyramid()
    {
        Random r = new Random();
        int index;
        int h=0;
        int w=0;
        while(!baseTile.isEmpty())
        {
            printMyPyramid();
            System.out.println(name + " choisir un index, start a 0, pour le 0, de gauche a droite et haut en bas:");
            printBaseTile();
            index = r.nextInt(baseTile.size());
            pyramid.get(h)[w]= baseTile.get(index);
            baseTile.remove(index);
            w++;
            if(w==pyramid.get(h).length)
            {
                h++;
                w = 0;
            }
        }

        tileReady.add(new int[2]);
        tileReady.get(0)[0]=5;
        tileReady.get(0)[1]=0;
    }

    @Override
    public void printMyPyramid()
    {
        int rows = 6, coef = 1;

        for(int i = 0; i < rows; i++)
        {
            for(int space = 1; space < rows - i; ++space) System.out.print("  ");

            for(int j = 0; j <= i; j++)
            {
                if (pyramid.size()-1<5-i)
                    coef = -1;
                else
                    coef = pyramid.get(5-i)[j];

                System.out.printf("%4d", coef);
            }

            System.out.println();
        }
    }

    @Override
    public boolean canPlay()
    {
        if(tileReady.isEmpty() && bonusTileReady.isEmpty()) return false;

        for (int[] ints : tileReady) {
            for (int j = 1; j < game.pyramid.size(); j++) {
                for (int k = 0; k < game.pyramid.get(j).length; k++) {
                    if (game.pyramid.get(j)[k] == -1 && (game.pyramid.get(j - 1)[k] == pyramid.get(ints[0])[ints[1]]) || game.pyramid.get(j - 1)[k + 1] == pyramid.get(ints[0])[ints[1]] || pyramid.get(ints[0])[ints[1]] == 0 || pyramid.get(ints[0])[ints[1]] == 6) {
                        return true;
                    }
                }
            }
        }

        for (int ints : bonusTileReady) {
            for (int j = 1; j < game.pyramid.size(); j++) {
                for (int k = 0; k < game.pyramid.get(j).length; k++) {
                    if (game.pyramid.get(j)[k] == -1 && (game.pyramid.get(j - 1)[k] == ints) || game.pyramid.get(j - 1)[k + 1] == ints || ints == 0 || ints == 6) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void addWin()
    {
        victory++;
        gamePlayed++;
    }

    @Override
    public void addLose()
    {
        lose++;
        gamePlayed++;
    }

    @Override
    public void newGame(Game game)
    {
        this.game = game;
        baseTile = new ArrayList<Integer>();
        pyramid = new ArrayList<int[]>();
        for(int i=6;i>0;i--) pyramid.add(new int[i]);
        tileReady = new ArrayList<int[]>();
        bonusTileReady = new ArrayList<Integer>();
    }

    @Override
    public void placeTile()
    {
        Random r = new Random();
        int color, height, width, list, index;
        boolean moveOk = false;

        while(!moveOk)
        {
            printMyPyramid();
            printTileReady();
            printBonusTileReady();

            System.out.println(name +" Choisir bonus=0 ou rdy=1");
            list = r.nextInt(2);
            if(bonusTileReady.isEmpty() && !tileReady.isEmpty()) list = 1;
            else if(tileReady.isEmpty() && !bonusTileReady.isEmpty()) list = 0;
            System.out.println(list);
            if(list == 0)
            {
                System.out.println(name+" Choisir la tile avec index");
                index = r.nextInt(bonusTileReady.size());
                System.out.println(index);
                color = bonusTileReady.get(index);
                System.out.println(name+" Choisir la hauteur de l'emplacement");
                height = r.nextInt(pyramid.size());
                if(height == 0) height++;
                System.out.println(height);
                System.out.println(name+" Choisir case");
                width = r.nextInt(pyramid.get(height).length-1)+1;
                System.out.println(width);
                moveOk = game.addPyramid(color, height, width);
                if(moveOk)
                {
                    bonusTileReady.remove(index);
                    game.penalty(height, width);
                }
                else System.out.println("Mauvais placement retry");
            }
            else
            {
                System.out.println(name+" Choisir la tile avec index");
                index = r.nextInt(tileReady.size());
                System.out.println(index);
                color = pyramid.get(tileReady.get(index)[0])[tileReady.get(index)[1]];
                System.out.println(name+" Choisir la hauteur de l'emplacement");
                height = r.nextInt(pyramid.size());
                if(height == 0) height++;
                System.out.println(height);
                System.out.println(name+" Choisir case");
                width = r.nextInt(pyramid.get(height).length);
                System.out.println(width);
                moveOk = game.addPyramid(color, height, width);
                if(moveOk)
                {
                    pyramid.get(tileReady.get(index)[0])[tileReady.get(index)[1]] = -1;
                    lastOffHeight = tileReady.get(index)[0];
                    lastOffWidth = tileReady.get(index)[1];
                    tileReady.remove(index);
                    setTileReady();
                }
                else System.out.println("Mauvais placement retry");
            }
        }
    }

    @Override
    public boolean isIA(){ return true;}

    @Override
    public void placeTileSidePyramid()
    {

    }
}
