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
public class Actor {
    private String id;
    private String name;
    private String role;
    private String person;
    
    public Actor(String id, String role, String name, String person){
        this.id = id;
        this.role = role;
        this.name = name;
        this.person = person;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getRole(){
        return this.role;
    }
    
    public String getPerson(){
        return this.person;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
