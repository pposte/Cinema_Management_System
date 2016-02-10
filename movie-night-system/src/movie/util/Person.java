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
public class Person {
    private final String id;
    private final String name;
    
    public Person(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
