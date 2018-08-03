package models.requestmodels;

/**
 * @Author Ammarah
 * @since 6/4/2017.
 */
public class TimeLimit {

    private String secondsLeft;
    private String reason;


    public String getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(String secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
