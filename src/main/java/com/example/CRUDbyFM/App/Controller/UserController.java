package com.example.CRUDbyFM.App.Controller;

import com.example.CRUDbyFM.App.Model.User;
import com.example.CRUDbyFM.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService ;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // User object comes as JSON format
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user
    ){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);

//        userDb.putIfAbsent(user.getId(),user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public  ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // /user/1 /user/2 /user/3 ---> dynamic url
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        boolean isDeleted = userService.deleteUser(id);
//        if(!userDb.containsKey(id)) return  new ResponseEntity<>(id+"",HttpStatus.NOT_FOUND);
        if(!isDeleted) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok("User " + id + " deleted");

//        userDb.remove(id);
//        return new ResponseEntity<>(id+"",HttpStatus.OK);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUser();
    }

//    @GetMapping("/users","/users/{id}")
    //  /user/100

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser( @PathVariable(
            value = "userId",required = false) int id
    ){
        User user = userService.getUserById(id);
        if(user == null) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/orders/{userId}/{orderId}")
//    public ResponseEntity<User> getUserOrder(
//            @PathVariable("userId") int id,
//            @PathVariable int orderId
//    ){
//        System.out.println("Order id : " + orderId);
//        if(!userDb.containsKey(id)) return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        return ResponseEntity.ok(userDb.get(id));
//    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = true,defaultValue = "name") String name,
            @RequestParam(required =true,defaultValue = "email") String email
            // required = false make the input  String name optional
    ){

        return ResponseEntity.ok(userService.searchUser(name,email));
    }

    @GetMapping("/info")
    public String getInfo(@RequestHeader("User-Agent") String userAgent){
        return "User Agent  : "+userAgent;
    }
}
