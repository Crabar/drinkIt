package guru.drinkit.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by pkolmykov on 12/11/2014.
 */
@SuppressWarnings("UnusedDeclaration")
public class UserBar {

    @Id
    private String userId;
    private List<UserIngredient> userIngredients;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserIngredient> getUserIngredients() {
        return userIngredients;
    }

    public void setUserIngredients(List<UserIngredient> userIngredients) {
        this.userIngredients = userIngredients;
    }


    public static class UserIngredient {

        public UserIngredient(Integer ingredientId, boolean enabled) {
            this.ingredientId = ingredientId;
            this.enabled = enabled;
        }

        public UserIngredient() {
        }

        private Integer ingredientId;
        private boolean enabled;

        public Integer getIngredientId() {
            return ingredientId;
        }

        public void setIngredientId(Integer ingredientId) {
            this.ingredientId = ingredientId;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
