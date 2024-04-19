package pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Roman extends Kniha {

	private String zanr;
	
	public Roman(String nazev, String autor, int rokVydani, String stav, String zanr) {
		super(nazev, autor, rokVydani, stav);
		this.zanr=zanr;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	@Override
	public String vypisKnihu() {
		String informace=	"Nazev: " + this.getNazev() + "\n" +
							"Autor: " + this.getAutor() + "\n" +
							"Rok vydani: " + this.getRokVydani() + "\n" +
							"Stav: " + this.getStav()+ "\n" +
							"Zanr: " + this.getZanr(); 
		return informace;
	}
	
	@Override
	public boolean exportujKnihu() throws IOException {
		String fileName=this.getNazev() + ".txt";
		File exportFile=new File(System.getProperty("user.dir"), fileName);
		exportFile.createNewFile();
		if(exportFile.canWrite()) {
			FileWriter zapis = new FileWriter(exportFile);
			try {
				zapis.write("Druh: Roman\n");
				zapis.write(this.vypisKnihu());
				zapis.close();
				return true;
			} 
			catch (IOException e) {
				zapis.close();
				throw e;
			}				
		}else{
			return false;
		}
	}
}
