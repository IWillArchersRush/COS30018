package agents;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class ReceiverAgent extends Agent {
    protected void setup() {
        System.out.println("Receiver Agent " + getAID().getName() + " is ready.");

        ACLMessage orderMsg = new ACLMessage(ACLMessage.REQUEST);
        orderMsg.addReceiver(new jade.core.AID("Distributor", AID.ISLOCALNAME));
        orderMsg.setContent("PointB");
        send(orderMsg);

        addBehaviour(new ReceiveConfirmationBehaviour());
    }

    private class ReceiveConfirmationBehaviour extends jade.core.behaviours.CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null && msg.getPerformative() == ACLMessage.INFORM) {
                System.out.println(getAID().getName() + " received confirmation: " + msg.getContent());
            } else {
                block();
            }
        }
    }
}
