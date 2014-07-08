package ua.kiev.naiv.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
import ua.kiev.naiv.drinkit.cocktail.web.controller.RecipeController;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author pkolmykov
 */
@RunWith(MockitoJUnitRunner.class)
public class CocktailServiceTests {

    @InjectMocks
    RecipeController recipeController = new RecipeController();

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setHandlerExceptionResolvers(new DefaultHandlerExceptionResolver())
                .build();
    }

    @Test
    public void getRecipeByIdShouldReturnValidRecipe() throws Exception {
        when(recipeService.getRecipeById(1)).thenReturn(creteMockRecipe());
        ResultActions resultActions = mockMvc.perform(get("/cocktails/1"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(objectMapper.writeValueAsString(creteMockRecipe())));
    }

//    @Test   //todo move + move test to controller package
//    public void getIngredientsShouldReturnValidResult() throws Exception {
//        when(recipeService.getIngredients()).thenReturn(Arrays.asList(new Ingredient()));
//        ResultActions resultActions = mockMvc.perform(get("/ingredients"));
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().string(objectMapper.writeValueAsString(new Ingredient())));
//    }

    @Test
    public void searchRecipeShouldReturnValidRecipes() throws Exception {
        Criteria criteria = createMockCriteria();
        when(recipeService.findByCriteria(any()))//todo fix any to criteria
                .thenReturn(Arrays.asList(creteMockRecipe()));
        ResultActions resultActions = mockMvc.perform(get("/cocktails/search")
        .param("criteria", objectMapper.writeValueAsString(criteria)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(objectMapper.writeValueAsString(Arrays.asList(creteMockRecipe()))));
    }

    @Test
    @Ignore
    public void deleteRecipeShouldReturnException() throws Exception {
        doThrow(new RuntimeException("RecordNotFound")).when(recipeService).delete(0);
        mockMvc.perform(delete("/cocktails/0"));
    }

    @Test
    public void deleteRecipeShouldReturn200() throws Exception {
        mockMvc.perform(delete("/cocktails/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateRecipeShouldReturn200() throws Exception {
        mockMvc.perform(put("/cocktails/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(creteMockRecipe())))
                .andExpect(status().isOk());
    }

    @Test
    public void createRecipeShouldReturnId() throws Exception {
        when(recipeService.create(creteMockRecipe())).thenReturn(1);
        String json = objectMapper.writeValueAsString(creteMockRecipe());
        mockMvc.perform(post("/cocktails").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }


    private Recipe creteMockRecipe() {
        Recipe recipe = new Recipe();
        recipe.setCocktailTypeId(1);
        recipe.setDescription("desc");
        recipe.setName("Test2");
        recipe.setOptions(new int[]{1, 2});
        recipe.setCocktailIngredients(new int[][]{{1, 50}, {2, 60}});
        return recipe;
    }

    private Criteria createMockCriteria() {
        return new Criteria(new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)),
                new HashSet<>(Arrays.asList(1)));
    }

}
