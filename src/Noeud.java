
public class Noeud {
	
	private int element;
	private Noeud parent;
	private Noeud gauche;
	private Noeud droite;

	Noeud(int e) {
		this.element = e;
		this.gauche = null;
		this.droite = null;
	}
	
	Noeud(int e, Noeud parent) {
	    this.element = e;
	    this.parent = parent;
	    this.gauche = null;
	    this.droite = null;
	}


	public Noeud getGauche() {
		return gauche;
	}

	public void setGauche(Noeud gauche) {
		this.gauche = gauche;
	}

	public Noeud getDroite() {
		return droite;
	}

	public void setDroite(Noeud droite) {
		this.droite = droite;
	}

	public int getElement() {
		return element;
	}
	
	public void setElement(int element) {
	    this.element = element;
	}
	
	public Noeud getParent() {
	    return parent;
	}

	public void setParent(Noeud parent) {
	    this.parent = parent;
	}
	
	public boolean estFeuille() {
	    return gauche == null && droite == null;
	}
	
	public boolean aEnfant() {
	    return gauche != null || droite != null;
	}

	@Override
	public String toString() {
	    return "Noeud{" +
	           "element=" + element +
	           ", gauche=" + gauche +
	           ", droite=" + droite +
	           '}';
	}

}
