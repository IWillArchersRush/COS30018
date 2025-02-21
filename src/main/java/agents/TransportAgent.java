package agents;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class TransportAgent extends Agent {
    protected void setup() {
        System.out.println("Transport Agent " + getAID().getName() + " is ready.");
        addBehaviour(new TransportBehaviour());
    }

    private class TransportBehaviour extends jade.core.behaviours.CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println(getAID().getName() + " received a delivery request to " + msg.getContent());

                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Delivered to " + msg.getContent());
                send(reply);
            } else {
                block();
            }
        }
    }
}
