package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Databaze {

	public Databaze() {
		prvkyDatabaze = new TreeMap<String, Kniha>();
		sc = new Scanner(System.in);
		sql = new SQL();
	}

	public SQL getSQL() {
		return this.sql;
	}

	public String setNewKniha() {
		int volba = 0;
		while (!(volba == 1 || volba == 2)) {
			System.out.println("Zadejte 1 pro ucenbici a 2 pro roman");
			if (sc.hasNextInt()) {
				volba = sc.nextInt();
			}
			sc.nextLine();
		}
		String nazev = "";
		String autor = "";
		int rokVydani = 0;
		String stav = "";
		String vhodnost = "";
		String zanr = "";
		if (volba == 1) {
			while (nazev == "") {
				System.out.println("Zadejte nazev teto ucebnice");
				nazev = sc.nextLine();
			}
			while (autor == "") {
				System.out.println("Zadejte autora teto ucebnice");
				autor = sc.nextLine();
			}
			while (rokVydani == 0) {
				System.out.println("Zadejte rok vydani teto ucebnice");
				if (sc.hasNextInt()) {
					rokVydani = sc.nextInt();
				}
				sc.nextLine();
			}
			while (!(stav.equals("k dispozici") || stav.equals("vypujceno"))) {
				System.out.println("Zadejte stav teto ucebnice (k dispozici/vypujceno)");
				stav = sc.nextLine();
			}
			while (vhodnost == "") {
				System.out.println("Zadejte vhodnost teto ucebnice");
				vhodnost = sc.nextLine();
			}
			return setKniha(volba, nazev, autor, rokVydani, stav, vhodnost);
		} else {
			while (nazev == "") {
				System.out.println("Zadejte nazev tohoto romanu");
				nazev = sc.nextLine();
			}
			while (autor == "") {
				System.out.println("Zadejte autora tohoto romanu");
				autor = sc.nextLine();
			}
			while (rokVydani == 0) {
				System.out.println("Zadejte rok vydani tohoto romanu");
				if (sc.hasNextInt()) {
					rokVydani = sc.nextInt();
				}
				sc.nextLine();
			}
			while (!(stav.equals("k dispozici") || stav.equals("vypujceno"))) {
				System.out.println("Zadejte stav tohoto romanu (k dispozici/vypujceno)");
				stav = sc.nextLine();
			}
			while (zanr == "") {
				System.out.println("Zadejte zanr tohoto romanu");
				zanr = sc.nextLine();
			}
			return setKniha(volba, nazev, autor, rokVydani, stav, zanr);
		}
	}

	public String setKniha(int volba, String nazev, String autor, int rokVydani, String stav, String vhodnostORzanr) {
		if (volba == 1) {
			if (!prvkyDatabaze.containsKey(nazev)) {
				prvkyDatabaze.put(nazev, new Ucebnice(nazev, autor, rokVydani, stav, vhodnostORzanr));
				return "Ucebnice se jmenem " + nazev + " byla uspesne pridana do databaze\n";
			} else {
				return "Kniha se jmenem " + nazev + " uz je v databazi\n";
			}
		}
		if (volba == 2) {
			if (!prvkyDatabaze.containsKey(nazev)) {
				prvkyDatabaze.put(nazev, new Roman(nazev, autor, rokVydani, stav, vhodnostORzanr));
				return "Roman se jmenem " + nazev + " byla uspesne pridan do databaze\n";
			} else {
				return "Kniha se jmenem " + nazev + " uz je v databazi\n";
			}
		}
		return "chyba pri pridavani knihy\n";
	}

	public String removeKniha() {
		String nazev = "";
		while (nazev == "") {
			System.out.println("Zadejte jmeno knihy k odstraneni");
			nazev = sc.nextLine();
		}
		if (prvkyDatabaze.containsKey(nazev)) {
			prvkyDatabaze.remove(nazev);
			return "Kniha se jmenem " + (String) nazev + " byla uspesne odstranena\n";
		} else {
			return "Kniha se jmenem " + (String) nazev + " nebyla nalezena v databazi\n";
		}
	}

	public String upravKnihu() {
		String nazev = "";
		while (nazev == "") {
			System.out.println("Zadejte jmeno knihy k uprave");
			nazev = sc.nextLine();
		}
		if (prvkyDatabaze.containsKey(nazev)) {
			Kniha kniha = prvkyDatabaze.get(nazev);
			boolean run = true;
			int volba = 0;
			int rok = 0;
			String autor = "";
			while (run) {
				System.out.println("Vyberte pozadovanou cinnost:");
				System.out.println("1 .. zmena autora");
				System.out.println("2 .. zmena roku vydani");
				System.out.println("3 .. zmena dostupnosti");
				System.out.println("4 .. konec zmen");
				if (sc.hasNextInt()) {
					volba = sc.nextInt();
				}
				sc.nextLine();
				switch (volba) {
				case 1:
					while (autor == "") {
						System.out.println("Zadejte noveho autora");
						autor = sc.nextLine();
					}
					kniha.setAutor(autor);
					System.out.println("Autor uspesne zmenen");
					break;
				case 2:
					while (rok == 0) {
						System.out.println("Zadejte novy rok vydani");
						if (sc.hasNextInt()) {
							rok = sc.nextInt();
						}
						sc.nextLine();
					}
					kniha.setRokVydani(rok);
					System.out.println("Rok vydani uspesne zmenen");
					break;
				case 3:
					if (kniha.getStav().equals("k dispozici")) {
						kniha.setStav("vypujceno");
						System.out.println("Zmena stavu na: vypujceno");
						break;
					} else {
						kniha.setStav("k dispozici");
						System.out.println("Zmena stavu na: k dispozici");
						break;
					}
				case 4:
					run = false;
					break;
				}
			}
			return "Zmeny na knize " + nazev + " dokonceny\n";
		} else {
			return "Kniha se jmenem " + (String) nazev + " nebyla nalezena v databazi\n";
		}
	}

	public String printKniha() {
		String nazev = "";
		while (nazev == "") {
			System.out.println("Zadejte jmeno knihy");
			nazev = sc.nextLine();
		}
		if (prvkyDatabaze.containsKey(nazev)) {
			return prvkyDatabaze.get(nazev).vypisKnihu() + "\n";
		} else {
			return "Kniha se jmenem " + (String) nazev + " nebyla nalezena v databazi\n";
		}
	}

	public String exportKnihaToFile() {
		String nazev = "";
		while (nazev == "") {
			System.out.println("Zadejte jmeno knihy");
			nazev = sc.nextLine();
		}

		if (prvkyDatabaze.containsKey(nazev)) {
			try {
				if (prvkyDatabaze.get(nazev).exportujKnihu()) {
					return "Kniha se jmenem " + nazev + " uspesne exportovana\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
				return "Nastala chyba pri zapisu do souboru\n";
			}
		}
		return "Kniha se jmenem " + (String) nazev + " nebyla nalezena v databazi\n";

	}

	public String importKnihaFromFile() {
		System.out.println("Adresar projektu s predprivanemi a hotovymi knihami");
		File localdir = new File(System.getProperty("user.dir"));
		System.out.println(localdir.getPath());
		File[] soubory = localdir.listFiles();
		for (File soubor : soubory) {
			System.out.println("* " + soubor.getName());
		}
		String typ = "";
		String nazev = "";
		String autor = "";
		int rokVydani = 0;
		String stav = "";
		String vhodnostORzanr = "";
		String path = "";
		while (path == "") {
			System.out.println("Zadejte cestu k souboru");
			path = sc.nextLine();
			System.out.println(path);
		}
		try {
			File soubor = new File(path);
			if (soubor.isFile() && soubor.canRead()) {
				FileReader cteni = new FileReader(soubor);
				BufferedReader bufferdCteni = new BufferedReader(cteni);
				String radek;
				String[] radekArray = new String[2];
				if ((radek = bufferdCteni.readLine()) != null) {
					radek.trim();
					radekArray = radek.split(":", 2);
					typ = radekArray[1].substring(1);
					if (typ.equals("Roman") || typ.equals("Ucebnice")) {

						if ((radek = bufferdCteni.readLine()) != null) {
							radek.trim();
							radekArray = radek.split(":", 2);
							nazev = radekArray[1].substring(1);
						} else {
							cteni.close();
							bufferdCteni.close();
							return "Soubor neobsahuje vsechna potrebna data//poruseny format//predcasny konec\n";
						}

						if ((radek = bufferdCteni.readLine()) != null) {
							radek.trim();
							radekArray = radek.split(":", 2);
							autor = radekArray[1].substring(1);
						} else {
							cteni.close();
							bufferdCteni.close();
							return "Soubor neobsahuje vsechna potrebna data//poruseny format//predcasny konec\n";
						}

						if ((radek = bufferdCteni.readLine()) != null) {
							radek.trim();
							radekArray = radek.split(":", 2);
							rokVydani = Integer.parseInt(radekArray[1].substring(1));
						} else {
							cteni.close();
							bufferdCteni.close();
							return "Soubor neobsahuje vsechna potrebna data//poruseny format//predcasny konec\n";
						}

						if ((radek = bufferdCteni.readLine()) != null) {
							radek.trim();
							radekArray = radek.split(":", 2);
							stav = radekArray[1].substring(1);
						} else {
							cteni.close();
							bufferdCteni.close();
							return "Soubor neobsahuje vsechna potrebna data//poruseny format//predcasny konec\n";
						}

						if ((radek = bufferdCteni.readLine()) != null) {
							radek.trim();
							radekArray = radek.split(":", 2);
							vhodnostORzanr = radekArray[1].substring(1);
						} else {
							cteni.close();
							bufferdCteni.close();
							return "Soubor neobsahuje vsechna potrebna data//poruseny format//predcasny konec\n";
						}

						if (typ.equals("Ucebnice")) {
							setKniha(1, nazev, autor, rokVydani, stav, vhodnostORzanr);
							cteni.close();
							bufferdCteni.close();
							return typ + " s nasledujicimi udaji byla uspesne importovana\n " + "nazev: " + nazev
									+ " autor: " + autor + " rok Vydani: " + rokVydani + " stav: " + stav
									+ " vhodnost: " + vhodnostORzanr + "\n";
						} else {
							setKniha(2, nazev, autor, rokVydani, stav, vhodnostORzanr);
							cteni.close();
							bufferdCteni.close();
							return typ + " s nasledujicimi udaji byla uspesne importovana\n " + "nazev: " + nazev
									+ "\nautor: " + autor + "\nrok Vydani: " + rokVydani + "\nstav: " + stav
									+ "\nzanr: " + vhodnostORzanr + "\n";
						}
					} else {
						cteni.close();
						bufferdCteni.close();
						return "Soubor se nepodarilo identifikovat jako Ucebnici ci Roman\n";
					}
				} else {
					cteni.close();
					bufferdCteni.close();
					return "Soubor je prazdny\n";
				}
			} else {
				return "soubor se nepodarilo otevrit ke cteni\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Nastala chyba pri cteni ze souboru\n";
		}
	}

	public String exportSQL() {
		sql.createTable();
		if (sql.deleteTable()) {
			System.out.print("Odstranena predesla tabulka,");
		}
		if (!prvkyDatabaze.isEmpty()) {
			Set<String> knihy = prvkyDatabaze.keySet();
			for (String kniha : knihy) {
				if (prvkyDatabaze.get(kniha) instanceof Roman) {
					Roman roman = (Roman) prvkyDatabaze.get(kniha);
					if (!sql.insertRecord(roman.getNazev(), "Roman", roman.getAutor(), roman.getRokVydani(),
							roman.getStav(), roman.getZanr())) {
						sql.disconnect();
						return "Nastala chyba pri vkladani";
					}
				} else {
					Ucebnice ucebnice = (Ucebnice) prvkyDatabaze.get(kniha);
					if (!sql.insertRecord(ucebnice.getNazev(), "Ucebnice", ucebnice.getAutor(), ucebnice.getRokVydani(),
							ucebnice.getStav(), ucebnice.getVhodnost())) {
						sql.disconnect();
						return "Nastala chyba pri vkladani";
					}
				}
			}
			sql.disconnect();
			return "Export do SQL probehl uspesne";
		} else {
			sql.disconnect();
			return "Databaze je doposud prazdna\n";
		}
	}

	public String importSQL() {
		sql.connect();
		sql.createTable();
		ResultSet sqlVypis = sql.selectAll();
		if (sqlVypis == null) {
			return "SQL je prazdne";
		}
		String typ = "";
		String nazev = "";
		String autor = "";
		int rokVydani = 0;
		String stav = "";
		String vhodnostORzanr = "";
		try {
			while (sqlVypis.next()) {
				nazev = sqlVypis.getString("nazev");
				typ = sqlVypis.getString("druh");
				autor = sqlVypis.getString("autor");
				rokVydani = sqlVypis.getInt("rok_vydani");
				stav = sqlVypis.getString("stav");
				vhodnostORzanr = sqlVypis.getString("vhodnost_nebo_zanr");
				if (typ.equals("Ucebnice")) {
					setKniha(1, nazev, autor, rokVydani, stav, vhodnostORzanr);
				} else {
					setKniha(2, nazev, autor, rokVydani, stav, vhodnostORzanr);
				}
			}
			sqlVypis.close();
			return "SQL uspesne importovano";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return "SQL nebylo importovano";
		}
	}

	public String printDatabaze() {
		if (!prvkyDatabaze.isEmpty()) {
			Set<String> knihy = prvkyDatabaze.keySet();
			for (String kniha : knihy) {
				System.out.println(prvkyDatabaze.get(kniha).vypisKnihu() + "\n");
			}
			return "\n";
		} else {
			return "Databaze je doposud prazdna\\n";
		}
	}

	public String printAutor() {
		if (!prvkyDatabaze.isEmpty()) {
			Set<String> knihy = prvkyDatabaze.keySet();
			String autor = "";
			while (autor == "") {
				System.out.println("Zvolte autora");
				autor = sc.nextLine();
			}
			System.out.println("Autor: \n");
			TreeSet<Kniha> podleData = new TreeSet<Kniha>(new PorovnaniVydani());
			for (String kniha : knihy) {
				if (prvkyDatabaze.get(kniha).getAutor().equals(autor)) {
					podleData.add(prvkyDatabaze.get(kniha));
				}
			}
			if (!podleData.isEmpty()) {
				for (Kniha kniha : podleData) {
					System.out.println(kniha.vypisKnihu() + "\n");
				}
				return "\n";
			} else {
				return autor + " nema v databazi zadnou knihu\\n";
			}
		} else {
			return "Databaze je doposud prazdna\\n";
		}
	}

	public String printZanr() {
		if (!prvkyDatabaze.isEmpty()) {
			String zanr = "";
			Set<String> knihy = prvkyDatabaze.keySet();
			while (zanr == "") {
				System.out.println("Zvolte zanr");
				zanr = sc.nextLine();
			}
			System.out.println(zanr + ": \n");
			for (String kniha : knihy) {
				if (prvkyDatabaze.get(kniha) instanceof Roman) {
					Roman roman = (Roman) prvkyDatabaze.get(kniha);
					if (roman.getZanr().equals(zanr)) {
						System.out.println(prvkyDatabaze.get(kniha).getNazev() + "\n");
					}
				}

			}
			return "\n";
		} else {
			return "Databaze je doposud prazdna\n";
		}
	}

	public String printPujcene() {
		if (!prvkyDatabaze.isEmpty()) {
			Set<String> knihy = prvkyDatabaze.keySet();
			System.out.println("vypujceno: \n");
			for (String kniha : knihy) {
				if (prvkyDatabaze.get(kniha).getStav().equals("vypujceno")) {
					if (prvkyDatabaze.get(kniha) instanceof Roman) {
						System.out.println(prvkyDatabaze.get(kniha).getNazev() + " (Roman)\n");
					} else {
						System.out.println(prvkyDatabaze.get(kniha).getNazev() + " (Ucebnice)\n");
					}
				}
			}
			return "\n";
		} else {
			return "Databaze je doposud prazdna\n";
		}
	}

	private Scanner sc;
	private TreeMap<String, Kniha> prvkyDatabaze;
	private SQL sql;
}