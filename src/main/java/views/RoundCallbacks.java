package views;

import models.Card;

import java.util.List;

/**
 * Created by Ammarah on 5/28/2017.
 */
public class RoundCallbacks {

@FunctionalInterface
    public interface ISendRegisterCallback{
        void callSendRegisterService(List<Card> register);

    }
@FunctionalInterface
    public interface IExitGameCallback{
        void callExitGameService();
    }
}
