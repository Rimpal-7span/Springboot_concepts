package com.example.restApi.controller;

import com.example.restApi.custome.CustomeUserDetail;
import com.example.restApi.dto.RequestUserDto;
import com.example.restApi.dto.ResponseUserDto;
import com.example.restApi.enumclass.UserType;
import com.example.restApi.model.User;
import com.example.restApi.repository.Userdao;
import com.example.restApi.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${user.action:}")
    String userAction;
    @Autowired
    private UserService userService;


    @Autowired
    Userdao userdao;


    @DeleteMapping(value = "/check")
    public void deleteaddress(@RequestParam Long id)
    {
        userService.deleteAddress(id);
    }

    @PostMapping("/")
    public ResponseUserDto saveDetail(@RequestBody @Valid RequestUserDto requestUserDto) {
        return userService.addUser(requestUserDto);
    }

    @GetMapping(value = "/getUser")
    public List<ResponseUserDto> getDetail() {
        return userService.getUser();
    }

    @GetMapping(value = "/getUserByUUid")
    public ResponseUserDto findByUuid(@RequestParam("uuid") UUID uuid)
    {
        return userService.findByUuid(uuid);
    }

    @GetMapping(value = "/getUserByid")
    public Optional<User> findById(@RequestParam("id") Long id)
    {
        return userService.findById(id);
    }


   @PutMapping(value = "/updateUser")
    public ResponseUserDto updateDetail(@RequestBody @Valid RequestUserDto requestUserDto) {
        return userService.updateUser(requestUserDto);
    }

    @DeleteMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "user delete with id" + id;
    }

    @PatchMapping(value = "/patchUser")
    public ResponseUserDto putUser(@RequestBody RequestUserDto requestUserDto) {
        return userService.patchUser(requestUserDto);
    }

    @GetMapping(value = "/getPagingUser")
    public Page<User> getPagingData() {
        return userService.getPagingData();
    }

    @GetMapping(value = "/getSortedUser")
    public List<ResponseUserDto> getSortedData() {
        return userService.getSortedData();
    }

    //Using @Query
    @GetMapping(value = "/getAllUser")
    public List<ResponseUserDto> findAllUser() {
    return userService.findAllUser();
    }


    //Using @Query
    @GetMapping(value = "/getUserByName")
    public List<ResponseUserDto> findUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }


    //Using Criteriabuilder
    @GetMapping(value = "/getUserByNameAndUserType")
    public List<ResponseUserDto> getByNameAndUserType(@RequestParam String name, @RequestParam UserType userType)
    {
        return userService.getByNameAndUserType(name,userType);

    }

    //Ccustom repository
    @GetMapping(value = "/getCustomeUser")
    public CustomeUserDetail findCustomeUser(@RequestParam UUID uuid) {
        return userdao.userDetail(uuid);
    }

    //Custome Repo with Criteriabuilder
    @GetMapping(value = "/getNameAndUserType")
    public List<ResponseUserDto> getNameAndUserType(@RequestParam String name, @RequestParam UserType userType)
    {
       return userdao.getNameAndUserType(name,userType);
    }

    @GetMapping(value = "/getUserByidLazy")
    public Optional<User> findByIdLazy(@RequestParam("id") Long id)
    {
        return userService.findByIdLazy(id);
    }
    @GetMapping(value = "/getUserByidLazyBuilder")
     public User findByIdLazyusingBuilder(Long id) {
        return userService.findByIdLazyusingBuilder(id);
    }

    /*@Scheduled(fixedRate = 5000)
    @GetMapping(value = "/schedularGet")
    public User updateUserBySchedular(@RequestBody User user)
    {
        HttpEntity<User> entity = new HttpEntity<User>(user);
        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8080/userManagement/users/getUserByid?id=1", HttpMethod.GET,entity,User.class);
        System.out.println(response.getBody());
        return user;
    }*/

    @GetMapping(value = "/test-profile")
    public void testProfile()
    {
        userService.testProfile();
        System.out.println("User Action is=>"+userAction);
    }

}