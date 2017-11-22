package model.utils;

public enum Gender {  
	MALE("Male",1), 
    FEMALE("Female", 2);
	private String descripcion; 
    private int id; 

    private Gender(String name, int color) { 
    	this.descripcion = name; 
    	this.id = color; 
    }
    public String getDescripcion() { 
        return descripcion; 
    } 
 
    public int getId() { 
        return id; 
    } 
} 