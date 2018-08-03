package controllers;

import models.requestmodels.Position;
import models.requestmodels.StartGameModel;
import models.responsemodels.SelectedRoboStartPos;
import sessionmanager.GameSession;
import views.GameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoint to receive and respond to all the game related requests from server
 *  @author Ammarah
 *  @since 5/24/2017.
 */

@RestController
@RequestMapping(value = "/games/{id}")
public class SpringGameController {
    private final GameSession gameSession;
    private final GameView gameView;

    /**
     *  Constructor @Autowired
     *
     * @param gameSession   inject {@link GameSession} objects
     * @param gameView      inject {@link GameView} objects
     */
    @Autowired
    public SpringGameController(GameSession gameSession, GameView gameView)
    {
        this.gameSession=gameSession;
        this.gameView=gameView;
    }

    /**
     * receive board layout from server and send player selected start position and robot
     * @param startGameModel             to save board layout
     * @return                      selected starting position and robot
     */
    @RequestMapping(value = "/start",method = RequestMethod.POST)
    public ResponseEntity<SelectedRoboStartPos> startGame
    (@PathVariable String id, @RequestBody StartGameModel startGameModel){
        gameView.showBoardLayout(startGameModel.getBoard().getBoardMap());
        gameSession.setBoardMap(startGameModel.getBoard().getBoardMap());
        List<String> robots=startGameModel.getAvailableRobots();
        String robo=gameView.getUserRobot(robots);
        Position pos=gameView.getSelectedUserPosition(startGameModel.getPosition());
        SelectedRoboStartPos selectedRoboStartPos=new SelectedRoboStartPos(robo,pos);
        gameSession.setSelbotStartPos(selectedRoboStartPos);
        return new ResponseEntity<>(selectedRoboStartPos, HttpStatus.OK);
    }

    /**
     *  Receive game ended message
     * @param response     string message about why game has ended
     */
    @RequestMapping(value = "/end",method = RequestMethod.POST)
    @ResponseStatus (value =HttpStatus.NO_CONTENT)
    public void endGame(@RequestBody String response) {
        gameView.printMessage(response);
    }

}
