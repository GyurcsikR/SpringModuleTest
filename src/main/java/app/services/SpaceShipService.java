package app.services;

import app.helpers.DataLoader;
import app.helpers.ShipData;
import app.models.SpaceShip;
import app.repositories.SpaceShipRepo;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceShipService {

    private SpaceShipRepo spaceShipRepo;
    private DataLoader dataLoader;

    public SpaceShipService(SpaceShipRepo spaceShipRepo, DataLoader dataLoader) {
        this.spaceShipRepo = spaceShipRepo;
        this.dataLoader = dataLoader;
    }

    public List<SpaceShip> getAllSpaceShip(){
        return (List<SpaceShip>) spaceShipRepo.findAll();
    }

    public SpaceShip saveNewShip(SpaceShip spaceShip){
        return spaceShipRepo.save(spaceShip);
    }
}
