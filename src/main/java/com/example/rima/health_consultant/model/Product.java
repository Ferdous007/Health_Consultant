package com.example.rima.health_consultant.model;

/**
 * Created by SHARMI on 28/12/2015.
 */
public class Product {
    private int id;
    private String physicalproblem;



    public Product(int id, String physicalproblem) {
        this.id = id;
        this.physicalproblem = physicalproblem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhysicalproblem() {
        return physicalproblem;
    }

    public void setPhysicalproblem(String physicalproblem) {
        this.physicalproblem = physicalproblem;
    }

}

