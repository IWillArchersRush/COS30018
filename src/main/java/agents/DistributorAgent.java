package agents;
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import Routing.*;

import java.util.*;

public class DistributorAgent extends Agent {
    private Graph deliveryMap;
    private DijkstraAlgorithm dijkstra;
    private List<AID> transportAgents = new ArrayList<>();

    protected void setup() {
        System.out.println("Distributor Agent " + getAID().getName() + " is ready.");
        deliveryMap = new Graph();

        // Tạo bản đồ tuyến đường
        deliveryMap.addNode("Warehouse");
        deliveryMap.addNode("PointA");
        deliveryMap.addNode("PointB");
        deliveryMap.addEdge("Warehouse", "PointA", 5);
        deliveryMap.addEdge("PointA", "PointB", 3);
        deliveryMap.addEdge("Warehouse", "PointB", 10);

        dijkstra = new DijkstraAlgorithm(deliveryMap);

        // Nhận yêu cầu từ agent nhận hàng
        addBehaviour(new ReceiveOrderBehaviour());
    }

    private class ReceiveOrderBehaviour extends jade.core.behaviours.CyclicBehaviour {
        public void action() {
            ACLMessage msg = receive();
            if (msg != null) {
                System.out.println("Received order from " + msg.getSender().getName() + ": " + msg.getContent());

                String destination = msg.getContent();
                AID bestTransport = selectBestTransportAgent(destination);

                if (bestTransport != null) {
                    ACLMessage transportMsg = new ACLMessage(ACLMessage.REQUEST);
                    transportMsg.addReceiver(bestTransport);
                    transportMsg.setContent(destination);
                    send(transportMsg);
                }
            } else {
                block();
            }
        }
    }

    private AID selectBestTransportAgent(String destination) {
        return transportAgents.isEmpty() ? null : transportAgents.get(0);  // Giả định chọn đại diện đầu tiên
    }
}
