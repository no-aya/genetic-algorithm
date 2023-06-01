package org.example.sma;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class MasterAgent extends Agent {
    protected void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ga");
        sd.setName("masterAgent");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        }catch (FIPAException e){
            throw new RuntimeException(e);
        }
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receivedMessage = receive();
                if (receivedMessage != null) {
                    System.out.println(receivedMessage.getSender().getName()+" | Fitness: "+receivedMessage.getContent());
                } else {
                    block();
                }
            }
        });
    }
}
