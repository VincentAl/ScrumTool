package hei.gl.scrumtool.core.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Time pointDepart;

	private Time pointFin;

	private long numero;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
	private List<Story> listeStories;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "sprint")
	private Personne scrumMaster;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "sprint")
	private Personne productOwner;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Time getPointDepart() {
		return pointDepart;
	}

	public void setPointDepart(Time pointDepart) {
		this.pointDepart = pointDepart;
	}

	public Time getPointFin() {
		return pointFin;
	}

	public void setPointFin(Time pointFin) {
		this.pointFin = pointFin;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public List<Story> getListeStories() {
		return listeStories;
	}

	public void setListeStories(List<Story> listeStories) {
		this.listeStories = listeStories;
	}
	
	public void addStory(Story story){
		this.listeStories.add(story);
	}
	
	public void addListStories(List<Story> stories){
		this.listeStories.addAll(stories);
	}
	
	public void removeStory(long idStory){
		for (int i=0;i<this.listeStories.size();i++) {
			if(this.listeStories.get(i).getId()==idStory){
				this.listeStories.remove(i);
				break;
			}
		}
	}

	public Personne getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Personne scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public Personne getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(Personne productOwner) {
		this.productOwner = productOwner;
	}
}
