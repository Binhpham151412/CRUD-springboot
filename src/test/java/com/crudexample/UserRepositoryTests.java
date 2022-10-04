package com.crudexample;

import com.crudexample.user.User;
import com.crudexample.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repository;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("thusuong.150398@gmail.com");
        user.setFirstName("thu");
        user.setLastName("suong");
        user.setPassword("suong123456");
        User saveUser = repository.save(user);

        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }

//    @Test
//    public void testListAll(){
//        Iterable<User> users = repository.findAll();
//        Assertions.assertThat(users).hasSameSizeAs(0);
//
//        for (User user:users){
//            System.out.println(user);
//        }
//
//    }

}
