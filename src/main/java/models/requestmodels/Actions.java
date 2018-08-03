package models.requestmodels;

import models.Card;

import java.util.List;

/**
 * @author Ammarah
 * @since 6/4/2017.
 */
public class Actions {
    private String playerId;
    private Card action;
    private List<Card> damageCards;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Card getAction() {
        return action;
    }

    public void setAction(Card action) {
        this.action = action;
    }

    public List<Card> getDamageCards() {
        return damageCards;
    }

    public void setDamageCards(List<Card> damageCards) {
        this.damageCards = damageCards;
    }


}
