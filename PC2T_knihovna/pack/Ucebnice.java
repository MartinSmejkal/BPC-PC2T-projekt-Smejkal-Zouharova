package pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ucebnice extends Kniha {

	private String vhodnost;

	public Ucebnice(String nazev, String autor, int rokVydani, String stav, String vhodnost) {
		super(nazev, autor, rokVydani, stav);
		this.vhodnost = vhodnost;
	}

	public String getVhodnost() {
		return vhodnost;
	}

	public void setVhodnost(String vhodnost) {
		this.vhodnost = vhodnost;
	}

	@Override
	public String vypisKnihu() {
		String informace = "Nazev: " + this.getNazev() + "\n" + "Autor: " + this.getAutor() + "\n" + "Rok vydani: "
				+ this.getRokVydani() + "\n" + "Stav: " + this.getStav() + "\n" + "Vhodnost: " + this.getVhodnost();
		return informace;
	}

	@Override
	public boolean exportujKnihu() throws IOException {
		String fileName = this.getNazev() + ".txt";
		File exportFile = new File(System.getProperty("user.dir"), fileName);
		exportFile.createNewFile();
		if (exportFile.canWrite()) {
			FileWriter zapis = new FileWriter(exportFile);
			try {
				zapis.write("Druh: Ucebnice\n");
				zapis.write(this.vypisKnihu());
				zapis.close();
				return true;
			} catch (IOException e) {
				zapis.close();
				throw e;
			}
		} else {
			return false;
		}
	}
}
