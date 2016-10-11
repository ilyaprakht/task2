package model;


import java.util.ArrayList;
import java.util.List;

public class ServerGamePlayPool {

    private static ServerGamePlayPool pool;
    private List<ServerGamePlay> freeGames;
    private List<ServerGamePlay> activeGames;

    private ServerGamePlayPool() {
        freeGames = new ArrayList<>();
        activeGames = new ArrayList<>();
    }

    public static ServerGamePlayPool getInstance() {
        if (pool == null) {
            pool = new ServerGamePlayPool();
        }
        return pool;
    }

    synchronized public ServerGamePlay findFreeGamePlay(Player player) {
        ServerGamePlay gamePlay = null;

        if (freeGames.size() == 0) {
            gamePlay = new ServerGamePlay(player);
            freeGames.add(gamePlay);
        } else {
            gamePlay = freeGames.remove(0);
            gamePlay.addSecondPlayer(player);
            activeGames.add(gamePlay);
        }

        return gamePlay;
    }

    synchronized public void removeGamePlayFromPool(ServerGamePlay gamePlay) {
        freeGames.remove(gamePlay);
        activeGames.remove(gamePlay);
    }

}
