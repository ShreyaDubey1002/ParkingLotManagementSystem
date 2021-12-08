package ParkingLotManagementSystem.models;

import java.util.HashMap;
import java.util.Iterator;

public class ParkingTicket {
    ParkingTicketStatus parkingTicketStatus;
    public String parkingTicketId;
    static int lastParkingTicketId;
    long issuedAt;
    long paidAt;
    double price;
    ParkingRate parkingRate;

    ParkingTicket(long issuedAt, ParkingRate parkingRate) {
        this.issuedAt = issuedAt;
        lastParkingTicketId = lastParkingTicketId+1;
        parkingTicketId=String.valueOf(lastParkingTicketId);
        parkingTicketStatus = ParkingTicketStatus.ACTIVE;
        this.parkingRate = parkingRate;
    }

    double scanTicket(long paidAt) {
        double total = 0.0;
        this.paidAt = paidAt;
        long hours = paidAt-issuedAt;
        HashMap<Long, Double> mp = parkingRate.hourlyRate;
        Iterator<Long> valSet = mp.keySet().iterator();
        while (hours > 0 && valSet.hasNext()) {
            long val = valSet.next();
            while (val < hours) {
                total += mp.get(val);
                hours--;
                if (valSet.hasNext()) {
                    val = valSet.next();
                } else {
                    break;
                }
            }
            total+=hours * mp.get(val);
        }
        this.price = total;
        return this.price;
    }

    void processPayment(long paidAt) {
        if (this.parkingTicketStatus == ParkingTicketStatus.PAID)
            return;
        double total = scanTicket(paidAt);
        System.out.println("Paid total : " + total);
        this.paidAt = paidAt;
        this.parkingTicketStatus = ParkingTicketStatus.PAID;
    }
}
