/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author prateek
 */
public class DBConnection {

    private static Connection conn;
    public static String movieId;
    public static String personId;
    public static String roomId;
    public static String actorId;
    public static String showingId;

    public static void createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl", "gmendonc", "lovehina");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Movie[] getMovies() {
        try {
            List<Movie> results;
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select movie_id, title from gmendonc.movie_tab");
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                stmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int addMovie(String title, String genre, Integer year, Integer length, String rate) throws ClassNotFoundException, SQLException {
        int result = 0;
        String id;
        try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.movie_tab values (?,?,?,?,?,?)")) {
            id = IdGenerator.randomID();
            pstmt.setString(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, genre);
            pstmt.setInt(4, year);
            pstmt.setInt(5, length);
            pstmt.setString(6, rate);
            result = pstmt.executeUpdate();
            pstmt.close();
        }
        if (result > 0) {
            movieId = id;
        }
        return result;
    }

    public static String[] getMovie(String movieID) {
        try {
            List<String> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select * from gmendonc.movie_tab where movie_id = ?")) {
                pstmt.setString(1, movieID);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(rs.getString(1));
                    results.add(rs.getString(2));
                    results.add(rs.getString(3));
                    results.add(rs.getString(4));
                    results.add(rs.getString(5));
                    results.add(rs.getString(6));
                }
                pstmt.close();
            }
            return results.toArray(new String[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int editMovie(String title, String genre, Integer year, Integer length, String rate) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.movie_tab set title = ?, genre = ?, year = ?, length = ?, pg_rated = ? where movie_id = ?")) {
                pstmt.setString(1, title);
                pstmt.setString(2, genre);
                pstmt.setInt(3, year);
                pstmt.setInt(4, length);
                pstmt.setString(5, rate);
                pstmt.setString(6, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static int deleteMovie() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("delete from gmendonc.movie_tab where movie_id = ?")) {
                pstmt.setString(1, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static Person[] getPersons() {
        try {
            List<Person> results;
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select person_id, name from gmendonc.person_tab");
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Person(rs.getString(1), rs.getString(2)));
                }
                stmt.close();
            }
            return results.toArray(new Person[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int addPerson(String name, Date date, Boolean sex) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.person_tab values (?,?,?,?)")) {
                pstmt.setString(1, IdGenerator.randomID());
                pstmt.setString(2, name);
                pstmt.setDate(3, date);
                pstmt.setBoolean(4, sex);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String[] getPerson(String personID) {
        try {
            List<String> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select * from gmendonc.person_tab where person_id = ?")) {
                pstmt.setString(1, personID);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(rs.getString(1));
                    results.add(rs.getString(2));
                    results.add(rs.getString(3));
                    results.add(rs.getString(4));
                }
                pstmt.close();
            }
            return results.toArray(new String[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int editPerson(String name, java.sql.Date date, Boolean sex) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.person_tab set name = ?, birth = ?, gender = ? where person_id = ?")) {
                pstmt.setString(1, name);
                pstmt.setDate(2, date);
                pstmt.setBoolean(3, sex);
                pstmt.setString(4, personId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static int deletePerson() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("delete from gmendonc.person_tab where person_id = ?")) {
                pstmt.setString(1, personId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static String getDirector() {
        try {
            String result = null;
            try (PreparedStatement pstmt = conn.prepareStatement("select person_id from gmendonc.director_tab where movie_id = ?")) {
                pstmt.setString(1, movieId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    result = rs.getString(1);
                }
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int addDirector() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.director_tab values (?,?)")) {
                pstmt.setString(1, personId);
                pstmt.setString(2, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int editDirector() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.director_tab set person_id = ? where movie_id = ?")) {
                pstmt.setString(1, personId);
                pstmt.setString(2, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String getWriter() {
        try {
            String result = null;
            try (PreparedStatement pstmt = conn.prepareStatement("select person_id from gmendonc.writer_tab where movie_id = ?")) {
                pstmt.setString(1, movieId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    result = rs.getString(1);
                }
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int addWriter() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.writer_tab values (?,?)")) {
                pstmt.setString(1, personId);
                pstmt.setString(2, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int editWriter() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.writer_tab set person_id = ? where movie_id = ?")) {
                pstmt.setString(1, personId);
                pstmt.setString(2, movieId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static Actor[] getActors() {
        try {
            List<Actor> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select actor_id, role, name, person_id from gmendonc.actor_tab natural join gmendonc.person_tab where movie_id = ?")) {
                pstmt.setString(1, movieId);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Actor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                pstmt.close();
            }
            return results.toArray(new Actor[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int addActor(String role){
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.actor_tab values (?,?,?,?)")) {
                pstmt.setString(1, IdGenerator.randomID());
                pstmt.setString(2, personId);
                pstmt.setString(3, movieId);
                pstmt.setString(4, role);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static int editActor(String role) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.actor_tab set role = ?, person_id = ?, movie_id = ? where actor_id = ?")) {
                pstmt.setString(1, role);
                pstmt.setString(2, personId);
                pstmt.setString(3, movieId);
                pstmt.setString(4, roomId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static int deleteActor() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("delete from gmendonc.actor_tab where actor_id = ?")) {
                pstmt.setString(1, actorId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    
    public static Movie[] searchForMovies(String title) {
        try {
            List<Movie> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select distinct movie_id, title from gmendonc.movie_tab where title = ?")) {
                pstmt.setString(1, title);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                pstmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Movie[] searchForMoviesGenre(String genre) {
        try {
            List<Movie> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select distinct movie_id, title from gmendonc.movie_tab where genre = ?")) {
                pstmt.setString(1, genre);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                pstmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Movie[] searchForMoviesWriter(String writer) {
        try {
            List<Movie> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select distinct movie_id, title from gmendonc.movie_tab natural join gmendonc.writer_tab natural join gmendonc.person_tab where name = ?")) {
                pstmt.setString(1, writer);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                pstmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Movie[] searchForMoviesDirector(String director) {
        try {
            List<Movie> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select distinct movie_id, title from gmendonc.movie_tab natural join gmendonc.director_tab natural join gmendonc.person_tab where name = ?")) {
                pstmt.setString(1, director);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                pstmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Movie[] searchForMoviesActor(String actor) {
        try {
            List<Movie> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select distinct movie_id, title from gmendonc.movie_tab natural join gmendonc.actor_tab natural join gmendonc.person_tab where name = ?")) {
                pstmt.setString(1, actor);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Movie(rs.getString(1), rs.getString(2)));
                }
                pstmt.close();
            }
            return results.toArray(new Movie[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int addRoom(int number, int capacity){
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.room_tab values (?,?,?)")) {
                pstmt.setString(1, IdGenerator.randomID());
                pstmt.setInt(2, capacity);
                pstmt.setInt(3, number);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static Room[] getRooms() {
        try {
            List<Room> results;
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select room_id, room_number, capacity from gmendonc.room_tab");
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Room(rs.getString(1), rs.getInt(2), rs.getInt(3)));
                }
                stmt.close();
            }
            return results.toArray(new Room[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int editRoom(int number, int capacity) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.room_tab set room_number = ?, capacity = ? where room_id = ?")) {
                pstmt.setInt(1, number);
                pstmt.setInt(2, capacity);
                pstmt.setString(3, roomId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    
    public static int deleteRoom() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("delete from gmendonc.room_tab where room_id = ?")) {
                pstmt.setString(1, roomId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    
    public static String[] getRoom(String roomID) {
        try {
            List<String> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select * from gmendonc.room_tab where room_id = ?")) {
                pstmt.setString(1, roomID);
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(rs.getString(1));
                    results.add(Integer.toString(rs.getInt(2)));
                    results.add(Integer.toString(rs.getInt(3)));
                }
                pstmt.close();
            }
            return results.toArray(new String[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int addShow(String showtime, float price){
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("insert into gmendonc.showing_tab values (?,?,?,?,?)")) {
                pstmt.setString(1, IdGenerator.randomID());
                pstmt.setString(2, movieId);
                pstmt.setString(3, roomId);
                pstmt.setString(4, showtime);
                pstmt.setFloat(5, price);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static int editShow(String showtime, float price) {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("update gmendonc.showing_tab set time = ?, room_id = ?, movie_id = ?, price = ? where showing_id = ?")) {
                pstmt.setString(1, showtime);
                pstmt.setString(2, roomId);
                pstmt.setString(3, movieId);
                pstmt.setFloat(4, price);
                pstmt.setString(5, showingId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    
    public static int deleteShow() {
        int result = 0;
        try {
            try (PreparedStatement pstmt = conn.prepareStatement("delete from gmendonc.showing_tab where showing_id = ?")) {
                pstmt.setString(1, showingId);
                result = pstmt.executeUpdate();
                pstmt.close();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }
    
    public static Showing[] getShows() {
        try {
            List<Showing> results;
            try (PreparedStatement pstmt = conn.prepareStatement("select * from gmendonc.showing_tab")) {
                ResultSet rs = pstmt.executeQuery();
                results = new ArrayList<>();
                while (rs.next()) {
                    results.add(new Showing(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5)));
                }
                pstmt.close();
            }
            return results.toArray(new Showing[results.size()]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
