package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the starting point in the application.
 * It will initialize all the boot controllers.
 * All the beans defined in {@link ApplicationConfiguration} will be injected.
 *
 * @author Ammarah
 * @since 5/12/2017.
 */
@SuppressWarnings({"squid:S2078", "squid:S2076"})
@SpringBootApplication
 class StartGame {
    public static void main(String[] args) {
        SpringApplication.run(StartGame.class, args);
    }
}
