package com.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.server.Model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    public List<UserModel> findByName(String name);

    public Optional<UserModel> findByEmail(String email);

    public List<UserModel> findByPhone(String phone);

    public UserModel findByEmailAndPassword(String email, String password);
    
}
