import ReqResStrings.RequestStrings;
import ReqResStrings.ResponseStrings;
import utils.Utils;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Ammarah on 5/10/2017.
 */
class StartServer {
    private static final RequestStrings requestStrings=new RequestStrings();
    private static final ResponseStrings responseStrings=new ResponseStrings();

    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private static final int STATUS_OK = 200;
   private static final int STATUS_FAILED=403;
   private static int STATUS_NOT_FOUND=404;
   private static final int INVALID=422;
   private static final int VALID=204;

    public static void main(String[] args){
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/games/list", new GetGamesHandler());
        server.createContext("/games/create",new CreateGameHandler());
        server.createContext("/games/join",new JoinGameHandler());
server.createContext("/games/round/sendRegisters",new UserRegisterHandler());
        server.start();
    }


    static class UserRegisterHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(),"utf-8");

            BufferedReader br = new BufferedReader(isr);
            String kk=   httpExchange.getRequestURI().getQuery();
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }

            br.close();
            isr.close();
            JSONObject jsonObject=new JSONObject(buf.toString());
            JSONArray jsonArray=jsonObject.getJSONArray("registers");



            final Headers headers =httpExchange.getResponseHeaders();
            final String responseBody =new Utils().getSecretKey();
            headers.set(HEADER_CONTENT_TYPE, String.format("text/plain; charset=%s", CHARSET));
            final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
            httpExchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
            httpExchange.getResponseBody().write(rawResponseBody);
            httpExchange.close();


        }
    }


    static class JoinGameHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
        // String ff=   httpExchange.getRequestURI().getQuery();
            Utils utils=new Utils();
utils.setSecretKey(12,32);
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }

            br.close();
            isr.close();

            final Headers headers =httpExchange.getResponseHeaders();
            final String responseBody =utils.getSecretKey();
            headers.set(HEADER_CONTENT_TYPE, String.format("text/plain; charset=%s", CHARSET));
            final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
            httpExchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
            httpExchange.getResponseBody().write(rawResponseBody);
            httpExchange.close();

        }

    }

    static class CreateGameHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String kk=   httpExchange.getRequestURI().getQuery();
            int b;
            StringBuilder buf = new StringBuilder(512);
            while ((b = br.read()) != -1) {
                buf.append((char) b);
            }

            br.close();
            isr.close();
            JSONObject jsonObject=new JSONObject(buf.toString());
     responseStrings.setCreateGameString(jsonObject.getString("gameName"),jsonObject.getInt("playerCount"));


            final Headers headers =httpExchange.getResponseHeaders();
            final String responseBody =responseStrings.getCreateGame();
            headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
            final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
            httpExchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
            httpExchange.getResponseBody().write(rawResponseBody);
            httpExchange.close();

        }

    }


    static class GetGamesHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
           /* String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();*/

            final Headers headers =t.getResponseHeaders();
            final String requestMethod = t.getRequestMethod().toUpperCase();


            //  final Map<String, List<String>> requestParameters = getRequestParameters(t.getRequestURI());
            // do something with the request parameters
            final String responseBody =responseStrings.getGames();
            headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", CHARSET));
            final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
            t.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
            t.getResponseBody().write(rawResponseBody);
            t.close();

        }
    }
}

