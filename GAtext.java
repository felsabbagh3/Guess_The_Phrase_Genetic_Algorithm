public class GAtext {
	String target;
	int lenPopulation;
	int mutRate;
	Population population;
	public GAtext() {
		target = "to be or not to be";
		lenPopulation = 200;
		mutRate = 4;
		population = new Population(target, lenPopulation, mutRate);
	}

	public boolean loop() {
		boolean found;
		population.calculatePopFitness();
		if (population.checkFound()) {
			return true;
		}
		population.naturalSelection();
		return false;
	}

	public static void main(String[] args) {
		GAtext algo = new GAtext();
		while (!algo.loop());
	}
}

