package ParkingLotManagementSystem.models;

import java.util.HashMap;

public class ExitPanel {
    public String exitPanelName;
    static int lastExitPanelId = 0;
    HashMap<String, ParkingTicket> tickets;

    public ExitPanel(HashMap<String, ParkingTicket> tickets) {
        this.lastExitPanelId = this.lastExitPanelId + 1;
        this.exitPanelName = String.valueOf(this.lastExitPanelId);
        this.tickets = tickets;
    }
    public void processPayment(ParkingTicket parkingTicket, long paidAt) {
        parkingTicket.processPayment(paidAt);
        tickets.remove(parkingTicket);
    }
    public double scanPayment(ParkingTicket parkingTicket, long paidAt) {
        return parkingTicket.scanTicket(paidAt);
    }
}
