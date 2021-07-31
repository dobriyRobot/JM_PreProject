package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarService {
    private List<Car> listCars;

    {
        listCars = new ArrayList<>();
        listCars.add(new Car("SUZUKI", "GRAND VITARA", 2010));
        listCars.add(new Car("VOLVO", "XC90", 2020));
        listCars.add(new Car("BMW", "X5", 1990));
        listCars.add(new Car("MERCEDES", "C180", 1999));
        listCars.add(new Car("OPEL", "VECTRA OPC", 2011));
    }

    public List<Car> showCars(Integer count) {
        if(count == null || count == 0 || count >= 5) {
            return listCars;
        }
        return listCars.stream().limit(count).collect(Collectors.toList());
    }
}
