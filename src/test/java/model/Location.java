package model;

import java.util.List;

import lombok.Data;

@Data
public class Location {

    private int id;
    private String city;
    private String country;
    private List<Address> address;
}
