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

}
