package pl.edu.pjwstk.jaz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.example.ExampleService;

@RestController
public class ExampleController {

    private final ExampleService exampleService;


    //tu jest klopot
    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping("/testEndpoint")
    public String test() {
        return exampleService.getMyHeader();
    }
}
