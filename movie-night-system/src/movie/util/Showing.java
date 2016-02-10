/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.util;

/**
 *
 * @author prateek
 */
public class Showing {
    private String showingId;
    private String roomId;
    private String movieId;
    private String time;
    private Float price;
    
    public Showing(String showingId, String movieId, String roomId, String time, Float price){
        this.showingId = showingId;
        this.roomId = roomId;
        this.movieId = movieId;
        this.time = time;
        this.price = price;
    }
    
    public String getId(){
        return this.showingId;
    }
    
    public String getRoomId(){
        return this.roomId;
    }
    
    public String getMovieId(){
        return this.movieId;
    }
    
    public String getTime(){
        return this.time;
    }
    
    public Float getPrice(){
        return this.price;
    }
    
    public String toString(){
        return DBConnection.getMovie(movieId)[1] + " - Room " + DBConnection.getRoom(roomId)[2] + " - " + time + " - " + price;
    }
}
