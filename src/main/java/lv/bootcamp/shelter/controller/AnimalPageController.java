package lv.bootcamp.shelter.controller;

import lombok.RequiredArgsConstructor;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AnimalPageController {

    private final AnimalService animalService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/animals")
    public String list(Model model) {
        model.addAttribute("animals", animalService.findAll());
        return "animals";
    }
}