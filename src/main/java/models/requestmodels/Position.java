package models.requestmodels;

/**
 * Created by Ammarah on 5/24/2017.
 */
public class Position {
    private   int x;
    private   int y;


public Position(){
// empty constructor is needed for json to object mapping in jackson spring
}
    public Position(int x,int y){
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}