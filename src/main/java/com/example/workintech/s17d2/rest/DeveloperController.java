package com.example.workintech.s17d2.rest;

import com.example.workintech.s17d2.dto.DeveloperResponse;
import com.example.workintech.s17d2.model.Developer;
import com.example.workintech.s17d2.model.DeveloperFactory;
import com.example.workintech.s17d2.model.Experience;
import com.example.workintech.s17d2.tax.DeveloperTax;
import com.example.workintech.s17d2.tax.Taxable;
import com.example.workintech.s17d2.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    Map<Integer, Developer> developers;
    private Taxable taxable;

    @Autowired
    public DeveloperController(DeveloperTax taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    @PostMapping
            @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer) {
        Developer createdDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
       if(createdDeveloper != null){
developers.put(createdDeveloper.getId(),createdDeveloper);
       }
return new DeveloperResponse(createdDeveloper,"Developer created", HttpStatus.OK.value());

    }

    @GetMapping
    public List<Developer> getAll(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable("id") Integer id ) {
        if (DeveloperValidation.isDeveloperExist(this.developers, id)) {
            return new DeveloperResponse(this.developers.get(id), "success", HttpStatus.OK.value());
        }
        return new DeveloperResponse(null, "developer does not exist", HttpStatus.NOT_FOUND.value());
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable("id") Integer id, @RequestBody Developer developer){
        if(!DeveloperValidation.isDeveloperExist(this.developers, id)){
            return new DeveloperResponse(null, "developer already does not exist id = " +id , HttpStatus.NOT_FOUND.value());
        }
        developer.setId(id);
        Developer developer1 = DeveloperFactory.createDeveloper(developer,taxable);
        developers.put(developer1.getId(),developer1);
        return new DeveloperResponse(developer1,"successed", HttpStatus.OK.value());
    }


    @DeleteMapping("/{id}")
    public DeveloperResponse deleteDeveloper(@PathVariable("id") Integer id){
        Developer removedDev = developers.get(id);
        developers.remove(id);
        return new DeveloperResponse(removedDev,"deleted",HttpStatus.OK.value() );
    }

}
