package recipe_helper.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;
	
	@Column(nullable = false)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<SavedRecipe> savedRecipe;

	public User() {
		super();
	}

	public User(Integer id, String username, String password, Role role, boolean enabled, String email,
			List<SavedRecipe> savedRecipe) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.email = email;
		this.savedRecipe = savedRecipe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SavedRecipe> getSavedRecipe() {
		return savedRecipe;
	}

	public void setSavedRecipe(List<SavedRecipe> savedRecipe) {
		this.savedRecipe = savedRecipe;
	}
}
