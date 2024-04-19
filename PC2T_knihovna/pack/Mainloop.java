package pack;

import java.util.Scanner;

public class Mainloop {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Databaze mojeDatabaze = new Databaze();
		boolean run = true;

		mojeDatabaze.importSQL();
		while (run) {
			int volba = 0;

			while (volba == 0) {
				System.out.println("Vyberte pozadovanou cinnost:");
				System.out.println("1 .. vlozeni nove knihy");
				System.out.println("2 .. odstraneni knihy");
				System.out.println("3 .. uprava knihy");
				System.out.println("4 .. vypis informace o knize");
				System.out.println("5 .. vypis databaze");
				System.out.println("6 .. vypis autora");
				System.out.println("7 .. vypis zanru");
				System.out.println("8 .. vypis vypujcenych");
				System.out.println("9 .. export knihy");
				System.out.println("10 .. import knihy");
				System.out.println("11 .. ukonceni aplikace");
				if (sc.hasNextInt()) {
					volba = sc.nextInt();
				}
				sc.nextLine();
			}
			switch (volba) {
			case 1:
				System.out.println(mojeDatabaze.setNewKniha());
				break;
			case 2:
				System.out.println(mojeDatabaze.removeKniha());
				break;
			case 3:
				System.out.println(mojeDatabaze.upravKnihu());
				break;
			case 4:
				System.out.println(mojeDatabaze.printKniha());
				break;
			case 5:
				System.out.println(mojeDatabaze.printDatabaze());
				break;
			case 6:
				System.out.println(mojeDatabaze.printAutor());
				break;
			case 7:
				System.out.println(mojeDatabaze.printZanr());
				break;
			case 8:
				System.out.println(mojeDatabaze.printPujcene());
				break;
			case 9:
				System.out.println(mojeDatabaze.exportKnihaToFile());
				break;
			case 10:
				System.out.println(mojeDatabaze.importKnihaFromFile());
				break;
			case 11:
				run = false;
				sc.close();
				System.out.println(mojeDatabaze.exportSQL());
				break;
			default:
				break;
			}
		}
	}
}
