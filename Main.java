package com.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.Contollers.BookingController;
import com.example.Contollers.TheatherContoller;
import com.example.Models.Booking;
import com.example.Models.City;
import com.example.Models.Movie;
import com.example.Models.Screen;
import com.example.Models.Show;
import com.example.Models.Theather;
import com.example.Models.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        City blr= new City("BLR",1);
        City rjy= new City("RJY",2);

        Screen screen1= new Screen(1,100);
        Screen screen2 = new Screen(2,150);

        Theather th1= new Theather(rjy, "Swami",new ArrayList<>(List.of(screen1, screen2)));
        Theather th2= new Theather(rjy, "Ashoka",new ArrayList<>(List.of(screen1)));
        Theather th3= new Theather(blr, "PVR",new ArrayList<>(List.of(screen1,screen2)));

        Movie movie1= new Movie("Once More",200000);
        Movie movie2=new Movie("Jailer", 20000);

        Show show1 = new Show(movie1,LocalDate.parse("2026-10-24"), LocalTime.parse("14:30"), screen1);
        Show show2 = new Show(movie2,LocalDate.parse("2026-10-24"), LocalTime.parse("14:30"), screen2);
        Show show3 = new Show(movie2,LocalDate.parse("2026-10-24"), LocalTime.parse("18:30"), screen2);

        screen1.addShow(show1.getDate(), show1);
        screen2.addShow(show2.getDate(), show2);
        screen2.addShow(show3.getDate(), show3);


        TheatherContoller theatherContoller= new TheatherContoller();
        theatherContoller.addTheather(th1);
        theatherContoller.addTheather(th2);
        theatherContoller.addTheather(th3);

        

        Set<Movie> movies = theatherContoller.getMovies( rjy,  LocalDate.parse("2026-10-24"));

        if (movies.isEmpty()) {
            System.out.println("No MOvies found in " + rjy.getCityName());
        } else {
            System.out.println("Movies showing in " + rjy.getCityName() + ":");
            for (Movie movie : movies) {
                System.out.printf("Name: %s",
                        movie.getMovieName());
                        System.out.println();
            }
        }

        List<Theather> theathers = theatherContoller.getTheatres(rjy, movie2, LocalDate.parse("2026-10-24"));

        if (theathers.isEmpty()) {
            System.out.println("No theaters found for " + movie2.getMovieName() + " in " + rjy.getCityName());
        } else {
            System.out.println("Theaters showing " + movie2.getMovieName() + " in " + rjy.getCityName() + ":");
            for (Theather theather : theathers) {
                System.out.printf("ID: %d, Name: %s, City: %s%n",
                        theather.getTheatherId(), theather.getTheatherName(), theather.getCity().getCityName());
            }
        }

        List<Show> shows= theatherContoller.getShows( movie2,  LocalDate.parse("2026-10-24"),  th1);

        if (shows.isEmpty()) {
            System.out.println("No shows found for " + movie2.getMovieName() + " in " + th1.getTheatherName());
        } else {
            System.out.println("Shows showing " + movie2.getMovieName() + " in " + th1.getTheatherName() + ":");
            for (Show show : shows) {
                System.out.println("Show time: "+show.getStartTime());
            }
        }

        BookingController bookingController= new BookingController();
        
        User user1= new User("0", "Satish");
        User user2= new User("1", "Ramya");
        Booking booking1=bookingController.createBooking(user1, show3, new ArrayList<>(List.of(1,2,3)));
        try{
            Booking booking2=bookingController.createBooking(user2, show3, new ArrayList<>(List.of(3)));
        }catch(Exception e){
            System.out.println(e.getMessage()+".....Unable to proceed to for booking !!!!");
        }

        // Booking booking2=bookingController.createBooking(user1, show3, new ArrayList<>(List.of(3)));

        System.out.printf(
                "Booking details:%nBooking ID: %s%nUser: %s (ID: %s)%nMovie: %s%nShow: %s at %s%nSeats: %s%nPayment: %s (ID: %s)%n",
                booking1.getBookingId(),
                booking1.getUser().getName(),
                booking1.getUser().getUserId(),
                booking1.getShow().getMovie().getMovieName(),
                booking1.getShow().getDate(),
                booking1.getShow().getStartTime(),
                booking1.getSeats(),
                booking1.getPayment().getStatus(),
                booking1.getPayment().getPaymentId());
        

    }

}    