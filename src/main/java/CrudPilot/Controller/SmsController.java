package CrudPilot.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class SmsController {

    @Autowired
    private Environment env;

    Logger logger = LoggerFactory.getLogger(SmsController.class);

    @PostMapping(value = "/sendSMS")
    public ResponseEntity<Map<String, Object>> sendSMS(@RequestBody Map<String, Object> request) {

        logger.info("Request received on SmsController : {}", request);

        Twilio.init(Objects.requireNonNull(env.getProperty("TWILIO_ACCOUNT_SID")), Objects.requireNonNull(env.getProperty("TWILIO_AUTH_TOKEN")));

        Map<String, Object> response = new HashMap<>();


        Message.creator(new PhoneNumber(request.get("phone_number").toString()),
                new PhoneNumber(Objects.requireNonNull(env.getProperty("TWILIO_PHONE_NUMBER"))), request.get("message").toString()).create();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
