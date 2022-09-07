package com.example.sample.repository;

import com.example.sample.model.entity.LoginEntity;
import com.example.sample.model.request.LoginDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LoginRepository extends CrudRepository<LoginEntity,Integer> {

    @Override
    ArrayList<LoginEntity> findAll();
}
