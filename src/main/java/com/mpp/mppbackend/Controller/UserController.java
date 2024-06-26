package com.mpp.mppbackend.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mpp.mppbackend.Model.Car;
import com.mpp.mppbackend.Model.User;
import com.mpp.mppbackend.Model.UserAccount;
import com.mpp.mppbackend.Model.Views;
import com.mpp.mppbackend.Repository.UserAccountRepository;
import com.mpp.mppbackend.Service.UserService;
import com.mpp.mppbackend.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user, @RequestHeader("Authorization") String token){
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        userService.addUser(user, userAccount);
        Map<String, Object> response = new HashMap<>();
        response.put("id", userService.getUserId(user));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestParam int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/getUsers")
    public Iterable<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam int id) {
        return userService.getUser(id);
    }

    @PostMapping("/addCarToUser")
    public void addCarToUser(@RequestParam int userId, @RequestBody Car car) {
        userService.addCarToUser(userId, car);
    }

    @DeleteMapping("/removeCarFromUser")
    public void removeCarFromUser(@RequestParam int carId) {
        userService.removeCarFromUser(carId);
    }

    @GetMapping("/getCarsByUserId")
    public Iterable<Car> getCarsByUserId(@RequestParam int userId) {
        return userService.getCarsByUserId(userId);
    }

    @GetMapping("/getPagesCount")
    public int getPagesCount(@RequestHeader("Authorization") String token){
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        return userService.getPagesCount(userAccount);
    }

    @GetMapping("/getPages")
    @JsonView(Views.Public.class)
    public Iterable<User> getPages(@RequestParam int page, @RequestHeader("Authorization") String token){
        String username = jwtUtil.getUsernameFromToken(token.substring(7));
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        return userService.getPages(page, userAccount);
    }

}
