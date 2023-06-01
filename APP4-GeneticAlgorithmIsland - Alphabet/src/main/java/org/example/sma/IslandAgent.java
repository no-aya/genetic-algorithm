package org.example.sma;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import org.example.sequencial.GAUtils;
import org.example.sequencial.Individual;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class IslandAgent extends Agent {
    private Individual[] population=new Individual[GAUtils.POPULATION_SIZE];
    private Individual individual1;
    private Individual individual2;

    @Override
    public void setup(){
        System.out.println("Hello! Island-agent "+getAID().getName()+" is ready.");

        /*DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ga");
        sd.setName("island");
        dfd.addServices(sd);

        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
*/
        SequentialBehaviour sb = new SequentialBehaviour();

        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                initialize();
                sortPopulation();
            }
        });
        sb.addSubBehaviour(new Behaviour() {
            int iter=0;
            @Override
            public void action() {
                crossover();
                mutation();
                sortPopulation();
                iter++;
            }

            @Override
            public boolean done() {
                return GAUtils.MAX_GENERATION==iter || getBestFitness()==GAUtils.CHROMOSOME_SIZE;
            }
        });
        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                DFAgentDescription dfd=new DFAgentDescription();
                ServiceDescription sd=new ServiceDescription();
                sd.setType("ga");
                dfd.addServices(sd);
                DFAgentDescription[] result=null;
                try {
                    result=DFService.search(getAgent(),dfd);
                } catch (FIPAException e) {
                    throw new RuntimeException(e);
                }
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(result[0].getName());
                msg.setContent(getBestFitness()+" | "+Arrays.toString(population[0].chromosome));
                send(msg);
            }
        });
        addBehaviour(sb);

    }

    private int getBestFitness() {
        return population[0].getFitness();
    }


    public void initialize(){
        for(int i=0;i<GAUtils.POPULATION_SIZE;i++) {
            population[i] = new Individual();
            population[i].calculateFitness();
        }
        System.out.println("Initial population : ");
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
        individual1=new Individual(population[0].chromosome);
        individual2=new Individual(population[1].chromosome);

        //Avant le crossover
        /*System.out.println("Before crosssover : ");
        System.out.println("Individual 1 : "+Arrays.toString(individual1.chromosome)+" | fitness : "+individual1.fitness);
        System.out.println("Individual 2 : "+Arrays.toString(individual2.chromosome)+" | fitness : "+individual2.fitness);*/

        Random random=new Random();
        int crossoverPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE-1);
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
            individual1.chromosome[mutationPoint]=GAUtils.APLHABET.charAt(random.nextInt(GAUtils.APLHABET.length()));
        }
        // Individu 2
        if(random.nextDouble()>GAUtils.MUTATION_RATE){
            int mutationPoint=random.nextInt(GAUtils.CHROMOSOME_SIZE);
            individual2.chromosome[mutationPoint]=GAUtils.APLHABET.charAt(random.nextInt(GAUtils.APLHABET.length()));
        }
    }


    @Override
    public void takeDown(){
            try {
                DFService.deregister(this);
            } catch (FIPAException e) {
                throw new RuntimeException(e);
            }
    }

}
