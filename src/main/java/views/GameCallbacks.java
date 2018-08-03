package views;

import controllers.ApacheGameController;

/**
 * This class contains the callbacks that will be used to get the user input from {@link GameView}
 to {@link ApacheGameController}
 *
 * @author  Ammarah
 * @since  5/12/2017.
 */


public class GameCallbacks {

   /*
   * JoinGame callback
   *
   * @param input             selected Game
   * @param playerName        player name input by player
   * @param clientURL         client URL entered by player
   *
   */
@FunctionalInterface
   public interface IJoinGameCallBack{
       void callJoinGameService(int input,String playerName,String clientURL);
   }


   /*
   * CreateGame callback
   *
   * @param gameName         game name entered by user
   * @param maxPlayer        maximum number of players entered by player must be less than 7 and greater than 0
   *
   */
@FunctionalInterface
   public interface ICreateGameCallBack{
      void createNewGameService(String gameName,int maxPlayer);
   }
}
