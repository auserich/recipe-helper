package recipe_helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import recipe_helper.model.SavedRecipe;

@Repository
public interface SavedRecipeRepository extends JpaRepository<SavedRecipe, Integer> {

}
