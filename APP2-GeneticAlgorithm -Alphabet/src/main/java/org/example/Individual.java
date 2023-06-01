package org.example;

import java.util.Random;

public class Individual implements Comparable{

    Random random = new Random();
    char[] chromosome = new char[GAUtils.CHROMOSOME_SIZE];
    int fitness;

    public Individual() {
        //chromosome contient tous les lettre de l'alphabet
        for (int i = 0; i < GAUtils.CHROMOSOME_SIZE; i++) {

            chromosome[i] = GAUtils.ALPHABET.charAt((int)(Math.random() * GAUtils.ALPHABET.length()));

        }
        calculateFitness();
    }

    public Individual(char[] chromosome) {
        this.chromosome = chromosome;
        calculateFitness();
    }

    public void calculateFitness() {
        fitness = 0;
        for (int i = 0; i < GAUtils.CHROMOSOME_SIZE; i++) {
            if (chromosome[i] == GAUtils.TARGET.charAt(i)) {
                fitness++;
            }
        }
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Object o) {
        Individual individual = (Individual) o;
        if (this.fitness > individual.fitness) {
            return 1;
        }
        return 0;
    }
}
