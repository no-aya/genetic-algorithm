package org.example;

import java.util.*;

public class GeneticAlgorithm {
    private Individual[] population=new Individual[GAUtils.POPULATION_SIZE];
    private Individual individual1;
    private Individual individual2;

    public void initialize(){
        for(int i=0;i<GAUtils.POPULATION_SIZE;i++) {
            population[i] = new Individual();
            population[i].calculateFitness();
        }
        System.out.println("Initial population : ");
        sortPopulation();
        showPopulation();
    }

    public void showPopulation(){
        System.out.println("Population : ");
        for(Individual individual:population){
            System.out.println(Arrays.toString(individual.chromosome) + " | fitness : "+individual.fitness);
        }
    }

    /*public void selection(){
        //Matting process
        individual1=population[0];
        individual2=population[1];
    }*/
    public void crossover(){
        individual1=new Individual(population[0].chromosome);
        individual2=new Individual(population[1].chromosome);

        //Avant le crossover
        /*System.out.println("Before crosssover : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);*/

        Random random=new Random();
        int crossoverPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE-1)+1;
        //System.out.println("Crossover point : "+crossoverPoint);
        for(int i=0;i<=crossoverPoint;i++){
            individual1.chromosome[i]=population[1].chromosome[i];
            individual2.chromosome[i]=population[0].chromosome[i];
        }
        individual1.calculateFitness();
        individual2.calculateFitness();

        //Après le crossover
        /*System.out.println("After crossover : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);*/


        /*On remplace les derniers individus de la population par les nouveaux individus*/
        population[GAUtils.POPULATION_SIZE-2]=individual1;
        population[GAUtils.POPULATION_SIZE-1]=individual2;

    }

    public void sortPopulation(){
        Arrays.sort(population, Collections.reverseOrder());
    }

    public void mutation(){
        Random random=new Random();
        // Individu 1
        if(random.nextDouble()>GAUtils.MUTATION_RATE){
            int mutationPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE);
            individual1.chromosome[mutationPoint]=1-individual1.chromosome[mutationPoint];
        }
        // Individu 2
        if(random.nextDouble()>GAUtils.MUTATION_RATE){
            int mutationPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE);
            individual2.chromosome[mutationPoint]=1-individual2.chromosome[mutationPoint];
        }
    }

    public boolean testSolution() {
        if (population[0].fitness == GAUtils.MAX_FITNESS) {
            System.out.println("Solution found : " + Arrays.toString(population[0].chromosome));
            return false;
        }
        return true;
    }
}
