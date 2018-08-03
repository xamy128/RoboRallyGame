package models;

import org.json.JSONObject;

/**
 * @author  Ammarah
 * @since 5/12/2017.
 */
public class GameModel {
    private String id;
    private final String name;
    private int maxRobotCount;
    private int currentRobotCount;

    public GameModel(String id,String name,int currentRobotCount,int maxRobotCount){
        this.id=id;
        this.name=name;
        this.currentRobotCount=currentRobotCount;
        this.maxRobotCount=maxRobotCount;
    }

    public GameModel(JSONObject jsonObject){
        id=jsonObject.getString("id");
        name=jsonObject.getString("name");
        maxRobotCount=jsonObject.getInt("maxRobotCount");
        currentRobotCount=jsonObject.getInt("currentRobotCount");
    }

    public String getName() {
        return name;
    }

    public int getMaxRobotCount() {
        return maxRobotCount;
    }

    public int getCurrentRobotCount() {
        return currentRobotCount;
    }


public void setId(String id){
this.id=id;
}
    public String getId(){
        return id;
    }
}
