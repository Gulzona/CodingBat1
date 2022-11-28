package com.example.codingbat1.Controller;

import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.SavollarDto;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.SavollarRepositary;
import com.example.codingbat1.Services.SavollarService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/savollar")
public class SavollarController {
    @Autowired
    SavollarService savollarService;
    @PostMapping("/kiritish")
    public HttpEntity<?> Kiritish(@RequestBody SavollarDto savollarDto){
        ApiResponse apiResponse=savollarService.PostSavollar(savollarDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }

    @PutMapping("/editSavollar/{id}")
    public HttpEntity<?> EditSavollar(@RequestBody SavollarDto savollarDto,@PathVariable Integer id){
        ApiResponse apiResponse=savollarService.PutSavollar(savollarDto,id);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/deleteSavollar/{id}")
    public HttpEntity<?> DeleteSavollar(@PathVariable Integer id){
        ApiResponse apiResponse=savollarService.DeleteSavollar(id);
        return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

    @Autowired
    SavollarRepositary savollarRepositary;
    @GetMapping("/oqish")
    public HttpEntity<?> Oqish(){
        return ResponseEntity.ok( savollarRepositary.findAll());
    }


    @GetMapping("/oqishId/{id}")
    public HttpEntity<?> OqishId(@PathVariable Integer id){
        return ResponseEntity.ok( savollarRepositary.findById(id));
    }




}
