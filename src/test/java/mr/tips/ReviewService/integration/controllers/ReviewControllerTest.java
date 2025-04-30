package mr.tips.ReviewService.integration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import mr.tips.ReviewService.config.SecurityConfig;
import mr.tips.ReviewService.controllers.ReviewController;
import mr.tips.ReviewService.dto.CreateReviewRequest;
import mr.tips.ReviewService.dto.UserImage;
import mr.tips.ReviewService.filters.JwtAuthenticationFilter;
import mr.tips.ReviewService.integration.utils.TestJwtUtils;
import mr.tips.ReviewService.services.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
@Import({SecurityConfig.class, TestJwtUtils.class})
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestJwtUtils testJwtUtils;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private ReviewService reviewService;

    @Test
    void createReviewTest() throws Exception {
        CreateReviewRequest request = new CreateReviewRequest(1, "some comment");

        mockMvc.perform(post("/create_review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


    @Test
    void createReviewTest_authenticatedUser() throws Exception {
        CreateReviewRequest request = new CreateReviewRequest(1, "some comment");
        String jwtToken = testJwtUtils.generateToken(
                new UserImage(1, "some@em.ail", "+78983213821")
        );

        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");

        mockMvc.perform(post("/create_review")
                        .cookie(jwtCookie)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void getReviewHistory() throws Exception {
        String jwtToken = testJwtUtils.generateToken(
                new UserImage(1, "some@em.ail", "+78983213821")
        );

        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");

        mockMvc.perform(get("/get_review_history")
                        .cookie(jwtCookie))
                .andExpect(status().isOk());
    }
}
