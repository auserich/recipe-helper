package recipe_helper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import recipe_helper.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
	
	public Optional<Recipe> findByTitle(String title);
}
