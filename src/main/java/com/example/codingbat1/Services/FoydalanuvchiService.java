package com.example.codingbat1.Services;

import com.example.codingbat1.Entity.Foydalanuvchi;
import com.example.codingbat1.Payload.ApiResponse;
import com.example.codingbat1.Repositary.FoydalanuvchiRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoydalanuvchiService {

    @Autowired
    FoydalanuvchiRepositary foydalanuvchiRepositary;
    public ApiResponse PostFoydalanuvchi(Foydalanuvchi foydalanuvchi) {
        boolean b = foydalanuvchiRepositary.existsByEmailAndPassword(foydalanuvchi.getEmail(), foydalanuvchi.getPassword());
        if(b) return new ApiResponse("Bunday foydalanuvhci allaqachon mavjud",false);
        Foydalanuvchi foydalanuvchi1=new Foydalanuvchi();
        foydalanuvchi1.setIsm(foydalanuvchi.getIsm());
        foydalanuvchi1.setFamilya(foydalanuvchi.getFamilya());
        foydalanuvchi1.setEmail(foydalanuvchi.getEmail());
        foydalanuvchi1.setPassword(foydalanuvchi.getPassword());
        foydalanuvchiRepositary.save(foydalanuvchi1);
        return new ApiResponse("Ma'lumot bazaga saqlandi",true);
    }

    public ApiResponse PutFoydalanuvchi(Foydalanuvchi foydalanuvchi, Integer id) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepositary.findById(id);
        if(byId.isPresent()){
            boolean b = foydalanuvchiRepositary.existsByEmailAndPassword(foydalanuvchi.getEmail(), foydalanuvchi.getPassword());
            if(b) return new ApiResponse("Bunday foydalanuvhci allaqachon mavjud",false);
            Foydalanuvchi foydalanuvchi1=byId.get();
            foydalanuvchi1.setIsm(foydalanuvchi.getIsm());
            foydalanuvchi1.setFamilya(foydalanuvchi.getFamilya());
            foydalanuvchi1.setEmail(foydalanuvchi.getEmail());
            foydalanuvchi1.setPassword(foydalanuvchi.getPassword());
            foydalanuvchiRepositary.save(foydalanuvchi1);
            return new ApiResponse("Foydalanuvchi ma'lumotlari tahrirlandi!",true);
        }
        return  new ApiResponse("bunday id da ma'lumot yoq",false);
    }
    public ApiResponse DeleteFoydalanuvchi(Integer id) {
        Optional<Foydalanuvchi> byId = foydalanuvchiRepositary.findById(id);
        if(!byId.isPresent()) return new ApiResponse("Bunday idda ma'lumor yoq",false);
        foydalanuvchiRepositary.deleteById(id);
        return new ApiResponse("Ma'limotlar o'chirildi",true);
    }

}
