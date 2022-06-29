package com.fundamentos.springboot.fundamentos.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    //anotacion que sirve para aceptar solicitudes dentro del metodo via HTTP
    @RequestMapping
    //Responder un "cuerpo" a nivel del servicio
    @ResponseBody

    public ResponseEntity<String> function(){

        return new ResponseEntity<>("Hola desde el controlador", HttpStatus.OK);

    }

}
