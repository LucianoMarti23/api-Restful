package com.example.demo.exceptions;

public class PacienteNotFound extends CustomHandler {

    public PacienteNotFound(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage() + " " + super.getDate();
    }

}
