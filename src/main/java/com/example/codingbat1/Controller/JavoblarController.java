package com.example.codingbat1.Controller;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.JavoblarDto;
import com.example.codingbat1.Repositary.JavoblarRepositary;
import com.example.codingbat1.Services.JavoblarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("javoblar")
public class JavoblarController {
    @Autowired
    JavoblarService javoblarService;

    @PostMapping("/JavoblarKiritish")
    public HttpEntity<?> Kiritish(@RequestBody JavoblarDto javoblarDto){
        ApiResponse apiResponse=javoblarService.PostJavoblar(javoblarDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> Kiritish1(@RequestBody JavoblarDto javoblarDto,@PathVariable Integer  id){
        ApiResponse apiResponse=javoblarService.PutJavoblar(javoblarDto,id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> Delete(@PathVariable Integer  id){
        ApiResponse apiResponse=javoblarService.DeleteJavoblar(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }

    @Autowired
    JavoblarRepositary javoblarRepositary;
    @GetMapping("/oqish")
    public HttpEntity<?> Oqish(){
        return ResponseEntity.ok(javoblarRepositary.findAll());
    }

    @GetMapping("/oqishId/{id}")
    public HttpEntity<?> OqishId(@PathVariable Integer id){
        return ResponseEntity.ok( javoblarRepositary.findById(id));
    }

}
