package views;

import models.GameModel;
import models.requestmodels.Fields;
import models.requestmodels.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author  Ammarah
 * @since 5/13/2017.
 */

public class GameView {
    private  List<String> gameNames;
     Scanner scanner;
    private  GameCallbacks.IJoinGameCallBack iJoinGameCallBack;
    private  GameCallbacks.ICreateGameCallBack iCreateGameCallBack;


    public GameView(){
        scanner=new Scanner(System.in);
    }

    /**
     * set callbacks to send data to controller whenever there is input from user
     * @param iJoinGameCallBack             callback when player select game to join
     * @param iCreateGameCallBack           callback when player creates game
     */
    public void  setCallBacks(GameCallbacks.IJoinGameCallBack iJoinGameCallBack, GameCallbacks.ICreateGameCallBack iCreateGameCallBack){
        this.iJoinGameCallBack=iJoinGameCallBack;
        this.iCreateGameCallBack=iCreateGameCallBack;
    }

    /**
     * show list of all available games on server
     *
     * @param games        list of games
     */
    public void showGames(List<GameModel> games) {
        gameNames=new ArrayList<>();
        gameNames= games.stream().map(GameModel::getName).collect(Collectors.toList());
        System.out.println("List of games available");
        for(int i=0;i<gameNames.size();i++) {
            System.out.println(i+1 + "." +gameNames.get(i));
        }
        createJoinGame();
    }

    /**
     *
     * show create or join game menu to player
     */
    private void createJoinGame(){
        int input;

        do{
            System.out.println("Type any number corresponding to each game to join or type 0 to create a new game");
            input=scanner.nextInt();

        }
        while(input<0 || input>gameNames.size());

        switch (input){
            case 0:
                createNewGame();
                break;
            case 1:
                joinGame(input);
                break;
                default:
                    break;
        }
    }

    /**
     * call join game service through {@link controllers.ApacheGameController}
     * @param input         selected game
     */
    public void joinGame(int input){
        System.out.println("Enter playerName to join game");
        String playerName=scanner.next();
        String ip = System.getProperty("myapplication.ip");
        iJoinGameCallBack.callJoinGameService(input,playerName,ip+"/rest/"+playerName);
    }

    /**
     * take user input for creating new game on server
     */

    private void createNewGame(){
        int maxPlayer;
        System.out.println("Enter Game Name: ");
        String gameName=scanner.next();
        do{
            System.out.println("Enter maximum number of players (between 1 and 6)in the game.");
            maxPlayer=scanner.nextInt();
        }
        while (maxPlayer<=1 && maxPlayer>6);

        iCreateGameCallBack.createNewGameService(gameName,maxPlayer);
    }

    /**
     * Display board layout to player at the beginning of game
     * @param fields        board fields
     */
    public void showBoardLayout(Fields[][] fields){
        System.out.println("Game Board Layout");

        for (int i=0; i<fields[0].length;i++){
            for (int j=0;j<fields[1].length;j++){
                System.out.print(fields[i][j].getField().toString()+"           ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Available robots on the server from hich player can select
     * @param robotNames            available robots list
     * @return                      player selected robot
     */
    public String getUserRobot(List<String> robotNames){
        int selectedRobo;

        System.out.println("Please choose desired robot.");

        for(int i=0;i<robotNames.size();i++){

            System.out.println(i+". "+robotNames.get(i));
        }
        do {
            System.out.println("Select valid robot.");
            selectedRobo=scanner.nextInt();

        }

        while (selectedRobo>=robotNames.size());

        System.out.println("Your selected robot is "+ robotNames.get(selectedRobo));
        return robotNames.get(selectedRobo);

    }


    /**
     * Avilable starting positions for the player to choose from
     * @param availableStartingPositions        list of positions available
     * @return                                  return layer selected position
     */
    public Position getSelectedUserPosition(List<Position> availableStartingPositions){
        int selectedPos;
        System.out.println("Choose desired starting position.");

        for(int i=0;i<availableStartingPositions.size();i++){
            System.out.println(i+"."+"("+availableStartingPositions.get(i).getX()+","+
                    availableStartingPositions.get(i).getY()+")");
        }

        do{
            System.out.println("Select valid position.");
            selectedPos=scanner.nextInt();
        }

        while(selectedPos<0 || selectedPos>=availableStartingPositions.size());
        return availableStartingPositions.get(selectedPos);

    }

    /**
     * print any message for the player
     */
    public void printMessage(String reason){
        System.out.println(reason);

    }
}
