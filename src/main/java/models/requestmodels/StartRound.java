package models.requestmodels;
import models.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Ammarah on 5/24/2017.
 */
public class StartRound {
    private List<PlayerPositions> playerPositions;
    private List<Card> drawPile;

    public void setPlayerPositions(List<PlayerPositions> playerPositions) {
        this.playerPositions = playerPositions;
    }

    public List<PlayerPositions> getPlayerPositions() {
        return playerPositions;
    }

    @JsonProperty("drawPile")
    public void setDrawPile(List<Card> drawPile) {
        this.drawPile = drawPile;
    }

    @JsonProperty("registers")
    public List<Card> getDrawPile() {
        return drawPile;
    }
}
