package com.Spring_Crud.CrudOperation.Service.ServiceImpl;

import com.Spring_Crud.CrudOperation.Dto.StudentMarkDto;
import com.Spring_Crud.CrudOperation.Model.StudentMark;
import com.Spring_Crud.CrudOperation.Repository.MarkRepo;
import com.Spring_Crud.CrudOperation.Service.MarkService;
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
