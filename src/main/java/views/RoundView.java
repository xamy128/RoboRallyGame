package views;

import models.Card;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import models.requestmodels.Actions;
import models.requestmodels.Fields;
import models.requestmodels.PlayerPositions;
import models.requestmodels.TimeLimit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ammarah on 5/24/2017.
 */

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)

public class RoundView {
    //  also show interface for board here and save board in session
    private final Scanner scanner;
    private RoundCallbacks.ISendRegisterCallback sendRegisterCallback;
    private RoundCallbacks.IExitGameCallback exitGameCallback;

    public RoundView(){
        scanner=new Scanner(System.in);
    }

    public void setCallBack(RoundCallbacks.ISendRegisterCallback sendRegisterCallback, RoundCallbacks.IExitGameCallback exitGameCallback){
        this.sendRegisterCallback=sendRegisterCallback;
        this.exitGameCallback=exitGameCallback;
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

    public void showAllBotStartPos(List<PlayerPositions> playerPositions){
        System.out.println("At the beginning of the round");

        for (PlayerPositions playerPos : playerPositions) {
            System.out.println("Robot " + playerPos.getRobot() + " is at position ("
                    + playerPos.getPosition().getX() + "," + playerPos.getPosition().getY() + ") in " + playerPos.getDirection() + " direction");
        }
    }

    /**
     * display the drawpile (9 cards) to the player
     * @param drawPile          list of cards0
     *
     */
    public void showDrawPile(List<Card> drawPile){
        System.out.println("Please choose five cards");

        for (int i=0;i<drawPile.size();i++){
            System.out.println((i+1)+". "+drawPile.get(i).toString());
        }

        sendRegisterCallback.callSendRegisterService(getUserRegister(drawPile));
    }

    /**
     * Get user register
     * @param drawPile          pile of 9 cards
     */
    private List<Card> getUserRegister(List<Card> drawPile){
        List<Integer> selectedDrawPile = new ArrayList<>();
        List<Card>register=new ArrayList<>();


        selectedDrawPile.clear();
        for (int i = 0; i < 5; i++) {
            selectedDrawPile.add(scanner.nextInt());
        }

        for (int i=0;i<drawPile.size();i++){
            if(selectedDrawPile.contains(i+1))
                register.add(drawPile.get(i));
        }
        return register;
    }

    /**
     * Display time limit warning to the player during programming phase
     * @param timeLimit        {@link TimeLimit} object to show time and reason
     */
    public void showTimeLimitWarning(TimeLimit timeLimit){
        System.out.println("Attention! time limit warning"+System.lineSeparator()+
                "Time left: "+timeLimit.getSecondsLeft()+System.lineSeparator()+
                timeLimit.getReason());
    }



    public void showMessage(String message){
        System.out.println(message);
    }

    public void showRoundActions(List<List<PlayerPositions>> allPositions, List<List<Actions>> allActions){
        int isExit;
        System.out.println("The board state at the end of the round is:");
        List<PlayerPositions> lastPos=allPositions.get(5);
        for (int i=0;i<lastPos.size();i++){
            System.out.println("Robot:"+lastPos.get(i).getRobot()+"\n"+"Player Name:"+lastPos.get(i).getPlayerName()+"\n"+
                    lastPos.get(i).getRobot()+" current position "+"("+lastPos.get(i).getPosition().getX()+","+
                    lastPos.get(i).getPosition().getY()+")"+"\n"+lastPos.get(i).getRobot()+" direction:"+lastPos.get(i).getDirection());
            System.lineSeparator();
            System.out.println("Damage cards received by "+lastPos.get(i).getRobot()+" after this round:");

            for (List<Actions> allAction : allActions) {
                for (int x = 0; x < allAction.get(i).getDamageCards().size(); x++) {
                    System.out.println(allAction.get(i).getDamageCards().get(x).toString() + "  ");
                }

            }
        }

        do {
            System.out.println("Do you want to exit the game? \n 1. Yes \n 2. No");
            isExit=scanner.nextInt();

        }
        while (isExit!=1 && isExit!=2);

        if(isExit==1)
        exitGameCallback.callExitGameService();



    }
}
