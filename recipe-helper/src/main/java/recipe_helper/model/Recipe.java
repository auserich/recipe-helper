package recipe_helper.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Recipe implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String ingredients;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<SavedRecipe> savedRecipe;

	public Recipe() {
		super();
	}

	public Recipe(Integer id, String title, String ingredients, List<SavedRecipe> savedRecipe) {
		super();
		this.id = id;
		this.title = title;
		this.ingredients = ingredients;
		this.savedRecipe = savedRecipe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public List<SavedRecipe> getSavedRecipe() {
		return savedRecipe;
	}

	public void setSavedRecipe(List<SavedRecipe> savedRecipe) {
		this.savedRecipe = savedRecipe;
	}
}
