package com.example.demo.service.province;

import com.example.demo.model.Province;
import com.example.demo.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    IProvinceRepository provinceRepository;

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void save(Province model) {
        provinceRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        provinceRepository.deleteById(id);
    }


}
