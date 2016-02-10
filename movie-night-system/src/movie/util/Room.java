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
public class Room {
    private final String id;
    private final int number;
    private final int capacity;
    
    public Room(String id, int number, int capacity){
        this.id = id;
        this.number = number;
        this.capacity = capacity;
    }
    
    public String getId(){
        return this.id;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public int getCapacity(){
        return this.capacity;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.number);
    }
}
