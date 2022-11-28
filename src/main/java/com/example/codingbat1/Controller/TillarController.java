package com.example.codingbat1.Controller;

import com.example.codingbat1.Entity.Tillar;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.TillarDto;
import com.example.codingbat1.Repositary.TillarRepositary;
import com.example.codingbat1.Services.TillarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tillar")
public class TillarController {

    @Autowired
    TillarService tillarService;

    @PostMapping("/joylash")
    public HttpEntity<?> Joylash(@RequestBody TillarDto tillarDto){
        ApiResponse apiResponse=tillarService.PostTillar(tillarDto);
        return ResponseEntity.status(apiResponse.isHolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @PutMapping("/edit/{id}")
    public HttpEntity<?> Edit(@PathVariable Integer id,@RequestBody TillarDto tillarDto){
        ApiResponse apiResponse=tillarService.PutTillar(id,tillarDto);
        return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @Autowired
    TillarRepositary tillarRepositary;
    @GetMapping("/oqish")
    public HttpEntity<?> Oqish(){
    return ResponseEntity.ok(tillarRepositary.findAll());
    }


    @GetMapping("/oqishId/{id}")
    public HttpEntity<?> OqishId(@PathVariable Integer id){
        return ResponseEntity.ok( tillarRepositary.findById(id));
    }




    @DeleteMapping("delete/{id}")
    public HttpEntity<?> DeleteId(@PathVariable Integer id){
        ApiResponse apiResponse=tillarService.DeleteTil(id);
        return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
}
