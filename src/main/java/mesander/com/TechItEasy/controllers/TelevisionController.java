package mesander.com.TechItEasy.controllers;

import mesander.com.TechItEasy.models.Television;
import mesander.com.TechItEasy.exceptions.RecordNotFoundException;
import mesander.com.TechItEasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {
    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {
        List<Television> televisions;
        televisions = televisionRepository.findAll();
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getSpecificTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television foundTelevision = television.get();
            return ResponseEntity.ok().body(foundTelevision);
        }

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Television> deleteSpecificTelevision(@PathVariable("id") Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable("id") Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television foundTelevision = television.get();
            foundTelevision.setAmbiLight(newTelevision.getAmbiLight());
            foundTelevision.setAvailableSize(newTelevision.getAvailableSize());
            foundTelevision.setBluetooth(newTelevision.getBluetooth());
            foundTelevision.setBrand(newTelevision.getBrand());
            foundTelevision.setHdr(newTelevision.getHdr());
            foundTelevision.setName(newTelevision.getName());
            foundTelevision.setOriginalStock(newTelevision.getOriginalStock());
            foundTelevision.setPrice(newTelevision.getPrice());
            foundTelevision.setRefreshRate(newTelevision.getRefreshRate());
            foundTelevision.setScreenQuality(newTelevision.getScreenQuality());
            foundTelevision.setScreenType(newTelevision.getScreenType());
            foundTelevision.setSmartTv(newTelevision.getSmartTv());
            foundTelevision.setSold(newTelevision.getSold());
            foundTelevision.setType(newTelevision.getType());
            foundTelevision.setVoiceControl(newTelevision.getVoiceControl());
            foundTelevision.setWifi(newTelevision.getWifi());

            Television updatedTelevision = televisionRepository.save(foundTelevision);
            return ResponseEntity.ok().body(updatedTelevision);
        }
    }

}
