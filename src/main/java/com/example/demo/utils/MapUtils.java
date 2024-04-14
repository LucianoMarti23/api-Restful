package com.example.demo.utils;


import org.modelmapper.ModelMapper;

import java.util.List;

import java.util.stream.Collectors;

public class MapUtils {

    private static final ModelMapper modelMapper = new ModelMapper();





    public static <T, U> List<U> getAll(List<T> lista, Class<U> objetoClase) {



            return lista.stream()
                    .map(entity -> modelMapper.map(entity, objetoClase))
                    .collect(Collectors.toList());


    }
    public static <T, U> U mapEntityToDTO(T entity, Class<U> entityDtoClass) {
        //

        return modelMapper.map(entity, entityDtoClass);
    }


    public static <T,U> U mapDtoToEntity(T dto , Class<U> entityClass){

        return modelMapper.map(dto,entityClass);

    }




}