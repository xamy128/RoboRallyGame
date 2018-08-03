package apacheclientservice;

import org.json.JSONStringer;

/**
 * Created by Ammarah on 5/12/2017.
 */
class BaseBusiness {

void invokeService(final boolean isPost, final String method,
                   final JSONStringer requestParameters, final IServiceCallback callback, final String mode){


 new ApacheServiceClient(isPost,method,requestParameters,callback,mode);



}
}
