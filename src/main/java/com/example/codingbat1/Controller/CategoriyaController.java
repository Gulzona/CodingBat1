package com.example.codingbat1.Controller;

import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.CategoriyaDto;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.TillarRepositary;
import com.example.codingbat1.Services.CategoriyaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoriya")
public class CategoriyaController {
      @Autowired
      CategoriyaService categoriyaService;

      @PostMapping("/joylash")
      public HttpEntity<?> JoylashCategoriya(@RequestBody CategoriyaDto categoriyaDto){
            ApiResponse apiResponse=categoriyaService.PostCategoriya(categoriyaDto);
            return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
      }
      @Autowired
      CategoriyaRepositary categoriyaRepositary;
      @GetMapping("/oqish")
      public HttpEntity<?> Oqish(){
            return ResponseEntity.ok(categoriyaRepositary.findAll());
      }

      @GetMapping("/oqishId/{id}")
      public HttpEntity<?> OqishId(@PathVariable Integer id){
            return ResponseEntity.ok( categoriyaRepositary.findById(id));
      }


      @PutMapping("/editCategoriya/{id}")
      public HttpEntity<?> Edit1(@RequestBody CategoriyaDto categoriyaDto,@PathVariable Integer id){
            ApiResponse apiResponse=categoriyaService.EditCategoriya(categoriyaDto,id);
            return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }

      @DeleteMapping("/delete/{id}")
      public HttpEntity<?> Delete(@PathVariable Integer id){
            ApiResponse apiResponse=categoriyaService.DeleteCategoriya(id);
            return  ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }


}
