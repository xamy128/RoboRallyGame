package controllers;

import sessionmanager.GameSession;
import views.RoundView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import models.requestmodels.ActionModel;
import models.requestmodels.StartRound;
import models.requestmodels.TimeLimit;

/**
 * to receive rounds related request from the server and send response
 * @author Ammarah
 * @since 5/17/2017.
 */

@RestController
@RequestMapping(value = "/games/{id}/round")
public class SpringRoundController {
    private final GameSession gameSession;
    private final RoundView roundView;

    /**
     * Constructor
     *
     * @param gameSession           injected {@link GameSession} object
     * @param roundView             injected {@link RoundView} object
     */
    @Autowired
    public SpringRoundController(GameSession gameSession,RoundView roundView)
    {
        this.gameSession=gameSession;
        this.roundView=roundView;
    }


    /**
     * receive board state and drawpile at start of round
     * @param startRound        to save player positions and drawpile
     */
    @RequestMapping(value = "/start",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void startRound(@RequestBody StartRound startRound) {
        gameSession.setStartRoundData(startRound);
        gameSession.setDrawPile(startRound.getDrawPile());
        roundView.showAllBotStartPos(startRound.getPlayerPositions());
        roundView.showDrawPile(startRound.getDrawPile());
        gameSession.setIsRoundEnded(false);
    }

    /**
     * Receives time limit warnings from the server
     * @param timeLimit       map request to {@link TimeLimit] object
     *
     */
    @RequestMapping(value = "/timeLimitWarning",method = RequestMethod.POST)
    @ResponseStatus (value =HttpStatus.NO_CONTENT)
    public void timeLimitWarning(@RequestBody TimeLimit timeLimit) {
        roundView.showTimeLimitWarning(timeLimit);

    }

    /**
     * Receive round end message from server
     */
    @RequestMapping(value = "/ends",method = RequestMethod.POST)
    @ResponseStatus (value =HttpStatus.NO_CONTENT)
    public void roundEnds()  {
        roundView.showMessage("This round has ended.");
        gameSession.setIsRoundEnded(true);
    }



    /**
     * Receive actions of all player during activation phase
     * @param actionModel        to map actions of all players to {@link ActionModel} object
     */

    @RequestMapping(value = "/actions",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void renderActions( @RequestBody ActionModel actionModel)  {
        roundView.showBoardLayout(gameSession.getBoardMap());
        roundView.showRoundActions(actionModel.getAllPositions(),actionModel.getAllActions());
    }

}
