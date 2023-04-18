package be.technifutur.java.airport.model.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DemoForm {

    @NotNull
    private String name;

    @PositiveOrZero
    private int age;
}
