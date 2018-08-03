package apacheclientservice;

import org.json.JSONException;
/**
 * @author Ammarah
 * @since 6/4/2017.
 */

@FunctionalInterface
public interface IServiceCallback {
    void onRequestCompleted(String response, String mode,String statusCode) throws JSONException;
}
