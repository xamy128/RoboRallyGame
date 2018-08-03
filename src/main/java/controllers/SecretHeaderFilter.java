package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sessionmanager.GameSession;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Intercept the request from the server and check header parameter secret for security. If it does not match then
 * sends an erro message to the server.
 *
 * @author  Ammarah
 * @since 6/11/2017.
 */

@Component
class SecretHeaderFilter extends OncePerRequestFilter{
   @Autowired
   private
   GameSession gameSession;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("secret");

        if(header !=null && header.equals(gameSession.getGameSecret())){
            filterChain.doFilter(request, response);

    } else {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Secret is not correct");

  }

      }
}
