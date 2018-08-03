package sessionmanager;

import models.Card;
import models.GameModel;
import models.requestmodels.Fields;
import models.requestmodels.StartRound;
import models.responsemodels.SelectedRoboStartPos;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import java.util.List;

/**
 * @Author Ammarah
 * @since 5/27/2017.
 */

/**
 * To save and get the different variables used in the entire game
 */

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameSession {
    private String gameSecret;
    private Fields[][] boardMap;
    private StartRound startRoundData;
    private List<Card> register;
    private List<Card> drawPile;
    private Boolean isRoundEnded=false;
    private Boolean isRegisterValid=false;
    private String playerName;
    private String gameName;
    private String clientUrl;
    private int maxRoboCount;
    private GameModel gameModel;

    private SelectedRoboStartPos selbotStartPos;

    /**
     * player joined game id
     * @return    String game id
     */
    public String getGameId() {
        return gameModel.getId();
    }

    public void setGameId(String gameId) {
        gameModel.setId(gameId);
    }


    public String getGameSecret() {
        return gameSecret;

    }

    public void setGameSecret(String gameSecret) {
        this.gameSecret = gameSecret;
    }

    public Fields[][] getBoardMap() {
        return boardMap;
    }

    public void setBoardMap(Fields[][] boardMap) {
        this.boardMap = boardMap;
    }

    public SelectedRoboStartPos getSelbotStartPos() {
        return selbotStartPos;
    }

    public void setSelbotStartPos(SelectedRoboStartPos selbotStartPos) {
        this.selbotStartPos = selbotStartPos;
    }

    public StartRound getStartRoundData() {
        return startRoundData;
    }

    public void setStartRoundData(StartRound startRoundData) {
        this.startRoundData = startRoundData;
    }

    public List<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(List<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public Boolean getIsRoundEnded() {
        return isRoundEnded;
    }

    public void setIsRoundEnded(Boolean roundEnded) {
        isRoundEnded = roundEnded;
    }

    public List<Card> getRegister() {
        return register;
    }

    public void setRegister(List<Card> register) {
        this.register = register;
    }

    public Boolean getRegisterValid() {
        return isRegisterValid;
    }

    public void setRegisterValid(Boolean registerValid) {
        isRegisterValid = registerValid;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public int getMaxRoboCount() {
        return maxRoboCount;
    }

    public void setMaxRoboCount(int maxRoboCount) {
        this.maxRoboCount = maxRoboCount;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    /**
     * save details of joined game that is id, name, maximum robotcount and current robot count
     * @param gameModel
     */
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
