package com.example.codingbat1.Services;

import com.example.codingbat1.Entity.Categoriya;
import com.example.codingbat1.Entity.Tillar;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.TillarDto;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.TillarRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TillarService {
    @Autowired
    TillarRepositary tillarRepositary;

    @Autowired
    CategoriyaRepositary categoriyaRepositary;
    public ApiResponse PostTillar(TillarDto tillarDto) {
        boolean b = tillarRepositary.existsByTilNomi(tillarDto.getTilNomi());
        if(b) return  new ApiResponse("Bunday nomli til mavjud",false);
        Tillar tillar1=new Tillar();
        tillar1.setTilNomi(tillarDto.getTilNomi());
        List<Categoriya> categoriyaList=new ArrayList<>();
        for(Categoriya categoriya : tillarDto.getCategoriyaList()){
            Categoriya categoriya1=new Categoriya();
            categoriya1.setNomi(categoriya.getNomi());
            categoriya1.setIzoh(categoriya.getIzoh());
            categoriya1.setYulduzchalarSoni(categoriya.getYulduzchalarSoni());
            categoriya1.setTillar(tillar1);
            categoriyaList.add(categoriya1);
        }
        tillar1.setCategoriyaList(categoriyaList);
        tillarRepositary.save(tillar1);
        return new ApiResponse("Ma'lumot saqlandi!",true);
    }

    public ApiResponse PutTillar(Integer id, TillarDto tillarDto) {
        Optional<Tillar> byId = tillarRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday til mavjud emas",false);
        Tillar tillar=byId.get();
        tillar.setTilNomi(tillarDto.getTilNomi());
        List<Categoriya> byTillarId = categoriyaRepositary.findByTillarId(id);
        List<Categoriya> categoriyaList=new ArrayList<>();
        Integer i=0;
        for(Categoriya categoriya:tillarDto.getCategoriyaList()){
            Categoriya categoriya1=byTillarId.get(i);
            categoriya1.setNomi(categoriya.getNomi());
            categoriya1.setIzoh(categoriya.getIzoh());
            categoriya1.setYulduzchalarSoni(categoriya.getYulduzchalarSoni());
            categoriya1.setTillar(tillar);
            categoriyaList.add(categoriya1);
            i++;
        }
        tillar.setCategoriyaList(categoriyaList);
        tillarRepositary.save(tillar);
        return new ApiResponse("Ma'lumot tahrirlandi!",true);



    }

    public ApiResponse DeleteTil(Integer id) {
        Optional<Tillar> byId =tillarRepositary.findById(id);
        if (!byId.isPresent())  return new ApiResponse("Bazada bunday idda ma'lumot mavjud emas",false);
        tillarRepositary.deleteById(id);
        return new ApiResponse("Ma'lumotlar o'chirildi",true);

    }
}
