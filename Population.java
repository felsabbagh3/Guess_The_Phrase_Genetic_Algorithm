import java.util.ArrayList;
import java.util.Random;
public class Population {
	int gen;
	String target;
	int lenPopulation;
	int mutRate;
	DNA[] population;
	DNA theOne;
	ArrayList<DNA> parents;
	double maxFitness;
	Random rand = new Random();

	public Population(String target, int lenPopulation, int mutRate) {
		this.gen = 1;
		this.target = target;
		this.lenPopulation = lenPopulation;
		this.mutRate = mutRate;

		population = new DNA[lenPopulation];
		parents = new ArrayList<DNA>(lenPopulation);

		for (int i = 0; i < lenPopulation; i++) {
			population[i] = new DNA(target.length(), mutRate);
		}
	}

	public void calculatePopFitness() {
		for (int i = 0; i < lenPopulation; i++) {
			population[i].calculateFitness(target);
		}
		maxFitness();
	}

	public boolean checkFound() {
		print();
		for (int i = 0; i < lenPopulation; i++) {
			if (population[i].getFound()) {
				theOne = population[i];
				return true;
			}
		}
		return false;
	}

	private void print() {
		System.out.printf("Generation: %d\n", gen);
		System.out.printf("Best Match: ");
		DNA bestMatch = getBestMatch();
		System.out.println(bestMatch.toString());
		System.out.printf("currMaxFit: %f\n", maxFitness);
		System.out.printf("Population size: %d\n", lenPopulation);
		System.out.printf("mutRate: %d\n\n\n", mutRate);
	}

	private DNA getBestMatch() {
		for (int i = 0; i < lenPopulation; i++) {
			if (population[i].getFitness() == maxFitness) {
				return population[i];
			}
		}
		return null;
	}

	public void naturalSelection() {
		gen++;
		makeParents();
		DNA child;
		DNA one;
		DNA two;
		for (int i = 0; i < lenPopulation; i++) {
			int oneRand = rand.nextInt(parents.size());
			int twoRand = rand.nextInt(parents.size());
			one = parents.get(oneRand);
			two = parents.get(twoRand);
			child = one.crossOver(two);
			population[i] = child;
		}
	}

	private void makeParents() {
		parents.clear();
		double weight;
		int n;
		for (int i = 0; i < lenPopulation; i++) {
			weight = (population[i].getFitness() / maxFitness) * 100;
			n = (int) Math.floor(weight);
			for (int z = 0; z < n; z++) {
				parents.add(population[i]);
			}
		}
	}

	private void maxFitness() {
		maxFitness = 0;
		for (int i = 0; i < lenPopulation; i++) {
			if (maxFitness < population[i].getFitness()) {
				maxFitness = population[i].getFitness();
			}
		}
	}
}