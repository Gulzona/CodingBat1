package com.example.codingbat1.Controller;

import com.example.codingbat1.Entity.Foydalanuvchi;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.FoydalanuvchiRepositary;
import com.example.codingbat1.Services.FoydalanuvchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/foydalanuvchi")
public class FoydalanuvchiController {
     @Autowired
     FoydalanuvchiService foydalanuvchiService;

     @PostMapping("/kiritiish")
     public HttpEntity<?> Kiritiish(@RequestBody Foydalanuvchi foydalanuvchi){
         ApiResponse apiResponse=foydalanuvchiService.PostFoydalanuvchi(foydalanuvchi);
         return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
     }
    @PutMapping("/tahrirlash/{id}")
    public HttpEntity<?> Tahrirlash(@RequestBody Foydalanuvchi foydalanuvchi,@PathVariable Integer id){
        ApiResponse apiResponse=foydalanuvchiService.PutFoydalanuvchi(foydalanuvchi,id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
   @DeleteMapping("/ochirish/{id}")
    public HttpEntity<?> Ochirish(@PathVariable Integer id){
        ApiResponse apiResponse=foydalanuvchiService.DeleteFoydalanuvchi(id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @Autowired
    FoydalanuvchiRepositary foydalanuvchiRepositary;
    @GetMapping("/oqish")
    public HttpEntity<?> Oqish(){
        return ResponseEntity.ok(foydalanuvchiRepositary.findAll());
    }

    @GetMapping("/oqishId/{id}")
    public HttpEntity<?> OqishId(@PathVariable Integer id){
        return ResponseEntity.ok( foydalanuvchiRepositary.findById(id));
    }
}
