package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.StudentMarkDto;
import com.springcrud.crudoperation.model.StudentMark;
import com.springcrud.crudoperation.repository.MarkRepo;
import com.springcrud.crudoperation.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarkServiceImpl  implements MarkService {

    @Autowired
    MarkRepo markRepo;

    @Override
    public void addStudentMarkById(StudentMarkDto studentMarkDto)
    {
        StudentMark studentMark=new StudentMark();
        studentMark.setEnglish(studentMarkDto.getEnglish());
        studentMark.setTamil(studentMarkDto.getTamil());
        markRepo.save(studentMark);
    }

    @Override
    public StudentMarkDto getMarkById(String id) {
        Optional<StudentMark>optional=markRepo.findById(id);
        StudentMark studentMark=optional.get();
        StudentMarkDto studentMarkDto=new StudentMarkDto();
        studentMarkDto.setId(studentMark.getId());
        studentMarkDto.setEnglish(studentMark.getEnglish());
        studentMarkDto.setTamil(studentMark.getTamil());
        return studentMarkDto;
    }

    @Override
    public String updateMarkById(String id, StudentMarkDto markDto) {
        return "";
    }
}
