package mk.ukim.finki.hardwareshop.service;

import mk.ukim.finki.hardwareshop.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    Optional<Manufacturer> findById(Long id);

    List<Manufacturer> findAll();

    Manufacturer findByName(String name);

    Optional<Manufacturer> save(String name, String address);

    Manufacturer save(Manufacturer manufacturer);

    void deleteById(Long id);
}


