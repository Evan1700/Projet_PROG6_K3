import java.util.List;

public interface Player
{
    void setTileReady();

    void addBaseTile(int color);

    void addBonusTileReady(int color);

    int getBonusTileReady(int index);

    void removeBonusTileReady(int index);

    int getTileReady(int index);

    void removeTileReady(int index);

    void printTileReady();

    void printBonusTileReady();

    void printBaseTile();

    boolean isBaseTileEmpty();

    void createMyPyramid();

    void placeTile();

    void printMyPyramid();

    boolean canPlay();

    String getName();

    void addWin();

    void addLose();
}
