package com.mariage.projet.zamak.Mariage_projet_joachim.Services;

import java.util.List;

public interface AbstractService<T>{
    Integer save(T dto);

    List<T> findAll();

    T findById (Integer id);

    void delete(Integer id);

}
