package com.example.demo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@Getter
public class CustomHandler extends RuntimeException{
    /**
     * -- GETTER --
     *  Gets the date associated with the exception.
     * <p>
     *
     * -- SETTER --
     *  Sets the date associated with the exception.
     *
     */

    private Date date;

    @Override
    public String toString() {
        return "CustomHandler{" +
                "date=" + date +
                "} " + super.toString();
    }

    public CustomHandler(final String message) {
        super(message);


    }




    /**
     * Constructs a new CustomedHandler object with the given error message.
     *
     * @param message The error message associated with the exception.
     */


    /**
     * Generates a string representation of the CustomedHandler object.
     *
     * @return A string representation containing the associated date.
     */


}
