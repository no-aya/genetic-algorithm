package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {

    private Individual[] population=new Individual[GAUtils.POPULATION_SIZE];
    private Individual individual1;
    private Individual individual2;

    public void initialize(){
        for(int i=0;i<GAUtils.POPULATION_SIZE;i++)
            population[i]=new Individual();
        sortPopulation();
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
        //copier chaine de caractere sans reference
        individual1=new Individual();
        System.arraycopy(population[0].chromosome, 0, individual1.chromosome, 0, GAUtils.CHROMOSOME_SIZE);

        individual2=new Individual();
        System.arraycopy(population[1].chromosome, 0, individual2.chromosome, 0, GAUtils.CHROMOSOME_SIZE);

        //Avant le crossover
        System.out.println("Before crossover : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);

        Random random=new Random();
        int crossoverPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE-1)+1;
        System.out.println("Crossover point : "+crossoverPoint);
        for(int i=0;i<=crossoverPoint;i++){
            individual1.chromosome[i]=population[1].chromosome[i];
            individual2.chromosome[i]=population[0].chromosome[i];

        }
        individual1.calculateFitness();
        individual2.calculateFitness();

        //Remplacer les deux dernier individus par les deux nouveaux
        population[GAUtils.POPULATION_SIZE-2]=individual1;
        population[GAUtils.POPULATION_SIZE-1]=individual2;

        //Après le crossover
        System.out.println("After crossover : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);

    }

    public void sortPopulation(){
        Arrays.sort(population, Comparator.comparing(Individual::getFitness).reversed());
    }
    public void mutation(){
        Random random=new Random();
        //indiv 1
        if(random.nextDouble()>GAUtils.MUTATION_PROB){
            int index = random.nextInt(GAUtils.CHROMOSOME_SIZE);
            individual1.chromosome[index]= GAUtils.ALPHABET.charAt((int)(Math.random() * GAUtils.ALPHABET.length()));

        }
        //indiv 2
        if(random.nextDouble()>GAUtils.MUTATION_PROB){
            int index = random.nextInt(GAUtils.CHROMOSOME_SIZE);
            individual2.chromosome[index]= GAUtils.ALPHABET.charAt((int)(Math.random() * GAUtils.ALPHABET.length()));
        }
        System.out.println("After mutation : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);
        individual1.calculateFitness();
        individual2.calculateFitness();
        population[GAUtils.POPULATION_SIZE-2]=individual1;
        population[GAUtils.POPULATION_SIZE-1]=individual2;

    }
    public int getBestFitness(){
        return population[0].getFitness();
    }


}
