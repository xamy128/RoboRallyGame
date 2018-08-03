package controllers;

import apacheclientservice.ServiceBusiness;
import sessionmanager.GameSession;
import views.GameView;
import views.RoundView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean Factory
 *
 *
 * @author Ammarah
 * @since 5/11/2017.
 *
 */

@Configuration
@ComponentScan
class ApplicationConfiguration {

    @Bean
     ServiceBusiness serviceBusiness() {
        return new ServiceBusiness();
    }

    @Bean
    public ApacheGameController apacheGameController() {
        return new ApacheGameController(serviceBusiness(), gameSession(),gameView());
    }

    @Bean
    public GameSession gameSession() {
        return new GameSession();
    }


    @Bean
    public RoundView roundView(){
        return new RoundView();
    }

    @Bean
    public GameView gameView(){
        return new GameView();
    }
    @Bean
    public ApacheRoundController apacheRoundController(){
        return new ApacheRoundController(serviceBusiness(),roundView(),gameSession());
    }

}