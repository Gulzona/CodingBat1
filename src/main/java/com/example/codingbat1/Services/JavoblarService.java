package com.example.codingbat1.Services;

import com.example.codingbat1.Entity.Foydalanuvchi;
import com.example.codingbat1.Entity.Javoblar;
import com.example.codingbat1.Entity.Savollar;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Payload.JavoblarDto;
import com.example.codingbat1.Repositary.FoydalanuvchiRepositary;
import com.example.codingbat1.Repositary.JavoblarRepositary;
import com.example.codingbat1.Repositary.SavollarRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JavoblarService {
    @Autowired
    JavoblarRepositary javoblarRepositary;

    @Autowired
    SavollarRepositary savollarRepositary;

    @Autowired
    FoydalanuvchiRepositary foydalanuvchiRepositary;

    public ApiResponse PostJavoblar(JavoblarDto javoblarDto) {
        Optional<Savollar> byId = savollarRepositary.findById(javoblarDto.getSavollarId());
        if(!byId.isPresent()) return new ApiResponse("Bazada bunday savol yoq",false);
        Optional<Foydalanuvchi> byId1 = foydalanuvchiRepositary.findById(javoblarDto.getFoydalanuvhciId());
        if(!byId1.isPresent()) return new ApiResponse("BUnday faydalanuvchi yoq",false);
        Javoblar javoblar=new Javoblar();
        javoblar.setTekshirish(javoblarDto.isTekshirish());
        javoblar.setSavollar(savollarRepositary.findById(javoblarDto.getSavollarId()).get());
        javoblar.setFoydalanuvchi(foydalanuvchiRepositary.findById(javoblarDto.getFoydalanuvhciId()).get());
        javoblarRepositary.save(javoblar);
        return new ApiResponse("Bazaga saqlandi",true);
    }

    public ApiResponse PutJavoblar(JavoblarDto javoblarDto, Integer id) {
        Optional<Javoblar> byId = javoblarRepositary.findById(id);
        if(byId.isPresent()){
            Optional<Savollar> byId2 = savollarRepositary.findById(javoblarDto.getSavollarId());
            if(!byId.isPresent()) return new ApiResponse("Bazada bunday savol yoq",false);
             Optional<Foydalanuvchi> byId1 = foydalanuvchiRepositary.findById(javoblarDto.getFoydalanuvhciId());
             if(!byId1.isPresent()) return new ApiResponse("BUnday faydalanuvchi yoq",false);
                 Javoblar javoblar=byId.get();
                  javoblar.setTekshirish(javoblarDto.isTekshirish());
                  javoblar.setSavollar(savollarRepositary.findById(javoblarDto.getFoydalanuvhciId()).get());
                  javoblar.setFoydalanuvchi(foydalanuvchiRepositary.findById(javoblarDto.getFoydalanuvhciId()).get());
                  javoblarRepositary.save(javoblar);
                  return  new ApiResponse("Ma'lumotlar tahrirlandi",true);
        }
        return new ApiResponse("Bunday id da ma'lumot yoq",false);
    }

    public ApiResponse DeleteJavoblar(Integer id) {
            Optional<Javoblar> byId = javoblarRepositary.findById(id);
             if(!byId.isPresent()) return new ApiResponse("Bunday id mavjud emas",false);
             javoblarRepositary.deleteById(id);
             return new ApiResponse("Ma'lumotlar o'chirildi",false);
    }
}
