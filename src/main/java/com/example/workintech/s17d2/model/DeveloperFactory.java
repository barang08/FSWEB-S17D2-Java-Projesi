package com.example.workintech.s17d2.model;

import com.example.workintech.s17d2.tax.Taxable;

public class DeveloperFactory {

    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer createDeveloper = null;
        if(developer.getExperience().name().equalsIgnoreCase("junior")){
         createDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(), (developer.getSalary()- developer.getSalary()* taxable.getSimpleTaxRate())/100);

        }else if(developer.getExperience().name().equalsIgnoreCase("mid")){
            createDeveloper = new MidDeveloper(developer.getId(), developer.getName(), (developer.getSalary()- developer.getSalary()* taxable.getMiddleTaxRate())/100);
        }else if(developer.getExperience().name().equalsIgnoreCase("senior")){
            createDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(), (developer.getSalary()- developer.getSalary()* taxable.getUpperTaxRate())/100);
        }
return createDeveloper;

    }
}
