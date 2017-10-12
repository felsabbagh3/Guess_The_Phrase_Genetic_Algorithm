import java.util.Random;
public class DNA {
	String characters = " abcdefghijklmnopqrstuvwxyz";
	Random rand = new Random();
	char[] genes;
	double fitness;
	int lenDNA;
	boolean theOne;
	int mutRate;

	public DNA(int n, int mutRate) {
		lenDNA = n;
		genes = new char[lenDNA];
		fitness = 0;
		for (int i = 0; i < lenDNA; i++) {
			genes[i] = characters.charAt(rand.nextInt(characters.length()));
		}
		this.mutRate = mutRate;
		theOne = false;
	}

	public DNA(char[] genes, int n, int mutRate) {
		lenDNA = n;
		this.mutRate = mutRate;
		fitness = 0;
		this.genes = genes;
		theOne = false;
	}

	public void calculateFitness(String target) {
		double fit = 0;
		for (int i = 0; i < lenDNA; i++) {
			if (genes[i] == target.charAt(i)) fit++;
		}
		fitness = fit / ( (double) lenDNA);
		if (fitness == 1.0) {
			theOne = true;
		} else {
			theOne = false;
		}
	}


	public boolean getFound() {
		return theOne;
	}

	public double getFitness() {
		return fitness;
	}

	public char[] getGenes() {
		return genes;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lenDNA; i++) {
			sb.append(genes[i]);
		}
		return sb.toString();
	}

	public DNA crossOver(DNA other) {
		char[] newGenes = new char[lenDNA];
		char[] otherGenes = other.getGenes();
		int mid = rand.nextInt(lenDNA);
		for (int i = 0; i < mid; i++) {
			newGenes[i] = genes[i];
		}
		for (int i = mid; i < lenDNA; i++) {
			newGenes[i] = otherGenes[i];
		}

		for (int i = 0; i < lenDNA; i++) {
			if (rand.nextInt(100) < mutRate) {
				newGenes[i] = characters.charAt(rand.nextInt(characters.length()));
			}
		}


		return new DNA(newGenes, lenDNA, mutRate);
	}


}