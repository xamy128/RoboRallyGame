package controllers;

import apacheclientservice.ServiceBusiness;
import models.GameModel;
import sessionmanager.GameSession;
import views.GameView;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.BDDMockito.verify;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.Mockito.times;

/**
 *  @author Ammarah
 *  @since 6/15/2017.
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)

public class ApacheGameControllerTest {
    @Mock
    GameView gameView;
    @Mock
    ServiceBusiness serviceBusiness;
    @Mock
    GameSession gameSession;
    @Mock
    ApacheGameController apacheGameController;
    //@Mock
    ArrayList<GameModel> games;

    @Before
    public void setup(){
        games=new ArrayList<>();
    games.add(new GameModel("AA","AmmarahGame",1,6));
    }

    @Test
    public void callGetGameService(){
        serviceBusiness.getGameList(apacheGameController);
        verify(serviceBusiness,times(1)).getGameList(apacheGameController);
    }

    @Test
    public void callJoinGameService() throws Exception {
        int input=1;
        String gameId="ABC";
        String playerName="Ammarah";
        String clientUrl="http://127.0.0.1:8000/rest/"+playerName;
        gameSession.setGameModel(games.get(input-1));
        verify(gameSession,times(1)).setGameModel(games.get(input-1));
        serviceBusiness.joinGame(gameId,playerName,clientUrl,apacheGameController);
        verify(serviceBusiness,times(1)).joinGame(gameId,playerName,clientUrl,apacheGameController);

    }

    @Test
    public void createNewGameService() throws Exception {
        serviceBusiness.createGame("myGame",4,apacheGameController);
        verify(serviceBusiness,times(1)).createGame("myGame",4,apacheGameController);
    }

    @Test
    public void parseResponse(){
        String response=JSONContent.listOfGames;
        List<GameModel> game=new ArrayList<>();

        JSONArray jsonArray=new JSONArray(response);
        for (int i=0;i<jsonArray.length();i++){
            game.add(new GameModel(jsonArray.getJSONObject(i)));
        }

        gameView.showGames(game);
        verify(gameView,times(1)).showGames(game);

    }


    @Test
    public void parseResponse_joinGame() {
        gameView.joinGame(1);
        verify(gameView,times(1)).joinGame(1);
    }

    @Test
    public void parseJoinGame() throws Exception {
        gameSession.setGameSecret(JSONContent.gameSecret);
        verify(gameSession,times(1)).setGameSecret(JSONContent.gameSecret);
        gameView.printMessage("Congratulations!! Welcome to the game.");
        verify(gameView,times(1)).printMessage("Congratulations!! Welcome to the game.");
    }

    @Test
    public void onRequestCompleted_getGame() throws Exception {
        apacheGameController.parseResponse(JSONContent.listOfGames,"getGame");
        verify(apacheGameController,times(1)).parseResponse(JSONContent.listOfGames,"getGame");
    }

    @Test
    public void onRequestCompleted_joinGame_200() throws Exception {
        apacheGameController.parseJoinGame(JSONContent.gameSecret);
        verify(apacheGameController,times(1)).parseJoinGame(JSONContent.gameSecret);

    }

    @Test
    public void onRequestCompleted_joinGame_404() throws Exception {
        gameView.printMessage("Game is not found. Joining game was unsuccessful.");;
        verify(gameView,times(1)).printMessage("Game is not found. Joining game was unsuccessful.");
        gameSession.setGameId("");
        verify(gameSession,times(1)).setGameId("");
    }

    @Test
    public void onRequestCompleted_joinGame_403() throws Exception {
        gameView.printMessage("Joining game was unsuccessful. Some error has occured.");
        verify(gameView,times(1)).printMessage("Joining game was unsuccessful. Some error has occured.");
        gameSession.setGameId("");
        verify(gameSession,times(1)).setGameId("");
    }
}