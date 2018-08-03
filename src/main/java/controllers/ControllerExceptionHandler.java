package controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author  Ammarah
 * @since  6/22/2017.
 */


/**
 * TO handle all the runtime exceptions thrown by all the controller.
 * Status 500 and a message will be sent.
 */

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleGenericException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String json = "{\"error\":\"There is some problem. Most probably null pointer exception somewhere.\"}";

        return new ResponseEntity<>(json, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
