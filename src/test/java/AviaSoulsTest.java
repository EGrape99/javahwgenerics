import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    private Ticket ticket1 = new Ticket("KLD", "SPB", 2000, 9, 10 );
    private Ticket ticket2 = new Ticket("KLD", "SPB", 8000, 11, 15);
    private Ticket ticket3 = new Ticket("KLD", "MSK", 3000, 20, 23);
    private Ticket ticket4 = new Ticket("KLD", "SPB", 5000, 17, 19);
    private Ticket ticket5 = new Ticket("KLD", "SPB", 3000, 16, 18);

    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void shouldAdd() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    public void shouldFindTicket() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = manager.search("KLD", "MSK");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicket() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("MSK", "SPB");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfPriceHigher() {
        int expected = 1;
        int actual = ticket4.compareTo(ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfPriceLower() {
        int expected = -1;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareIfPriceSame() {
        int expected = 0;
        int actual = ticket3.compareTo(ticket5);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByPrice() {
        Ticket[] expected = {ticket1, ticket5, ticket4, ticket2};
        Ticket[] actual = manager.search("KLD", "SPB");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortByTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket1, ticket4, ticket5, ticket2};
        Ticket[] actual = manager.searchAndSortBy("KLD", "SPB", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
