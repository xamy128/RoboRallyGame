package models.responsemodels;

import models.requestmodels.Position;

/**
 * Created by Ammarah on 5/24/2017.
 */
public class SelectedRoboStartPos {
    private String robot;
    private Position position;

    public SelectedRoboStartPos(String robot,Position position){
        this.robot=robot;
        this.position=position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;
    }

    public String getRobot() {
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }
}
