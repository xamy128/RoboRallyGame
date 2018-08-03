package models;


import utils.CardDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Representation of Movement Cards.
 *
 * @author jpr
 */

@JsonDeserialize(using = CardDeserialize.class)
public enum Card {
    /**
     * Moving Forward 1 Fields
     */
    MOVE1,
    /**
     * Moving Forward 2 Fields
     */
    MOVE2,
    /**
     * Moving Forward 3 Fields
     */
    MOVE3,
    /**
     * Turning to the Right 90°
     */
    RIGHT_TURN,
    /**
     * Turning to the Left 90°
     */
    LEFT_TURN,
    /**
     * Moving Backward 1 Fields
     */
    MOVE_BACK,
    /**
     * Turning around (180°)
     */
    UTURN,
    /**
     * Take the same Move again as in the last register; not to be played as first register!
     */
    AGAIN,
    /**
     * Take one energy cube and do not move at all
     */
    POWER_UP,
    /**
     * The simplest kind of damage. SPAM cards are dealt, when robots take damage (get shot by a robot, a laser or fall
     * off the board)
     */
    SPAM(true),
    /**
     * When a VIRUS is played in a register, it spreads to all surrounding robots, that are not more than 6 fields away
     * from the source of the disease.
     */
    VIRUS(true),
    /**
     * When a WORM is programmed, the bot simply reboots.
     */WORM(true),
    /**
     * When a Trojan Horse is programmed, it gets replaced by 2 new SPAM cards :#
     */TROJAN_HORSE(true);

    private final boolean damage;

    Card() {
        this.damage = false;
    }

    Card(boolean damage) {
        this.damage = damage;
    }
}
