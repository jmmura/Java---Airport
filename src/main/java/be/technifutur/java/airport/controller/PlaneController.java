package be.technifutur.java.airport.controller;

import be.technifutur.java.airport.config.SecurityConfig;
import be.technifutur.java.airport.model.dto.PlaneDTO;
import be.technifutur.java.airport.model.form.PlaneInsertForm;
import be.technifutur.java.airport.service.PlaneService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    //@PreAuthorize("isAuthenticated")
    @PostMapping("/add")
    public void create(@RequestBody @Valid PlaneInsertForm form) {
        planeService.create(form);
    }


    @GetMapping("/{id:[0-9]+}")
    public PlaneDTO getOne(@PathVariable long id) {
        return planeService.getOne(id);
    }

    @GetMapping("/all")
    public List<PlaneDTO> getAll() {
        return planeService.getAll();
    }


    @PatchMapping("/maintenance/{id:[0-9]+}")
    public List<PlaneDTO> maintenance(@PathVariable long id){
        planeService.updateInMaintenance(id);
        return planeService.getAll();
    }

    @PatchMapping("/update-maintenance/{id:[0-9]+}")
    public void updateMaintenance(@PathVariable Long id, @RequestParam Boolean inMaintenance) {
        planeService.updateMaintenance(id, inMaintenance);
        // et dans postman -> { "maintenance" : true/false }
    }

//    @PatchMapping("/update-company")
//    public void updateCompany(@RequestBody Map<Long, Long> map) {
//        map.forEach((key, value) -> planeService.updateCompany(key, value));
//    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          sexe

    // /plane/9 OU
    // /plane/9/delete
    @DeleteMapping({"/{id:[0-9]+}", "/{id:[0-9]+}/delete"})
    public void delete(@PathVariable long id){
        planeService.delete(id);
    }



}