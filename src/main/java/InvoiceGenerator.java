public class InvoiceGenerator {
    private static final int MINIMUM_COST_PER_KM = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FARE = 5.0;
    private final RideRepository rideRepository;

    public InvoiceGenerator() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalFare = (distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME);
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalfare = 0;
        for (Ride ride : rides) {
            totalfare += ride.cabRide.calcCostOfCabRide(ride);
        }
        return new InvoiceSummary(rides.length, totalfare);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }
}
