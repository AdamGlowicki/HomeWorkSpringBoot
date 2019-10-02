package com.glowicki.homework3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> cars;

    public CarApi() {

        cars = Arrays.asList(
                new Car(1L, "BMW", "X2", "White"),
                new Car(2L, "Volvo", "cx40", "White"),
                new Car(3L, "Land Rover", "Evoque", "White")

        );

    }

    @GetMapping
    public ResponseEntity<List<Car>> getCar() {
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarByID(@PathVariable Long id) {
        Optional<Car> carById = findCarById(id);

        if (carById.isPresent()) {
            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{colour}")
    public ResponseEntity<Car> getCarsById(@PathVariable String colour) {
        Optional<Car> carsByColour = findCarsByColour(colour);

        if (carsByColour.isPresent()) {
            return new ResponseEntity<>(carsByColour.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        boolean add = cars.add(car);

        if (add) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Car> editCar(@RequestBody Car car) {
        Optional<Car> carById = findCarById(car.getId());

        if (carById.isPresent()) {
            cars.remove(carById.get());
            cars.add(car);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{colour}/conf/{id}")
    public ResponseEntity<Car> editColour(@PathVariable String colour,
                                          @PathVariable Long id) {
     return setParameterOfCar(colour, id);

    }

    @PutMapping("/{model}conf/{id}")
    public ResponseEntity<Car> editModel(@PathVariable String model,
                                         @PathVariable Long id) {
        return setParameterOfCar(model, id);
    }

    @PutMapping("/{mark}conf/{id}")
    public ResponseEntity<Car> editMark(@PathVariable String mark,
                                        @PathVariable Long id) {
        return setParameterOfCar(mark, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Car> deleteCar(@RequestBody Car car,
                                         @PathVariable Long id) {
        Optional<Car> carById = findCarById(id);

        if (carById.isPresent()) {
            cars.remove(carById.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Optional<Car> findCarById(Long id) {
        return cars.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    private Optional<Car> findCarsByColour(String colour) {
        return cars.stream()
                .filter(c -> c.getColor().equals(colour))
                .findFirst();
    }

    private ResponseEntity<Car> setParameterOfCar(String parameter, Long id) {
        Optional<Car> carById = findCarById(id);

        if (carById.isPresent()) {
            Car car = carById.get();
            car.setColor(parameter);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
