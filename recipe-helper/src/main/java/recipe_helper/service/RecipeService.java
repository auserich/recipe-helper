package recipe_helper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe_helper.exception.ResourceNotFoundException;
import recipe_helper.model.Recipe;
import recipe_helper.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository repo;
	
	public List<Recipe> getAllRecipes() {
		return repo.findAll();
	}
	
//	public List<Recipe> getAllUserRecipes(int userId) {
//		
//	}
	
	public Recipe getRecipeById(int id) throws ResourceNotFoundException {
		Optional<Recipe> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Recipe", id);
		}
		return found.get();
	}
	
	public Recipe createRecipe(Recipe recipe) {
		Optional<Recipe> exists = repo.findByTitle(recipe.getTitle());
		if (!exists.isEmpty()) {
			return exists.get();
		}
		recipe.setId(null);
		Recipe created = repo.save(recipe);
		return created;
	}
	
	public Recipe updateRecipe(Recipe recipe) throws ResourceNotFoundException {
		if (repo.existsById(recipe.getId())) {
			return repo.save(recipe);
		}
		throw new ResourceNotFoundException("Recipe", recipe.getId());
	}
	
	public Recipe deleteRecipeById(int id) throws ResourceNotFoundException {
		Optional<Recipe> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Recipe", id);
		}
		repo.deleteById(id);
		return found.get();
	}
}
