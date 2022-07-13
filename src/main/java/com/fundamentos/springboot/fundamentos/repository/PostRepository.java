package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Los 2 parametros que se reciben son:
//La entidad que se desea mapear, y el tipo de dato del Id de la entidad
//La anotacion Repository es para poder inyectar esta interfaz
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
}
