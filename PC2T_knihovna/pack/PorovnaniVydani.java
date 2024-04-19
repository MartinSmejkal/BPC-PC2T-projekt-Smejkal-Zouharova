package pack;

import java.util.Comparator;

public class PorovnaniVydani implements Comparator<Kniha> {

	@Override
	public int compare(Kniha kniha1, Kniha kniha2) {
		int vydani1 = kniha1.getRokVydani();
		int vydani2 = kniha2.getRokVydani();
		if (vydani1 < vydani2) {
			return -1;
		}
		if (vydani1 > vydani2) {
			return 1;
		}
		return 0;

	}

}
