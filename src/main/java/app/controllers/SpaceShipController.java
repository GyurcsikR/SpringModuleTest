package app.controllers;

import app.models.SpaceShip;
import app.models.SpaceShipClass;
import app.repositories.SpaceShipRepo;
import app.services.SpaceShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SpaceShipController {

    private SpaceShipService spaceShipService;

    private SpaceShipRepo spaceShipRepo;

    public SpaceShipController(SpaceShipService spaceShipService, SpaceShipRepo spaceShipRepo) {
        this.spaceShipService = spaceShipService;
        this.spaceShipRepo = spaceShipRepo;
    }

    @GetMapping(value = {"/spaceship"})
    public String saveNewSpaceShip(Model model) {
        model.addAttribute("newShip", new SpaceShip());
        model.addAttribute("shipClasses", SpaceShipClass.values());

        return "spaceship_new";
    }

    @PostMapping(value = {"/spaceship"})
    public String saveShip(SpaceShip spaceShip){
        spaceShipService.saveNewShip(spaceShip);

        return "redirect:/spaceships";
    }

    @GetMapping(value = {"/spaceships"})
    public String getSpaceShips(Model model){
        List<SpaceShip> spaceShips = spaceShipService.getAllSpaceShip();
        model.addAttribute("spaceShips", spaceShips);

        return "spaceships";
    }

    @GetMapping(value = "/spaceships/active")
    public String getActiveShips(Model model){
        List<SpaceShip> activeShips = spaceShipRepo.findSpaceShipByActiveIsTrue();
        model.addAttribute("spaceShips", activeShips);

        return "spaceships";
    }

    @GetMapping(value = "/crew/{name}")
    public String findCrewByShips(@PathVariable String name, Model model){
        List<SpaceShip> spaceships = spaceShipRepo.findSpaceShipByName(name);
        model.addAttribute("spaceship", spaceships);

        return "crew";
    }
}
