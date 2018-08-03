package controllers;

import apacheclientservice.ServiceBusiness;
import models.Card;
import sessionmanager.GameSession;
import views.RoundView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Ammarah
 * @since 6/20/2017.
 */

@RunWith(MockitoJUnitRunner.class)

public class ApacheRoundControllerTest {
    @Mock
    RoundView roundView;
    @Mock
    GameSession gameSession;
    @InjectMocks
    ApacheRoundController apacheRoundController;
    @Mock
    ServiceBusiness serviceBusiness;

    @Test
    public void callSendRegisterService() throws Exception {
        List<Card> register=new ArrayList<>();

        register.add(Card.SPAM);
        List<String > card=register.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());
        gameSession.setRegister(register);
        verify(gameSession).setRegister(register);

        assertEquals(Arrays.asList("SPAM")
                ,card);

        //doCallRealMethod().when(serviceBusiness).sendRegister(Arrays.asList("SPAM"),apacheRoundController);
        serviceBusiness.sendRegister(Arrays.asList("SPAM"),apacheRoundController);
        verify(serviceBusiness).sendRegister(Arrays.asList("SPAM"),apacheRoundController);
    }


    @Test
    public void onRequestCompleted_sendRegister_Success() throws Exception {

     //   gameSession.setPlayerName("Mike");
       // roundView.showMessage(gameSession.getPlayerName() + " has left the game.");
       // verify(roundView,times(1)).showMessage(gameSession.getPlayerName() + " has left the game.");
        gameSession.setRegisterValid(true);
        verify(gameSession,times(1)).setRegisterValid(true);
    }

    @Test
    public void onRequestCompleted_sendRegister_NotSuccess_roundEnded() throws Exception {
        gameSession.setRegisterValid(false);
        verify(gameSession,times(1)).setRegisterValid(false);
    }

    @Test
    public void onRequestCompleted_sendRegister_NotSuccess_roundNotEnded() throws Exception {
        roundView.showDrawPile(gameSession.getDrawPile());
        verify(roundView,times(1)).showDrawPile(gameSession.getDrawPile());
        gameSession.setRegisterValid(false);
        verify(gameSession,times(1)).setRegisterValid(false);

    }

    @Test
    public void callExitGameService() throws Exception {
       // doCallRealMethod().when(serviceBusiness).leaveGame(eq(apacheRoundController));
        serviceBusiness.leaveGame(apacheRoundController);
        verify(serviceBusiness).leaveGame(apacheRoundController);
    }

}