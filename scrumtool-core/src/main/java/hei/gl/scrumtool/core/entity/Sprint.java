package hei.gl.scrumtool.core.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pointDepart;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pointFin;

	private long numero;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
	private List<Story> listeStories;

	@OneToOne
	private Personne scrumMaster;

	@OneToOne
	private Personne productOwner;


	public void addStory(Story story){
		this.listeStories.add(story);
	}
	
	public void addListStories(List<Story> stories){
		this.listeStories.addAll(stories);
	}
	
	public void removeStory(long idStory){
		Iterator<Story>i=this.listeStories.iterator();
		while (i.hasNext()) {
			Story story = (Story) i.next();
			if(story.getId()==idStory){
				i.remove();
			}
		}
	}

	
	//Getters-Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPointDepart() {
		return pointDepart;
	}

	public void setPointDepart(Date pointDepart) {
		this.pointDepart = pointDepart;
	}

	public Date getPointFin() {
		return pointFin;
	}

	public void setPointFin(Date pointFin) {
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
