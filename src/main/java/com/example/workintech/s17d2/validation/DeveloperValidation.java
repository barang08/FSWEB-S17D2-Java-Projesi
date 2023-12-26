package com.example.workintech.s17d2.validation;

import com.example.workintech.s17d2.model.Developer;

import java.util.Map;

public class DeveloperValidation {
    public static boolean isDeveloperExist(Map<Integer, Developer> developers, int id){
        return developers.containsKey(id);

    }
}
