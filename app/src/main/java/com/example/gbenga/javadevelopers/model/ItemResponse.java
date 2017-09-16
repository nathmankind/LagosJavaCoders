package com.example.gbenga.javadevelopers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by GBENGA on 9/15/2017.
 */

public class ItemResponse {
    @SerializedName("Items")
    @Expose
    private List<Item> items;

    public List<Item> getItems(){
        return items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
}
