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
public class Movie {
    private final String id;
    private final String title;
    
    public Movie(String id, String title){
        this.id = id;
        this.title = title;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    @Override
    public String toString(){
        return this.title;
    }
}
