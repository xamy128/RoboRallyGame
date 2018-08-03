package apacheclientservice;

import org.json.JSONArray;
import org.json.JSONStringer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import java.util.List;

/**
 * Created by Ammarah on 5/12/2017.
 */

@org.springframework.context.annotation.Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ServiceBusiness extends BaseBusiness {

    public void getGameList(IServiceCallback callback) {
        JSONStringer jsonStringer = new JSONStringer();
        invokeService(false, "/games/list", jsonStringer, callback, "getGame");
    }

    public void createGame(String gameName, int playerCount, IServiceCallback callback) {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("gameName").value(gameName);
        jsonStringer.key("playerCount").value(playerCount);
        jsonStringer.endObject();
        invokeService(true, "/games/create", jsonStringer, callback, "createGame");
    }

    public void joinGame(String id, String playerName, String clientUrl, IServiceCallback callback) {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("playerName").value(playerName);
        jsonStringer.key("clientUrl").value(clientUrl);
        jsonStringer.endObject();
        invokeService(true, "/games/join", jsonStringer, callback, "joinGame");
    }

    public void sendRegister(List<String> registers, IServiceCallback callback) {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("registers").value(new JSONArray(registers));
        jsonStringer.endObject();
        invokeService(true, "/games/round/sendRegisters", jsonStringer, callback, "sendRegister");
    }

    public void leaveGame(IServiceCallback callback) {
        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.endObject();
        invokeService(true, "/games/leave", jsonStringer, callback, "leaveGame");
    }

}