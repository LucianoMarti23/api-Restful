package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;

public class PacienteAlreadyExist extends CustomHandler{
    public PacienteAlreadyExist(final String message) {
        super(message);
    }







    @Override
    public String toString() {
        return super.getMessage() + " " + super.getDate();
    }

}
