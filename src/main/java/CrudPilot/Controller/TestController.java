package CrudPilot.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // Adjust the origin based on your Angular app's URL
public class TestController {
    @GetMapping("/test")
    public Callable<String> testController() {
        return () -> {
            System.out.println("Inside TestController()");
        try{
            Thread.sleep(3000); // 3 seconds
        }catch (InterruptedException e){
            System.out.printf("thread InterruptedException : " + e);
        }
            return "Success";
        };
    }



}
