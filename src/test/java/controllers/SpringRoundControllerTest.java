package controllers;

import sessionmanager.GameSession;
import views.RoundView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ammarah
 * @since 6/17/2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {SpringRoundController.class})

@ContextConfiguration(classes = ApplicationConfiguration.class)

public class SpringRoundControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RoundView roundView;

    @MockBean
    GameSession gameSession;


    @Test
    public void startRound() throws Exception {
        given(gameSession.getGameSecret()).willReturn("abc");

        mockMvc.perform(post("/games/1/round/start")
                .content(JSONContent.startRound)
.header("secret","abc")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void timeLimitWarning() throws Exception {
        given(gameSession.getGameSecret()).willReturn("abc");

        String requestContent="{\"secondsLeft\": 30,\"reason\": \"Mike has finished programming his registers\"}";


        mockMvc.perform(post("/games/1/end").accept(MediaType.APPLICATION_JSON_UTF8)
 .header("secret","abc")
                .content(requestContent))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void roundEnds() throws Exception {
        given(gameSession.getGameSecret()).willReturn("abc");

        mockMvc.perform(post("/games/1/round/ends")
        .header("secret","abc")
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void renderActions() throws Exception {
        given(gameSession.getGameSecret()).willReturn("abc");

        mockMvc.perform(post("/games/1/round/actions")
                .content(JSONContent.roundRenderActions)
                .header("secret","abc")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}