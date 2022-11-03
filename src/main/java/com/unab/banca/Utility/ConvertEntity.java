package com.unab.banca.Utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertEntity {
    @Autowired
    ModelMapper modelMapper;
    public Object convert(Object user,Object dto){
        System.out.println(dto.getClass());
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
                dto = modelMapper.map(user, dto.getClass());
        return dto;
    }
}
