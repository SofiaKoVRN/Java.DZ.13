package Ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    // Тесты search
    // Совпадения по городам
    @Test
    public void searchNoMatchesFromOrTo() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Воронеж", 1_000, 1, 10);
        Ticket t2 = new Ticket("Липецк", "Белгород", 1_000, 1, 10);
        Ticket t3 = new Ticket("Тверь", "Орел", 1_000, 1, 10);
        Ticket t4 = new Ticket("Тамбов", "Иркутск", 1_000, 1, 10);
        Ticket t5 = new Ticket("Казань", "Томск", 1_000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {};
        Ticket[] actual = avia.search("Саратов", "Норильск");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void search50To50() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Воронеж", 1_000, 1, 10);
        Ticket t2 = new Ticket("Москва", "Белгород", 1_000, 1, 10);
        Ticket t3 = new Ticket("Москва", "Орел", 1_000, 1, 10);
        Ticket t4 = new Ticket("Тамбов", "Иркутск", 1_000, 1, 10);
        Ticket t5 = new Ticket("Казань", "Иркутск", 1_000, 1, 10);


        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);


        Ticket[] expected = {};
        Ticket[] actual = avia.search("Москва", "Иркутск");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOneCoincidence() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Воронеж", 1_000, 1, 10);
        Ticket t2 = new Ticket("Липецк", "Белгород", 1_000, 1, 10);
        Ticket t3 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t4 = new Ticket("Тамбов", "Иркутск", 1_000, 1, 10);
        Ticket t5 = new Ticket("Казань", "Томск", 1_000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t3};
        Ticket[] actual = avia.search("Москва", "Иркутск");
        Assertions.assertArrayEquals(expected, actual);
    }
    // Тесты search
    // Совпадения по городам и возрастанию цены
    @Test
    public void searchSomeMatchesCityAndPrice() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);
        Ticket t2 = new Ticket("Липецк", "Белгород", 1_000, 1, 10);
        Ticket t3 = new Ticket("Москва", "Иркутск", 15_000, 1, 10);
        Ticket t4 = new Ticket("Тамбов", "Омск", 1_000, 1, 10);
        Ticket t5 = new Ticket("Москва", "Иркутск", 19_000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t1, t3, t5};
        Ticket[] actual = avia.search("Москва", "Иркутск");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchAllMatchesAllPricesAreEqual() {
        AviaSouls avia = new AviaSouls();
        Ticket t1 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);
        Ticket t2 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);
        Ticket t3 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);
        Ticket t4 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);
        Ticket t5 = new Ticket("Москва", "Иркутск", 10_000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t1, t2, t3, t4, t5};
        Ticket[] actual = avia.search("Москва", "Иркутск");
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты searchAndSortBy
    @Test
    public void searchAndSortBySomeMatchesFromAndToDirectOrder() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Липецк", "Белгород", 1_000, 1, 2);
        Ticket t2 = new Ticket("Москва", "Иркутск", 1_000, 10, 20);
        Ticket t3 = new Ticket("Москва", "Иркутск", 1_000, 10, 21);
        Ticket t4 = new Ticket("Тамбов", "Омск", 1_000, 1, 2);
        Ticket t5 = new Ticket("Москва", "Иркутск", 1_000, 10, 22);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t2, t3, t5};
        Ticket[] actual = avia.searchAndSortBy("Москва", "Иркутск", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchAndSortByAllMatchesFromAndToAllFlyingTimesAreEqual() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);
        Ticket t2 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);
        Ticket t3 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);
        Ticket t4 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);
        Ticket t5 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t1, t2, t3, t4, t5};
        Ticket[] actual = avia.searchAndSortBy("Москва", "Иркутск", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchAndSortByAllMatchesFromAndToSomeFlyingTimesAreEqual() {
        AviaSouls avia = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 20);
        Ticket t2 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t3 = new Ticket("Москва", "Иркутск", 1_000, 1, 2);
        Ticket t4 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t5 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);

        avia.add(t1);
        avia.add(t2);
        avia.add(t3);
        avia.add(t4);
        avia.add(t5);

        Ticket[] expected = {t3, t2, t4, t5, t1};
        Ticket[] actual = avia.searchAndSortBy("Москва", "Иркутск", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    // Тесты compareTo
    @Test
    public void thisPriceIsLessThanThat() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t2 = new Ticket("Тамбов", "Омск", 2_000, 1, 10);

        int expected = -1;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void thisPriceIsEqualThat() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 2_000, 1, 10);
        Ticket t2 = new Ticket("Тамбов", "Омск", 2_000, 1, 10);

        int expected = 0;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void thisPriceIsMoreThanThat() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 2_000, 1, 10);
        Ticket t2 = new Ticket("Тамбов", "Омск", 1_000, 1, 10);

        int expected = 1;
        int actual = t1.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    // Тесты компейр
    @Test
    public void FlyingTimeOneIsLessThanFlyingTimeTwo() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 2_000, 1, 10);
        Ticket t2 = new Ticket("Тамбов", "Омск", 1_000, 1, 18);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void FlyingTimeTwoIsLessThanFlyingTimeOne() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 2_000, 1, 18);
        Ticket t2 = new Ticket("Тамбов", "Омск", 1_000, 1, 8);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void FlyingTimeEqual() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 2_000, 1, 8);
        Ticket t2 = new Ticket("Тамбов", "Омск", 1_000, 1, 8);
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(t1, t2);
        Assertions.assertEquals(expected, actual);
    }
    // Тесты equals
    @Test
    public void objectIsEqualItself() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);

        boolean expected = true;
        boolean actual = t1.equals(t1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ObjectsEquality() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 18);
        Ticket t2 = new Ticket("Москва", "Иркутск", 1_000, 1, 18);

        boolean expected = true;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void objectIsEqualNull() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t2 = null;

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void equalsNotEqualGetClasses() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        int t2 = 1_000;

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void notEqualObjectsFrom() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t2 = new Ticket("Тамбов", "Омск", 1_000, 1, 10);

        boolean expected = false;
        boolean actual = t1.equals(t2);
        Assertions.assertEquals(expected, actual);
    }
    // Тесты HashCodes
    @Test
    public void equalityOfHashCodesOfTwoEqualityObjects() {
        Ticket t1 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);
        Ticket t2 = new Ticket("Москва", "Иркутск", 1_000, 1, 10);

        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }
}
