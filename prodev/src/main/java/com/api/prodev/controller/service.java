package com.api.prodev.controller;

import com.api.prodev.pojo.DataPojo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/service")
public class service {
    private   Map<Long, DataPojo>  store=new HashMap<>();

    @PostMapping
    public boolean add(@RequestBody DataPojo data){
        data.setDate(LocalDateTime.now());
        store.put(data.getId(), data);
        return true;
    }

    @GetMapping
    public List<DataPojo> get(){
        return new ArrayList(store.values());
    }

    @GetMapping("id/{id}")
    public DataPojo idget(@PathVariable Long id){
        return store.get(id);
    }
    @DeleteMapping("id/{id}")
    public boolean del(@PathVariable Long id){
        store.remove(id);
        return true;
    }

    @PutMapping("id/{id}")
    public List<DataPojo> geet(@PathVariable long id,@RequestBody DataPojo pojo){
        pojo.setDate(LocalDateTime.now());
        store.put(id,pojo);
        return new ArrayList(store.values());
    }
}
