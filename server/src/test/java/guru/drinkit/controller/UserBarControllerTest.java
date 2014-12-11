package guru.drinkit.controller;

import guru.drinkit.domain.UserBar;
import guru.drinkit.repository.UserBarRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserBarControllerTest extends AbstractRestMockMvc {

    private static final String RESOURCE_ENDPOINT = "/userbar";
    @Autowired
    UserBarRepository userBarRepository;

    @Test
    public void testSaveUserBar() throws Exception {
        UserBar userBar = new UserBar();
        userBar.setUserId(new ObjectId(new Date()).toString());
        userBar.setUserIngredients(new ArrayList<UserBar.UserIngredient>() {
            {
                add(new UserBar.UserIngredient(1, true));
                add(new UserBar.UserIngredient(2, true));
                add(new UserBar.UserIngredient(3, false));
            }
        });
//        userBarRepository.save(userBar);
        mockMvc.perform(put(RESOURCE_ENDPOINT + "/" + userBar.getUserId())
                .content(objectMapper.writeValueAsBytes(userBar))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(RESOURCE_ENDPOINT + "/" + userBar.getUserId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userIngredients", hasSize(3)));
    }

    @Test
    public void testGetUserBar() throws Exception {

    }
}