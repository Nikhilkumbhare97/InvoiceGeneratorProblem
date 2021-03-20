import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator;

    @BeforeEach
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        double diff = Math.abs(25 - fare);
        Assertions.assertEquals(25.0, fare, diff);
    }

    @Test
    public void givenLessDistaneOrTime_ShouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        double diff = Math.abs(5 - fare);
        Assertions.assertEquals(5, fare, diff);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5, CabRide.NORMAL),
                new Ride(0.1, 1, CabRide.NORMAL)
        };
        InvoiceSummary actualInvoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "Nikhil";
        Ride[] rides = {
                new Ride(2.0, 5, CabRide.NORMAL),
                new Ride(0.1, 1, CabRide.NORMAL),
                new Ride(1, 2, CabRide.NORMAL)
        };
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary actualInvoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 42.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
    }

    @Test
    public void givenUserIdAndRidesForTwoCategoriesRides_ShouldReturnInvoiceSummary() {
        String userId = "Nikhil";
        Ride[] rides = {
                new Ride(2.0, 5, CabRide.NORMAL),
                new Ride(0.1, 1, CabRide.PREMIUM),
                new Ride(2, 2, CabRide.PREMIUM)
        };
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 79);
        Assertions.assertEquals(expectedInvoiceSummary, summary);
    }

}
