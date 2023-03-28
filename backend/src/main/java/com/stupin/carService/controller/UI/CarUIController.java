package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.Car;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.CarService;
import com.stupin.carService.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("ui/users/{id}/")
public class CarUIController {
    private final CarService carService;
    private final UserService userService;

    @Autowired
    public CarUIController(CarService carService,
                           UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("garage")
    public String viewUserGaragePage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("cars", carService.getByUserId(id));
        model.addAttribute("user", userService.getById(id));
        return "user/user_garage_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("add")
    public String addCarToGarage(@PathVariable Integer id,
                                 @ModelAttribute Car car,
                                 @RequestParam String brand,
                                 @RequestParam String model,
                                 @RequestParam int odometer,
                                 @RequestParam int productionYear) {
        User user = userService.getById(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setOdometer(odometer);
        car.setProductionYear(productionYear);
        car.setUser(user);
        carService.save(car);
        return "redirect:garage";

    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{carId}/edit")
    public String getEditCarPage(@PathVariable String carId, Model model) {
        model.addAttribute("userCar", carService.getByVinCode(carId));
        return "user/car_edit_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("{carId}/edit")
    public RedirectView manageUser(@PathVariable String carId,
                                   @RequestParam String brand,
                                   @RequestParam String model,
                                   @RequestParam int odometer,
                                   @RequestParam int productionYear) {
        Car car = carService.getByVinCode(carId);
        User user = car.getUser();
        car.setOdometer(odometer);
        car.setBrand(brand);
        car.setModel(model);
        car.setProductionYear(productionYear);
        carService.save(car);
        return new RedirectView("/ui/users/{id}/garage");
    }
    @GetMapping("{carId}/delete")
    public RedirectView removeCar(@PathVariable String carId,
                             Model model) {
        carService.delete(carId);
        model.addAttribute("carId", carId);
        return new RedirectView("/ui/users/{id}/garage");
    }
}
