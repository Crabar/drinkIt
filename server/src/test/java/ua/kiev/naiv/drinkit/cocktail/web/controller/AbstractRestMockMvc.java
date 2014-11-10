package ua.kiev.naiv.drinkit.cocktail.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.kiev.naiv.drinkit.cocktail.AbstractBaseTest;
import ua.kiev.naiv.drinkit.cocktail.common.WebContextFilter;
import ua.kiev.naiv.drinkit.springconfig.WebConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 19.10.2014
 * Time: 21:53
 */
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class AbstractRestMockMvc extends AbstractBaseTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new WebContextFilter())
                .build();
    }

}
