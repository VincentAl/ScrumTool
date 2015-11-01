package hei.gl.scrumtool.core.entity;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String titre;

	private String description;

	private String etat;

	private String priorite;

	private Time pointDepart;

	@ManyToOne(cascade = CascadeType.ALL)
	private Story story;
}
