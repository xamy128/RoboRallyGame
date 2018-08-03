package models.requestmodels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ammarah
 * @since 6/4/2017.
 */
public class ActionModel {

private List<PlayerPositions> positions0;
    private List<PlayerPositions> positions1;
    private List<PlayerPositions> positions2;
    private List<PlayerPositions> positions3;
    private List<PlayerPositions> positions4;
    private List<PlayerPositions> positions5;
    private List<Actions> actions1;
    private List<Actions> actions2;
    private List<Actions> actions3;
    private List<Actions> actions4;
    private List<Actions> actions5;


    public List<PlayerPositions> getPositions0() {
        return positions0;
    }

    public void setPositions0(List<PlayerPositions> positions0) {
        this.positions0 = positions0;
    }

    public List<PlayerPositions> getPositions1() {
        return positions1;
    }

    public void setPositions1(List<PlayerPositions> positions1) {
        this.positions1 = positions1;
    }

    public List<PlayerPositions> getPositions2() {
        return positions2;
    }

    public void setPositions2(List<PlayerPositions> positions2) {
        this.positions2 = positions2;
    }

    public List<PlayerPositions> getPositions3() {
        return positions3;
    }

    public void setPositions3(List<PlayerPositions> positions3) {
        this.positions3 = positions3;
    }

    public List<PlayerPositions> getPositions4() {
        return positions4;
    }

    public void setPositions4(List<PlayerPositions> positions4) {
        this.positions4 = positions4;
    }

    public List<PlayerPositions> getPositions5() {
        return positions5;
    }

    public void setPositions5(List<PlayerPositions> positions5) {
        this.positions5 = positions5;
    }

    public List<Actions> getActions1() {
        return actions1;
    }

    public void setActions1(List<Actions> actions1) {
        this.actions1 = actions1;
    }

    public List<Actions> getActions2() {
        return actions2;
    }

    public void setActions2(List<Actions> actions2) {
        this.actions2 = actions2;
    }

    public List<Actions> getActions3() {
        return actions3;
    }

    public void setActions3(List<Actions> actions3) {
        this.actions3 = actions3;
    }

    public List<Actions> getActions4() {
        return actions4;
    }

    public void setActions4(List<Actions> actions4) {
        this.actions4 = actions4;
    }

    public List<Actions> getActions5() {
        return actions5;
    }

    public void setActions5(List<Actions> actions5) {
        this.actions5 = actions5;
    }

    public List<List<PlayerPositions>> getAllPositions(){
        return new ArrayList<>(Arrays.asList(positions0,positions1,positions2,positions3,positions4,positions5));
    }
    public List<List<Actions>> getAllActions(){
        return new ArrayList<>(Arrays.asList(actions1,actions2,actions3,actions4,actions5));
    }
}
