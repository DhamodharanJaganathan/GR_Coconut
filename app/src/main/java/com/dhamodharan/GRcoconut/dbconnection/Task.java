package com.dhamodharan.GRcoconut.dbconnection;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "waste")
    private String waste;

    @ColumnInfo(name = "totalamount")
    private String totalamount;

    @ColumnInfo(name = "percoconut")
    private String percoconut;
    @ColumnInfo(name = "weight")
    private String weight;
    @ColumnInfo(name = "notes")
    private String notes;
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "coconutweight")
    private String coconutweight;

    @ColumnInfo(name = "coconutprice")
    private String coconutprice;

    public String getCoconutweight() {
        return coconutweight;
    }

    public void setCoconutweight(String coconutweight) {
        this.coconutweight = coconutweight;
    }

    public String getCoconutprice() {
        return coconutprice;
    }

    public void setCoconutprice(String coconutprice) {
        this.coconutprice = coconutprice;
    }

    public String getTotalcoconut() {
        return totalcoconut;
    }

    public void setTotalcoconut(String totalcoconut) {
        this.totalcoconut = totalcoconut;
    }

    @ColumnInfo(name = "totalcoconut")
    private String totalcoconut;


    /*
     * Getters and Setters
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getPercoconut() {
        return percoconut;
    }

    public void setPercoconut(String percoconut) {
        this.percoconut = percoconut;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}


