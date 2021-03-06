package senai.sp.cotia.auditorio.rest;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import senai.sp.cotia.auditorio.model.EmailModel;
import senai.sp.cotia.auditorio.services.EmailService;

import javax.validation.Valid;


@RestController
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    EmailService emailService;
    

    @RequestMapping(value="sending-email", method = RequestMethod.POST)
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

    /*
    @GetMapping("/emails")
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
    */
}
