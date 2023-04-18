package be.technifutur.java.airport.model.dto;
import lombok.*;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String method;
    private String path;
    private String message;
    private int status;
}