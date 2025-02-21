package main;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import jade.core.Runtime;

public class MainLauncher {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();

        // **Tạo Main Container**
        Profile mainProfile = new ProfileImpl();
        mainProfile.setParameter(Profile.GUI, "true");
        AgentContainer mainContainer = rt.createMainContainer(mainProfile);  // **Sửa ở đây**

        try {
            // **Tạo và khởi động các agent**
            AgentController distributor = mainContainer.createNewAgent("Distributor", "agents.DistributorAgent", null);
            AgentController transport = mainContainer.createNewAgent("Transport1", "agents.TransportAgent", null);
            AgentController receiver = mainContainer.createNewAgent("Receiver1", "agents.ReceiverAgent", null);

            distributor.start();
            transport.start();
            receiver.start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
