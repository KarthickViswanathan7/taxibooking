package taxii;
import java.util.*;
public class TaxiBookingApp {
    static int taxicount = 4;
    static int numofpoints = 6;
    static int pointDistance = 15;
    static int timetaken = 60;
    static int basefare = 100;
    static int additionalfare = 10;
    static List<Taxi> taxis = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    

    public static void main(String[] args) throws Exception {
        try {
            initializeTaxis();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1.Book\n2.View bookings\n3.exit");
                int select = Integer.parseInt(sc.next());
                switch (select) {
                    case 2:
                        viewBookings();
                        break;
                    case 3:
                        break;
                    default:
                        if (select != 1) {
                            System.out.println("Wrong input");
                            break;
                        }
                }

                System.out.println("Enter booking details:");
                System.out.println("Pickup point:");
                String pickupPoint = sc.next().toUpperCase();
                System.out.println("Drop point:");
                String dropPoint = sc.next().toUpperCase();
                System.out.println("Time:");
                int time = Integer.parseInt(sc.next());

                Taxi taxi = allocateTaxi(pickupPoint, dropPoint, time);
                if (taxi != null) {
                    int fare = calculateFare(pickupPoint, dropPoint);
                    bookings.add(new Booking(pickupPoint, dropPoint, time, taxi, fare));
                    System.out.println("Taxi booked: taxi-" + taxi.getId());
                    System.out.println("Fare: " + fare);
                } else {
                    System.out.println("Booking rejected: No taxi available");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("WRONG INPUT:" + e.getLocalizedMessage());
        }
    }

    private static void initializeTaxis() {
        for (int i = 0; i < taxicount; i++) {
            taxis.add(new Taxi(i + 1, "A"));
        }
    }
    private static Taxi allocateTaxi(String pickupPoint, String dropPoint, int time) {
        Taxi taxi = null;
        for (Taxi t : taxis) {
            if (t.isAvailable() == false) {
                if (t.getDepartureTime() <= time) {
                    t.setAvailable(true);
                }
            }
            if (t.getLocation().equals(pickupPoint) && t.isAvailable()) {
                taxi = t;
                break;
            }
        }
        if (taxi == null) {
            int minDistance = Integer.MAX_VALUE;
            for (Taxi t : taxis) {
                if (t.isAvailable()) {
                    int distance = calculateDistance(t.getLocation(), pickupPoint);
                    if (distance < minDistance) {
                        minDistance = distance;
                        taxi = t;
                    }
                }
            }
        }

        if (taxi == null) {
            return null;
        }
        taxi.setAvailable(false);
        taxi.setLocation(dropPoint);
        taxi.setDepartureTime(time);
        return taxi;
    }

    private static int calculateDistance(String point1, String point2) {
        char p1 = point1.charAt(0);
        char p2 = point2.charAt(0);
        int index1 = (int) p1;
        int index2 = (int) p2;
        return Math.abs(index1 - index2) * pointDistance;
    }

    private static int calculateFare(String pickupPoint, String dropPoint) {
        int distance = calculateDistance(pickupPoint, dropPoint);
        if (distance <= 5) {
            return basefare;
        } else {
            return basefare + (distance - 5) * additionalfare;
        }
    }

    private static void viewBookings() {
        System.out.println("List of Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Pickup point: " + booking.getPickupPoint());
            System.out.println("Drop point: " + booking.getDropPoint());
            System.out.println("Time: " + booking.getTime());
            System.out.println("Taxi: " + booking.getTaxi().getId());
            System.out.println("Fare: " + booking.getFare());
            System.out.println();
        }
    }
    
}
