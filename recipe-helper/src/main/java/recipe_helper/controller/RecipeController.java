package recipe_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import recipe_helper.exception.ResourceNotFoundException;
import recipe_helper.model.Recipe;
import recipe_helper.service.RecipeService;
import recipe_helper.service.UserService;

@RestController
@RequestMapping("/api")
public class RecipeController {
	
	@Value("${api.key}")
	private String API_KEY;
	
	@Autowired
	RecipeService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/recipe")
	public List<Recipe> getAllRecipes() {
		return service.getAllRecipes();
	}
	
	@GetMapping("recipe-name/{name}")
	public ResponseEntity<?> getRecipesFromName(@PathVariable String name) {
		String apiUrl = "https://api.api-ninjas.com/v1/recipe?query=" + name;
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Api-Key", API_KEY);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
		String responseBody = responseEntity.getBody();
		
		return ResponseEntity.status(responseEntity.getStatusCode()).body(responseBody);
	}
	
	@GetMapping("/recipe-id/{id}")
	public ResponseEntity<?> getRecipeFromId(@PathVariable int id) throws ResourceNotFoundException {
		Recipe found = service.getRecipeById(id);
		return ResponseEntity.status(200).body(found);
	}
	
	@PostMapping("/recipe")
	public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) throws ResourceNotFoundException {
		Recipe created = service.createRecipe(recipe);
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/recipe")
	public ResponseEntity<?> updateRecipe(@RequestBody Recipe recipe) throws ResourceNotFoundException {
		Recipe updated = service.updateRecipe(recipe);
		return ResponseEntity.status(200).body(updated);
	}
	
	@DeleteMapping("/recipe/{id}")
	public ResponseEntity<?> deleteRecipeById(@PathVariable int id) throws ResourceNotFoundException {
		Recipe deleted = service.deleteRecipeById(id);
		return ResponseEntity.status(200).body(deleted);
	}
}
