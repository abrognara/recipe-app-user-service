package com.brognara.recipeappuserservice;

import com.brognara.recipeappuserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
