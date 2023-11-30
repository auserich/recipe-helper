package recipe_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import recipe_helper.exception.ResourceAlreadyExistsException;
import recipe_helper.exception.ResourceNotFoundException;
import recipe_helper.model.Recipe;
import recipe_helper.model.SavedRecipe;
import recipe_helper.model.User;
import recipe_helper.service.RecipeService;
import recipe_helper.service.SavedRecipeService;
import recipe_helper.service.UserService;

@RestController
@RequestMapping("/api")
public class SavedRecipeController {
	
	@Autowired
	SavedRecipeService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/saved-recipe")
	public List<SavedRecipe> getAllSavedRecipes() {
		return service.getAllSavedRecipes();
	}
	
	@GetMapping("/saved-recipe/{id}")
	public ResponseEntity<SavedRecipe> getSavedRecipeById(@PathVariable int id) throws ResourceNotFoundException {
		SavedRecipe found = service.getSavedRecipeById(id);
		return ResponseEntity.status(200).body(found);
	}
	
	@GetMapping("/saved-recipe/user")
	public List<SavedRecipe> getAllSavedRecipesByUser(@RequestParam int userId) throws ResourceNotFoundException {
		User found = userService.getUserById(userId);
		return service.getAllSavedRecipesByUser(found.getId());
	}
	
	@GetMapping("/saved-recipe/user/recipe")
	public SavedRecipe getSavedRecipeByUserAndRecipe(@RequestParam int userId, @RequestParam int recipeId) throws ResourceNotFoundException {
		User foundUser = userService.getUserById(userId);
		Recipe foundRecipe = recipeService.getRecipeById(recipeId);
		return service.getSavedRecipeByUserAndRecipe(foundUser.getId(), foundRecipe.getId());
	}
	
	@PostMapping("/saved-recipe")
	public ResponseEntity<SavedRecipe> createSavedRecipe(@RequestParam int userId, @RequestParam int recipeId) throws ResourceNotFoundException, ResourceAlreadyExistsException {
		User foundUser = userService.getUserById(userId);
		Recipe foundRecipe = recipeService.getRecipeById(recipeId);
		SavedRecipe created = service.createSavedRecipe(foundUser, foundRecipe);
		return ResponseEntity.status(201).body(created);
	}
	
	@DeleteMapping("/saved-recipe")
	public ResponseEntity<SavedRecipe> deleteSavedLocation(@RequestParam int id) throws ResourceNotFoundException {
		SavedRecipe deleted = service.deleteSavedRecipeById(id);
		return ResponseEntity.status(200).body(deleted);
	}
}
