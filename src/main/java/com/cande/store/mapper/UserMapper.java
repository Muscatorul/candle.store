package com.cande.store.mapper;

import com.cande.store.dto.UserDto;
import com.cande.store.entity.Role;
import com.cande.store.entity.ShoppingCart;
import com.cande.store.entity.User;
import com.cande.store.repository.RolleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolleRepository rolleRepository;
    public User mapp(UserDto userDto){
        User user=new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        String passwordEncoded = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(passwordEncoded);
        user.setAddress(userDto.getAddress());
        Optional<Role>role= rolleRepository.findByName(userDto.getUserRole());
        Role role1=new Role();
        if(role.isEmpty()){
           role1 = saveRole(userDto);
        user.setRoles(List.of(role1));
        }
        else {
            user.setRoles(List.of(role.get()));
        }
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);

        return user;
    }
    private Role saveRole(UserDto userDto){
       Role role= new Role();
       role.setName(userDto.getUserRole());
       return rolleRepository.save(role);
    }
}
