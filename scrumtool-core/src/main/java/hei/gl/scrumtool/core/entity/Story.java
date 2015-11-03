package hei.gl.scrumtool.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import hei.gl.scrumtool.core.enumeration.ColonneStory;


@Entity
public class Story {
	
	private ColonneStory category;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long storyPoints;
	
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

	public ColonneStory getCategory() {
		return category;
	}

	public void setCategory(ColonneStory colonneStory) {
		this.category = colonneStory;
	}

}
