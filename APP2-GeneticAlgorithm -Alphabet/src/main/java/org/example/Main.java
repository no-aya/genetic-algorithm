package org.example;

public class Main {
    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm=new GeneticAlgorithm();
        geneticAlgorithm.initialize();
        /*
        geneticAlgorithm.showPopulation();
        geneticAlgorithm.crossover();
        System.out.println(GAUtils.TARGET);
        geneticAlgorithm.showPopulation();

         */

        int i=0;
        while(i < GAUtils.MAX_ITERATIONS && geneticAlgorithm.getBestFitness()<GAUtils.CHROMOSOME_SIZE){
            System.out.println("Iteration : "+i);
            geneticAlgorithm.crossover();
            geneticAlgorithm.mutation();
            geneticAlgorithm.sortPopulation();
            geneticAlgorithm.showPopulation();
            i++;
        }
    }
}