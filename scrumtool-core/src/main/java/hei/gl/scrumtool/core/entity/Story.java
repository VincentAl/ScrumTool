package hei.gl.scrumtool.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Story {
	public static final int IDEA = 1;
	public static final int CONFIRMED = 2;
	public static final int NEXT_SPRINT = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long storyPoints;
	
	private int categorie;
	
	private String description;

	@ManyToOne
	private Sprint sprint;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "story")
	private Set<Tache> listeTaches;

	private String titre;

	// Getters-Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(long storyPoints) {
		this.storyPoints = storyPoints;
	}

	public Set<Tache> getListeTaches() {
		return listeTaches;
	}

	public void setListeTaches(Set<Tache> listeTaches) {
		this.listeTaches = listeTaches;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
