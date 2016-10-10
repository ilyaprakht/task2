package model;


public class ClientGamePlay {

    private Player player;
    private Player competitorsPlayer;
    private boolean isFirstStepper;

    public ClientGamePlay(String userName) {
        this.player = new Player(userName);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getCompetitorsPlayer() {
        return competitorsPlayer;
    }

    public void setCompetitorsPlayer(Player competitorsPlayer) {
        this.competitorsPlayer = competitorsPlayer;
    }

    public boolean isFirstStepper() {
        return isFirstStepper;
    }

    public void setFirstStepper(boolean firstStepper) {
        isFirstStepper = firstStepper;
    }
}
