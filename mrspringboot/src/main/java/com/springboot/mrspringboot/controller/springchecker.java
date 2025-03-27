    package com.springboot.mrspringboot.controller;
    import com.springboot.mrspringboot.DataStore.Pojo;
    import com.springboot.mrspringboot.DataStore.User;
    import com.springboot.mrspringboot.service.UserService;
    import org.bson.types.ObjectId;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;
    import com.springboot.mrspringboot.service.springservice;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/can")
    public class springchecker {

        @Autowired
        private springservice springservice;

        @Autowired
        private UserService userService;


        @PostMapping()
        public boolean add(@RequestBody Pojo myentry) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            myentry.setDate(LocalDateTime.now());
            springservice.saveEntry(myentry, userName);
            return true;
        }


        @GetMapping()
        public List<Pojo> get() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User use= userService.findbyname(userName);
            return use.getGetall();
        }

        @GetMapping("id/{id}")
        public Optional<Pojo> byid(@PathVariable ObjectId id) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User use= userService.findbyname(userName);
            List<Pojo> collect = use.getGetall().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
            try {
                if (!collect.isEmpty()){
                    return springservice.id(id);
                }
            }
            catch (Exception e){
                System.out.println(e);
              return   Optional.empty();
            }
            return Optional.empty();
        }
        @DeleteMapping("{id}")
        public boolean delete(@PathVariable ObjectId id){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User use= userService.findbyname(userName);
            List<Pojo> collect = use.getGetall().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
                if (!collect.isEmpty()){
                    springservice.del(id);
                    return true;
                }

          return false;

        }
    }
