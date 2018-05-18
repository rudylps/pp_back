package co.simplon.projetPerso.model;


public class Logement {
	
	private Long id_logement;
	private int numero;
	private String rue;
	private String complement;
	private String code_postal;
	private String ville;
	private boolean parking;
	private boolean toilettes;
	private boolean salle_de_bain;
	private boolean cuisine;
	private int nombre_lit;
	private int prix;
	
	public Logement() {
		super();
	}

	public Long getId_logement() {
		return id_logement;
	}

	public void setId_logement(Long id_logement) {
		this.id_logement = id_logement;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isToilettes() {
		return toilettes;
	}

	public void setToilettes(boolean toilettes) {
		this.toilettes = toilettes;
	}

	public boolean isSalle_de_bain() {
		return salle_de_bain;
	}

	public void setSalle_de_bain(boolean salle_de_bain) {
		this.salle_de_bain = salle_de_bain;
	}

	public boolean isCuisine() {
		return cuisine;
	}

	public void setCuisine(boolean cuisine) {
		this.cuisine = cuisine;
	}

	public int getNombre_lit() {
		return nombre_lit;
	}

	public void setNombre_lit(int nombre_lit) {
		this.nombre_lit = nombre_lit;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	
}