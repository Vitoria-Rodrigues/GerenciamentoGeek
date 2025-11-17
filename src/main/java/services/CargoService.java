
package services;

import classes.Cargo;
import classesDAO.CargoDAO;
import java.util.List;


public class CargoService {

    private final CargoDAO cargoDAO;

    public CargoService() {
        this.cargoDAO = new CargoDAO();
    }

    public List<Cargo> listarCargos() {
        return cargoDAO.pegarCargos();
    }
}
