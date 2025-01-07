package com.example.data.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SampleDTO {
    private String ss;
    private int ii;
    private List<Map<String, String>> lm;
}
