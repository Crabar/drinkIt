package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
