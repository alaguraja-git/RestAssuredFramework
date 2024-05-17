package model;

import lombok.Builder;
import lombok.Data;

@Data
public class Posts {

    private int id;
    private String title;
    private String views;
}
