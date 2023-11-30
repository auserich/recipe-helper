package recipe_helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import recipe_helper.model.SavedRecipe;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, Integer> {
	
	@Query(value = "SELECT * FROM saved_recipe WHERE user_id = ?1", nativeQuery = true)
	public List<SavedRecipe> getAllSavedRecipesByUser(int id);
	
	@Query(value = "SELECT * FROM saved_recipe WHERE user_id = ?1 AND recipe_id = ?2", nativeQuery = true)
	public SavedRecipe getSavedRecipeByUserAndRecipe(int userId, int recipeId);
}
