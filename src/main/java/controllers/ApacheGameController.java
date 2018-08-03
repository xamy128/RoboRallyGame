package controllers;

import apacheclientservice.ServiceBusiness;
import sessionmanager.GameSession;
import views.GameCallbacks;
import models.GameModel;
import apacheclientservice.IServiceCallback;
import views.GameView;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * send and receive message to and from server.
 * send and receive data to and from {@link GameView} to display user input and show server response.
 *
 * @author  Ammarah
 * @since  5/12/2017.
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ApacheGameController implements GameCallbacks.IJoinGameCallBack,IServiceCallback, GameCallbacks.ICreateGameCallBack {
    private  GameView gameView;
    private ServiceBusiness serviceBusiness;
    private ArrayList<GameModel> games;
    private  GameSession gameSession;
    private Logger logger;
    private int selectedGame;
    /**
     * Constructor
     *
     * @param serviceBusiness    {@link ServiceBusiness} class object to call methods
     *
     */
    @Autowired
    public ApacheGameController(ServiceBusiness serviceBusiness,GameSession gameSession,GameView gameView) {
        this.serviceBusiness=serviceBusiness;
        this.gameSession=gameSession;
        this.gameView=gameView;
        logger = Logger.getAnonymousLogger();
        gameView.setCallBacks(this,this);
        callGetGameService();
    }

    /**
     * to get list of all available games on the server
     */
    public void callGetGameService() {

        serviceBusiness.getGameList(this);

    }

    /**
     * ping the server to join game
     *
     * @param input                 selected game
     * @param playerName            entered by player
     * @param clientURL             client url sent to server
     */
    @Override
    public void callJoinGameService(int input, String playerName, String clientURL) {
        selectedGame=input;
        serviceBusiness.joinGame(games.get(input-1).getId(),playerName,clientURL,this);

    }

    /**
     * ping server to create new game
     * @param gameName              player entered game name
     * @param maxPlayer             player entered maximum player received from {@link GameView} {@link views.GameCallbacks.ICreateGameCallBack} callback
     */
    @Override
    public void createNewGameService(String gameName, int maxPlayer) {
        serviceBusiness.createGame(gameName,maxPlayer,this);

    }

    /**
     * parse response from server and display it to player by passing to {@link GameView}
     *
     * @param response         response received from server
     * @param mode             to identify which server received is received from server
     */
    public void parseResponse(String response,String mode){

            games=new ArrayList<>();
            JSONArray jsonArray=new JSONArray(response);
            for (int i=0;i<jsonArray.length();i++){
                games.add(new GameModel(jsonArray.getJSONObject(i)));
            }
            if(("getGame").equals(mode))
                gameView.showGames(games);
            else {
                gameView.joinGame(1);
            }
    }


    /**
     * parse response of join game service from server
     * @param response              response from server
     */
    public void parseJoinGame(String response){
        gameSession.setGameSecret(response);
        logger.log(Level.INFO,"response = ",  response );
        System.out.println("response = [" + response + "]");
        gameView.printMessage("Congratulations!! Welcome to the game.");

    }

    /**
     * Endpoint to receive responses from the server
     * @param response              response string received from server
     * @param mode                  mode to identify which service response was received
     * @param statusCode            show status of request from the server
     * @throws JSONException        handle exceptions
     */
    @Override
    public void onRequestCompleted(String response, String mode,String statusCode) {
        try {
            if(("getGame").equals(mode) || ("createGame").equals(mode)) {
                parseResponse(response,mode);
            }
            else if (("joinGame").equals(mode)){
                int stCode=Integer.parseInt(statusCode);
                if(stCode==200) {
                    gameSession.setGameModel(games.get(selectedGame-1));
                    parseJoinGame(response);

                }
                else if (stCode==404){
                    gameView.printMessage("Game is not found. Joining game was unsuccessful.");
                }
                else if(stCode==403)
                    gameView.printMessage("Joining game was unsuccessful. Some error has occured.");
            }
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Exception occureed in ApacheServiceClientClass", e);
gameView.printMessage("Some problem has occured. Please try again after sometime.");
        }
    }
}
