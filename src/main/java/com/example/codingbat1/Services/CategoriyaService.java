package com.example.codingbat1.Services;

import com.example.codingbat1.Entity.Categoriya;
import com.example.codingbat1.Entity.Tillar;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.CategoriyaDto;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.TillarRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriyaService {
    @Autowired
    CategoriyaRepositary categoriyaRepositary;

    @Autowired
    TillarRepositary tillarRepositary;

    public ApiResponse PostCategoriya(CategoriyaDto categoriyaDto) {
            Optional<Tillar> byId = tillarRepositary.findById(categoriyaDto.getTillarId());
            if(!byId.isPresent()) return new ApiResponse("Til topilmadi",false);
            boolean b = categoriyaRepositary.existsByNomiAndTillarId(categoriyaDto.getNomi(), categoriyaDto.getTillarId());
            if(b) return new ApiResponse("Bunday nomli categoriya mavjud",false);
            Categoriya categoriya=new Categoriya();
            categoriya.setNomi(categoriyaDto.getNomi());
            categoriya.setIzoh(categoriyaDto.getIzoh());
            categoriya.setYulduzchalarSoni(categoriyaDto.getYulduzchalarSoni());
            categoriya.setTillar(tillarRepositary.findById(categoriyaDto.getTillarId()).get());
            categoriyaRepositary.save(categoriya);
            return new ApiResponse("Bazaga joylandi",true);
        }
    public ApiResponse EditCategoriya(CategoriyaDto categoriyaDto, Integer id) {
        Optional<Categoriya> byId = categoriyaRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday categoriya yo'q",false);
        Categoriya categoriya=byId.get();
        categoriya.setNomi(categoriyaDto.getNomi());
        categoriya.setIzoh(categoriyaDto.getIzoh());
        categoriya.setYulduzchalarSoni(categoriyaDto.getYulduzchalarSoni());
        categoriya.setTillar(tillarRepositary.findById(categoriyaDto.getTillarId()).get());
        categoriyaRepositary.save(categoriya);
        return new ApiResponse("Malumot tahrirlandi!",true);
    }

    public ApiResponse DeleteCategoriya(Integer id) {
        Optional<Categoriya> byId =categoriyaRepositary.findById(id);
        if (!byId.isPresent())  return new ApiResponse("Bazada bunday idda ma'lumot mavjud emas",false);
        categoriyaRepositary.deleteById(id);
        return new ApiResponse("Ma'lumotlar o'chirildi!",true);
    }
}
