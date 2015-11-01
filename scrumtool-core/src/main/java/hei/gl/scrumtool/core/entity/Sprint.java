package hei.gl.scrumtool.core.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Entity;

@SuppressWarnings("deprecation")
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
}
