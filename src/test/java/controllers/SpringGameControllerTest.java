package controllers;

import apacheclientservice.ServiceBusiness;
import sessionmanager.GameSession;
import views.GameView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author  Ammarah
 * @since 6/15/2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {SpringGameController.class})
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class SpringGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameSession gameSession;
    @MockBean
    ServiceBusiness serviceBusiness;

    @Mock
    GameView gameView;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void endGame() throws Exception {
given(gameSession.getGameSecret()).willReturn("abc");
        mockMvc.perform(post("/games/1/end").accept(MediaType.TEXT_PLAIN)
                .header("secret","abc")
                .content("Game has ended because player ABC has won"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}