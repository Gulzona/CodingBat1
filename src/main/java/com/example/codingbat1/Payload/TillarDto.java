package com.example.codingbat1.Payload;

import com.example.codingbat1.Entity.Categoriya;
import lombok.Data;

import java.util.List;

@Data
public class TillarDto {
    private String tilNomi;
    private List<Categoriya> categoriyaList;
}
