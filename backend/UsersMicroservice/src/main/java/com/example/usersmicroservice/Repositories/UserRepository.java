package com.example.usersmicroservice.Repositories;

import com.example.usersmicroservice.Tables.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@org.socialsignin.spring.data.dynamodb.repository.EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
