package ReqResStrings;

/**
 * Created by Ammarah on 5/14/2017.
 */
public class ResponseStrings {

    private String createGame="";

    public ResponseStrings() {
    }

public void setCreateGameString(String name,int count){
    createGame="[{\"id\": \"ABS\",\"name\":"+name+",\"maxRobotCount\":"+count+",\"currentRobotCount\": 5}]";

}

public String getCreateGame(){
    return createGame;
}
    public String getGames() {
        return "[{\"id\": \"ABS\",\"name\": \"Jan-Philipps game\",\"maxRobotCount\": 6,\"currentRobotCount\": 5},{\"id\": \"BDC\",\"name\": \"Ammarah game\",\"maxRobotCount\": 6,\"currentRobotCount\": 5}]";
    }
}
