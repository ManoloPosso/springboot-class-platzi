package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Los 2 parametros que se reciben son:
//La entidad que se desea mapear, y el tipo de dato del Id de la entidad
//La anotacion Repository es para poder inyectar esta interfaz
@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    //Agregamos en Query, la consulta JPQL
    //Es una consulta a partir de objetos, NO de Tablas
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);


    List<User> findByName(String name);

    Optional<User> findByNameAndEmail(String name, String email);

}
