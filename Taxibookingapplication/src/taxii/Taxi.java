package taxii;

public class Taxi {
	    private int id;
	    private String location;
	    private String destination;
	    private int departureTime=0;
	    private boolean available;

	    public Taxi(int id, String location) {
	        this.id = id;
	        this.location = location;
	        this.available = true;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public int getDepartureTime() {
			return departureTime;
		}

		public void setDepartureTime(int departureTime) {
			this.departureTime = departureTime;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
	    
}
