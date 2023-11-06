package com.server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.server.Repository.UserRepo;
import com.server.Request.UserRequest;
import com.server.Model.UserModel;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<UserModel> getUserList(){
        return userRepo.findAll();
    }

    public UserModel getUserById(Long id) throws Exception{
        return userRepo.findById(id).orElseThrow(() -> new Exception("No user found."));
    }

    public List<UserModel> getUserByName(String name){
        return userRepo.findByName(name);
    }

    public Optional<UserModel> getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public List<UserModel> getUserByPhone(String phone){
        return userRepo.findByPhone(phone);
    }

    public UserModel createUser(UserRequest userRequest){
        try {
            UserModel userModel = new UserModel();
            userModel.setName(userRequest.getName());
            userModel.setEmail(userRequest.getEmail());
            userModel.setPhone(userRequest.getPhone());
            userModel.setAdmin(userRequest.isAdmin());
            userRepo.save(userModel);
            return userModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserModel updateUser(Long id, UserRequest userRequest) throws Exception{
        try {
            UserModel userModel = userRepo.findById(id).orElseThrow(() -> new Exception("No user found"));
            if(userRequest.getEmail() != null && !userRequest.getEmail().equals("")){
                userModel.setEmail(userRequest.getEmail());
            }
            if(userRequest.getName() != null && !userRequest.getName().equals("")){
                userModel.setName(userRequest.getName());
            }
            if(userRequest.getPhone() != null && !userRequest.getPhone().equals("")){
                userModel.setPhone(userRequest.getPhone());
            }
            if(userRequest.getPassword() != null && !userRequest.getPassword().equals("")){
                userModel.setPassword(userRequest.getPassword());
            }
            userRepo.save(userModel);
            return userModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserModel deleteUser(Long id) throws Exception{
        try {
            UserModel userModel = userRepo.findById(id).orElseThrow(() -> new Exception("No user found"));
            userRepo.delete(userModel);
            return userModel;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UserModel login(String email, String password) throws Exception{
        try {
            UserModel userModel = userRepo.findByEmail(email).orElseThrow(() -> new Exception("No user found."));
            if(userModel.getPassword() == null){
                userModel.setPassword(password);
                userRepo.save(userModel);
                return userModel;
            } else if (userModel.getPassword().equals(password)) {
                return userModel;
            } else {
                throw new Exception("Check your email and password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
