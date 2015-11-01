package hei.gl.scrumtool.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long numero;

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

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
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

}
