package CrudPilot.CommandLineRunner;

import CrudPilot.Controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
    This has been added to try if we can implement the functionality to execute the cURLs on the fly at the time Application startup.
 */

@Component
public class CurlOnStartup implements CommandLineRunner {

    @Autowired
    CrudController crudController;

    @Override
    public void run(String... args) throws Exception {
//        String curl = "curl --location --request POST 'http://localhost:8080/createTable' \\\n" +
//                "--header 'Content-Type: application/json' \\\n" +
//                "--data-raw '{\n" +
//                "    \"element_name\": \"movie\",\n" +
//                "    \"configuration\": {\n" +
//                "        \"name\": \"VARCHAR(255)\",\n" +
//                "        \"age\": \"INT\",\n" +
//                "        \"nationality\": \"VARCHAR(255)\",\n" +
//                "        \"address\": \"VARCHAR(255)\"\n" +
//                "    }\n" +
//                "}'";
//
//        try{
//            Process process = Runtime.getRuntime().exec(curl);
//            int exitCode = process.waitFor();
//
//            System.out.println("Inside CurlOnStartUp run() ...");
//
//            if (exitCode == 0){
//                System.out.println("Curl command executed sucessfully");
//            }else {
//                System.out.println("Error occurred while executing Curl. Exit Code : " + exitCode);
//            }
//
//        }catch (Exception e){
//            System.out.println("Exception occurred probable cause : " + e.toString());
//        }

    }
}
