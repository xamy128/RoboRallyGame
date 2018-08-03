package models.requestmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by Ammarah on 5/23/2017.
 */
public class StartGameModel {

    private List<String> availableRobots;
    private List<Position> position;
private Board board;


public StartGameModel(){
// empty constructor is needed for json to object mapping in jackson spring

}

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @JsonProperty("availableStartingPositions")
    public void setPosition(List<Position> position) {
        this.position = position;
    }

    @JsonProperty("availableStartingPositions")
    public List<Position> getPosition() {
        return position;
    }

    public void setAvailableRobots(List<String> availableRobots) {
        this.availableRobots = availableRobots;
    }

    public List<String> getAvailableRobots() {
        return availableRobots;
    }

    public class Board {
        Size size;
        List<Fields> fields;



        public void setFields(List<Fields> fields) {
            this.fields = fields;
        }

        public List<Fields> getFields() {
            return fields;
        }

        public Size getSize() {
            return size;
        }

        public void setSize(Size size) {
            this.size = size;
        }

        public Fields[][] getBoardMap(){
            Fields [][] boardMap=new Fields[size.getWidth()][size.getHeight()];

            for (Fields field : fields) {
                boardMap[field.getX()][field.getY()] = field;

            }
return boardMap;
    }

}

}
