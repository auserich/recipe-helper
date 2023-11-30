package recipe_helper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipe_helper.exception.ResourceAlreadyExistsException;
import recipe_helper.exception.ResourceNotFoundException;
import recipe_helper.model.Recipe;
import recipe_helper.model.SavedRecipe;
import recipe_helper.model.User;
import recipe_helper.repository.SavedRecipeRepository;

@Service
public class SavedRecipeService {
	
	@Autowired
	SavedRecipeRepository repo;
	
	public List<SavedRecipe> getAllSavedRecipes() {
		return repo.findAll();
	}
	
	public SavedRecipe getSavedRecipeById(int id) throws ResourceNotFoundException {
		Optional<SavedRecipe> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Found", id);
		}
		return found.get();
	}
	
	public List<SavedRecipe> getAllSavedRecipesByUser(int userId) {
		return repo.getAllSavedRecipesByUser(userId);
	}
	
	public SavedRecipe getSavedRecipeByUserAndRecipe(int userId, int recipeId) {
		return repo.getSavedRecipeByUserAndRecipe(userId, recipeId);
	}
	
	public SavedRecipe createSavedRecipe(User user, Recipe recipe) throws ResourceAlreadyExistsException {
		SavedRecipe found = repo.getSavedRecipeByUserAndRecipe(user.getId(), recipe.getId());
		if (found != null) {
			throw new ResourceAlreadyExistsException("SavedRecipe", found.getId());
		}
		SavedRecipe created = new SavedRecipe(null, user, recipe);
		return repo.save(created);
	}
	
	public SavedRecipe deleteSavedRecipeById(int id) throws ResourceNotFoundException {
		Optional<SavedRecipe> found = repo.findById(id);
		if (found.isEmpty()) {
			throw new ResourceNotFoundException("SavedRecipe", id);
		}
		repo.deleteById(id);
		return found.get();
	}
}
