package model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Race {
    private int ID;
    private String Name;
    private String Country;
    private LocalDate Date;
    private int Guests;
}
