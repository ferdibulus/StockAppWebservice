package com.stock.stockapp.controller;
import com.stock.stockapp.entity.Users;
import com.stock.stockapp.responses.UsersResponse;
import com.stock.stockapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    UserService usersService;

    @GetMapping("/users")
    public List<Users> getListOfUsers(){
        return usersService.getListOfUsers();
    }

    @PostMapping("/users")
    public String save(@RequestBody Users user){
        try {
            usersService.save(user);
        }catch (Exception e){
            return "Fail";
        }
        return "Success";
    }
    @PutMapping("/users/{id}")
    public String update(@RequestBody Users item,@PathVariable Integer id){
        try{
            Optional<Users> product = usersService.findById(id);
            product.map(temp->{
                temp.setUsername(item.getUsername());
                temp.setPassword(item.getPassword());
                temp.setApproved(item.isApproved());
                usersService.save(temp);
                return "";
            });
        }catch (Exception e) {
            return "Fail";
        }
        return "Success";
    }

    @DeleteMapping("/users/{id}")
    public String  delete(@PathVariable Integer id) {
        try{
            usersService.deleteById(id);
        }catch (Exception e){
            return "Fail";
        }
        return "Success";
    }

    @PostMapping("/users/login")
    public ResponseEntity<UsersResponse> login(@RequestBody Users user){
        UsersResponse response = new UsersResponse();
        try {
            List<Users> userLogin = usersService.findUser(user);
            for(int i=0;i<userLogin.size(); i++){
                byte[] decodedBytes = Base64.getDecoder().decode(userLogin.get(i).getPassword());
                String decodedPass = new String(decodedBytes);
                if(userLogin.get(i).getUsername().equals(user.getUsername()) && decodedPass.equals(user.getPassword()) && userLogin.get(i).isApproved() == true){
                    response.setUsername(user.getUsername());
                    response.setPassword(user.getPassword());
                }
            }
        }catch (Exception e){
            return new ResponseEntity<UsersResponse>(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UsersResponse>(response,HttpStatus.ACCEPTED);
    }

}
