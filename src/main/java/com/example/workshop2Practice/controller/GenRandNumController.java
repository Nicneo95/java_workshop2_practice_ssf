package com.example.workshop2Practice.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.workshop2Practice.models.NumberInput;

@Controller
public class GenRandNumController {
    // Display the form
    @GetMapping(path = "/")
    public String displayRandForm(Model model) {
        NumberInput in = new NumberInput();
        model.addAttribute("numberObj", in);
        return "numberForm";
    }

    @PostMapping("/generate")
    public String storeInputValue(@ModelAttribute NumberInput numberInput, Model model) {
        int maxGenNum = 30;
        String[] imgNumbers = new String[maxGenNum+1];
        for(int i = 0; i < maxGenNum; i++) {
            imgNumbers[i] = "number" + i + ".jpg";
        }
        if (numberInput.getNumberVal() < 1 || numberInput.getNumberVal() > 30) {
            throw new FormInputOutOfRangeException("Input number must be between 1 and 30 inclusive");
        }
        Set<Integer> randomNumbers = new HashSet<>();
        Random random = new Random();
        while (randomNumbers.size() < numberInput.getNumberVal()) {
            randomNumbers.add(random.nextInt(30)+1);
        }
        
        
        model.addAttribute("numberInput", numberInput);
        model.addAttribute("randomNumbers", randomNumbers);

        return "result";
    }
}
