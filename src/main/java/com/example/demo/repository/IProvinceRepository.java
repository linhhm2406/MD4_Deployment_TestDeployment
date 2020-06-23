package com.example.demo.repository;

import com.example.demo.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends PagingAndSortingRepository<Province,Long>, CrudRepository<Province,Long> {
}
