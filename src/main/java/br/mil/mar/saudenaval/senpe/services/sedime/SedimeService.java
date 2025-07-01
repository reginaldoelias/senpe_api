package br.mil.mar.saudenaval.senpe.services.sedime;

import br.mil.mar.saudenaval.senpe.entities.Sedime;
import br.mil.mar.saudenaval.senpe.repositories.SedimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedimeService {

    @Autowired
    private SedimeRepository repository;

    public List<Sedime> getData(){
        return repository.findAll();
    }
}
