package org.example;

public class GAApplication {

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm=new GeneticAlgorithm();
        geneticAlgorithm.initialize();
        /*Creating a loop to repeat the process until hitting the solution*/
        int generation=1;
        do{
            System.out.println("****************************************************** Generation : "+generation+++" ******************************************************");
            geneticAlgorithm.crossover();
            geneticAlgorithm.mutation();
            geneticAlgorithm.sortPopulation();
        }while (geneticAlgorithm.testSolution() && generation<GAUtils.MAX_GENERATION);

        geneticAlgorithm.showPopulation();
    }
}
