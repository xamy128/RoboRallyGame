package apacheclientservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;
import sessionmanager.GameSession;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  Ammarah
 * @since 5/12/2017.
 */

class ApacheServiceClient  {
    Logger logger;

GameSession gameSession;

private static final String SERVICE_URL   ="http://localhost:8000/";
    public ApacheServiceClient(final boolean isPost, final String method,
         final JSONStringer requestParameters, final IServiceCallback callback, final String mode) {
        super();
        gameSession=new GameSession();
        logger = Logger.getAnonymousLogger();
        try {
            HttpUriRequest request;

            if (isPost) {
                // POST request to <service>/<method>
                request = new HttpPost(SERVICE_URL + method);
                StringEntity entity;

                entity = new StringEntity(requestParameters.toString(),
                        HTTP.UTF_8);
                ((HttpPost) request).setEntity(entity);
            } else {
                // GET request to <service>/<method>
                request = new HttpGet(SERVICE_URL + method);
            }
if(gameSession.getGameSecret()!=null && !gameSession.getGameSecret().equals(""))
            request.setHeader("secret", gameSession.getGameSecret());

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json;charset=UTF-8");
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpResponse response;
            response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            String jsonResponse = EntityUtils.toString(responseEntity, HTTP.UTF_8);
            String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
            callback.onRequestCompleted(jsonResponse, mode, statusCode);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occureed in ApacheServiceClientClass", e);

        }
    }


}
