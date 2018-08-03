package controllers;

import apacheclientservice.IServiceCallback;
import apacheclientservice.ServiceBusiness;
import models.Card;
import sessionmanager.GameSession;
import views.RoundCallbacks;
import views.RoundView;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ammarah
 * @since 5/28/2017.
 */

/**
 * Responsible for sending the client requests to the sever, parsing the responses received and
 * interacting with the console view.
 */

public class ApacheRoundController implements IServiceCallback, RoundCallbacks.ISendRegisterCallback,RoundCallbacks.IExitGameCallback {
    private final RoundView roundView;
    private final ServiceBusiness serviceBusiness;
    private final GameSession gameSession;

    /**
     * oonstructor
     *
     * @param serviceBusiness   {@link ServiceBusiness} object gets injected here
     * @param roundView         {@link RoundView} object gets injected here
     * @param gameSession       {@link GameSession} object gets injected here
     */
    @Autowired
    public ApacheRoundController(ServiceBusiness serviceBusiness, RoundView roundView, GameSession gameSession){
        this.serviceBusiness=serviceBusiness;
        this.roundView=roundView;
        this.gameSession=gameSession;
        roundView.setCallBack(this,this);
    }

    /**
     * send user selected register to the server
     * @param register  user selected five card register
     */
    @Override
    public void callSendRegisterService(List<Card> register) {
        gameSession.setRegister(register);
        List<String> cards=register.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());
        serviceBusiness.sendRegister(cards,this);
    }

    /**
     * to tell the server that the client gas left the game.
     */
    @Override
    public void callExitGameService() {
        serviceBusiness.leaveGame(this);
    }

    /**
     * Receive responses from the server
     *
     * @param response      string response from server
     * @param mode          to distinguish which service has sent back the response
     * @param statusCode    to check the status of response
     */

    @Override
    public void onRequestCompleted(String response, String mode, String statusCode) {

        int stCode=Integer.parseInt(statusCode);

if(("sendRegister").equals(mode)){
    if(stCode==200) {
    //    roundView.showMessage(gameSession.getPlayerName() + " has left the game.");
        gameSession.setRegisterValid(true);
    roundView.showMessage("5 card register selected successfuly");
    }
     else if (stCode == 422) {
//if time left send register again
        if (!gameSession.getIsRoundEnded()) {
            roundView.showDrawPile(gameSession.getDrawPile());
        roundView.showMessage("Register was not valid. Another attempt is being made. Please wait");
        }
            gameSession.setRegisterValid(false);
    }

}

else if(("leaveGame").equals(mode)){
roundView.showMessage("Thankyou for joining us.");
}
    }


}
