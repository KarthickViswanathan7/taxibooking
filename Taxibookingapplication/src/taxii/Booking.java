package taxii;

public class Booking {
    private String pickupPoint;
    private String dropPoint;
    private int time;
    private Taxi taxi;
    private int fare;

    public Booking(String pickupPoint, String dropPoint, int time, Taxi taxi, int fare) {
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.time = time;
        this.taxi = taxi;
        this.fare = fare;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public int getTime() {
        return time;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public int getFare() {
        return fare;
    }
}

