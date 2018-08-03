package models.requestmodels;

import models.Field;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ammarah on 5/24/2017.
 */
public class Fields {

    private int x;
    private int y;
    private Field field;

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @JsonProperty("field")
    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }
}
