package com.example.codingbat1.Services;
import com.example.codingbat1.Entity.Categoriya;
import com.example.codingbat1.Entity.Namuna;
import com.example.codingbat1.Entity.Savollar;
import com.example.codingbat1.Entity.Tillar;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.SavollarDto;
import com.example.codingbat1.Payload.TillarDto;
import com.example.codingbat1.Repositary.CategoriyaRepositary;
import com.example.codingbat1.Repositary.NamunaRepositary;
import com.example.codingbat1.Repositary.SavollarRepositary;
import com.example.codingbat1.Repositary.TillarRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavollarService {
     @Autowired
    SavollarRepositary savollarRepositary;
     @Autowired
    NamunaRepositary namunaRepositary;
     @Autowired
    TillarRepositary tillarRepositary;
     @Autowired
    CategoriyaRepositary categoriyaRepositary;


    public ApiResponse PostSavollar(SavollarDto savollarDto) {
        Optional<Tillar> byId = tillarRepositary.findById(savollarDto.getTillarId());
        if(!byId.isPresent()) return new ApiResponse("Til mavjud emas",false);
        Optional<Categoriya> byId1 = categoriyaRepositary.findById(savollarDto.getCategoriyaId());
        if(!byId1.isPresent())  return new ApiResponse("Bunday categoriya mavjud emas",false);
        boolean b = savollarRepositary.existsByNomiAndCategoriya_Id(savollarDto.getNomi(), savollarDto.getCategoriyaId());
            if(b) return new ApiResponse("Bunday savol mavjud",false);
            Savollar savollar=new Savollar();
            savollar.setNomi(savollarDto.getNomi());
            savollar.setMatni(savollarDto.getMatni());
            savollar.setYechim(savollarDto.getYechim());
            savollar.setYordam(savollarDto.getYordam());
            savollar.setFunksiya(savollarDto.getFunksiya());
            Namuna namuna=new Namuna();
            namuna.setMant(savollarDto.getMatn());
            namunaRepositary.save(namuna);
            savollar.setNamuna(namuna);
            savollar.setTillar(byId.get());
            savollar.setCategoriya(byId1.get());
            savollarRepositary.save(savollar);
            return  new ApiResponse("Saqlandi",true);

    }

//    public ApiResponse PutSavollar(SavollarDto savollarDto, Integer id) {
//        Optional<Savollar> byId1 = savollarRepositary.findById(id);
//        if(byId1.isPresent()) {
//            Optional<Tillar> byId = tillarRepositary.findById(savollarDto.getTillarId());
//            if(!byId.isPresent()) return new ApiResponse("Til mavjud emas",false);
//            boolean b = savollarRepositary.existsByNomiAndTillarId(savollarDto.getNomi(), savollarDto.getTillarId());
//            if(b) return new ApiResponse("Bunday savol mavjud",false);
//            Savollar savollar=byId1.get();
//            savollar.setNomi(savollarDto.getNomi());
//            savollar.setMatni(savollarDto.getMatni());
//            savollar.setYechim(savollarDto.getYechim());
//            savollar.setYordam(savollarDto.getYordam());
//            savollar.setFunksiya(savollarDto.getFunksiya());
//            Namuna namuna= byId1.get().getNamuna();
//            namuna.setMant(savollarDto.getMatn());
//            namunaRepositary.save(namuna);
//            savollar.setNamuna(namuna);
//            savollar.setTillar(byId.get());
//            savollarRepositary.save(savollar);
//            return  new ApiResponse("Tahrirlandi",true);
//        }
//        return new ApiResponse("Bunday id da ma'lumot yoq",false);
//
//    }

    public ApiResponse DeleteSavollar(Integer id) {
        Optional<Savollar> byId = savollarRepositary.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Bunday id da ma'lumot mavjud emas!",false);
        savollarRepositary.deleteById(id);
        return new ApiResponse("Savollar o'chirildi",true);
    }

//    public ApiResponse GetSavollar(Integer id) {
//        Optional<Savollar> byId = savollarRepositary.findById(id);
//        if(byId.isPresent()){
//          Savollar savollar=byId.get();
//          Namuna namuna=namunaRepositary.findById(savollarRepositary.findById(id).get().getNamuna().getId()).get();
//          Tillar tillar=tillarRepositary.findById(savollarRepositary.findById(id).get().getTillar().getId()).get();
//          Categoriya categoriya=categoriyaRepositary.findById(savollarRepositary.findById(id).get().getCategoriya().getId()).get();
////          String xabar="nomi:"+savollar.getNomi()+"\n"+"matni:"+savollar.getMatni()+"\n"+"yechim:"+savollar.getYechim()+
////                  "\n"+"funksiya:"+savollar.getFunksiya()+"\n"+"yordam:"+savollar.getYordam()+"\n"+"mant:"+namuna.getMant()+
////"\n"+"tilNomi:"+tillar.getTilNomi()+"\n"+"nomi:"+categoriya.getNomi()+"\n"+"izoh:"+categoriya.getIzoh()+" yulduzchalarSoni:"+categoriya.getYulduzchalarSoni();
//
//          return new ApiResponse(xabar,true);
//        }
//        return new ApiResponse("Bunday id yoq",false);
//    }
}
