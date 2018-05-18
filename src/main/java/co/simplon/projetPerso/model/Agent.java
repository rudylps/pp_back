package co.simplon.projetPerso.model;

public class Agent {
	
	private Long id_agent;
	private String nom;
	private String prenom;
	private String bureau_affectation;
	private String mail;
	private String telephone;
	
	public Agent() {
		super();
	}

	public Long getId_agent() {
		return id_agent;
	}

	public void setId_agent(Long id_agent) {
		this.id_agent = id_agent;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getBureau_affectation() {
		return bureau_affectation;
	}

	public void setBureau_affectation(String bureau_affectation) {
		this.bureau_affectation = bureau_affectation;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
