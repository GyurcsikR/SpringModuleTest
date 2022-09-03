package app.repositories;

import app.models.Crew;
import app.models.SpaceShip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceShipRepo extends CrudRepository<SpaceShip, Long> {

    List<SpaceShip> findSpaceShipByName(String name);

    @Query("select s from SpaceShip s where s.isActive = true")
    List<SpaceShip> findSpaceShipByActiveIsTrue();
}
