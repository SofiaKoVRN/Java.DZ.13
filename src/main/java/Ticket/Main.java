package Ticket;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ticket ticket1 = new Ticket(
                "DMD.Moscow",
                "VOZ.Voronezh",
                7_500,
                19_00,
                23_00
        );
        Ticket ticket2 = new Ticket(
                "DMD.Moscow",
                "VOZ.Voronezh",
                10_500,
                15_00,
                17_00
        );
        Ticket ticket3 = new Ticket(
                "DMD.Moscow",
                "VOZ.Voronezh",
                4_000,
                6_00,
                7_00
        );

        //System.out.println(ticket1.compareTo(ticket2));

//        Ticket[] tickets = {ticket1, ticket2, ticket3};
//        Arrays.sort(tickets);

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] tickets = {ticket1, ticket2};
        Arrays.sort(tickets, timeComparator);

    }


}