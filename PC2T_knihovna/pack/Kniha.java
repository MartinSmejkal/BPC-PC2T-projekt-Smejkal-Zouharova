package pack;

import java.io.IOException;

public abstract class Kniha implements Comparable<Kniha> {
	private String nazev;
	private String autor;
	private int rokVydani;
	private String stav;

	public Kniha(String nazev, String autor, int rokVydani, String stav) {
		super();
		this.nazev = nazev;
		this.autor = autor;
		this.rokVydani = rokVydani;
		this.stav = stav;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getRokVydani() {
		return rokVydani;
	}

	public void setRokVydani(int rokVydani) {
		this.rokVydani = rokVydani;
	}

	public String getStav() {
		return stav;
	}

	public void setStav(String stav) {
		this.stav = stav;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Kniha)) {
			if (this.getNazev() == ((Kniha) obj).getNazev()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Kniha kniha) {
		if (this.getNazev().compareTo(kniha.getNazev()) == -1)
			return -1;
		if (this.getNazev().compareTo(kniha.getNazev()) == 1)
			return 1;
		return 0;
	}

	public abstract String vypisKnihu();

	public abstract boolean exportujKnihu() throws IOException;
}
