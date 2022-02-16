package dao;

public class Etudiant {
    private int id ;
    private static int compteur;
    private String nom;
    private String prenom ;
    private String filiere ;

    public Etudiant(String nom, String prenom, String filiere) {
        this.id= ++compteur;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
    }

    public Etudiant(int id, String nom, String prenom, String filiere) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.filiere = filiere;
	}

	public Etudiant() {
        this.id =  ++compteur;
        this.nom =null;
        this.prenom = null;
        this.filiere =null;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", filiere=" + filiere + "]";
	}
	
	public void affiche() {
		System.out.println(toString());
	}
    
    
}