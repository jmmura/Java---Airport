package be.technifutur.java.airport.controller;

import be.technifutur.java.airport.model.dto.FlightDTO;
import be.technifutur.java.airport.model.form.FlightInsertForm;
import be.technifutur.java.airport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid FlightInsertForm form) {
        flightService.createFlight(form);
    }

//    @GetMapping("/all")
//    public List<FlightDTO> getAll() {
//        return flightService.getAll();
//    }
//

    @DeleteMapping({"/{id:[0-9]+}", "/{id:[0-9]+}/delete"})
    public void delete(@PathVariable long id){
        flightService.delete(id);
    }

    /*
    * @DeleteMapping({"/{id:[0-9]+}", "/{id:[0-9]+}/delete"})
    public void delete(@PathVariable long id){
        planeService.delete(id);
    }
    * */


}
