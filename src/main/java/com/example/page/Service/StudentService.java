package com.example.page.Service;

import com.example.page.Repositoriy.StudentRepository;
import com.example.page.dto.StudentDTO;
import com.example.page.dto.StudentFilter;
import com.example.page.entity.StudentEntity;
import com.example.page.exp.AppBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDate.now());
        entity.setLevel(dto.getLevel());
        if (dto.getName().isBlank()) {
            throw new AppBadRequestException("Not found name:");
        }
        if (dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Not found surname: ");
        }
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;

    }

    public StudentEntity getById(Integer id) {
        Optional<StudentEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> entityIterable = repository.findAll();
        List<StudentDTO> list = new LinkedList<>();
        entityIterable.forEach(studentEntity -> {
            StudentDTO dto = new StudentDTO();
            dto.setName(studentEntity.getName());
            dto.setSurname(studentEntity.getSurname());
            dto.setAge(studentEntity.getAge());
            dto.setGender(studentEntity.getGender());
            dto.setLevel(studentEntity.getLevel());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public Boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = getById(id);
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        entity.setAge(studentDTO.getAge());
        entity.setGender(studentDTO.getGender());
        entity.setLevel(studentDTO.getLevel());
        entity.setCreatedDate(LocalDate.now());
        repository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        StudentEntity entity = getById(id);
        repository.delete(entity);
        return true;
    }

    public List<StudentDTO> getName(String name) {
        List<StudentEntity> entityList = repository.getAllByName(name);
        return getByStudentDTOList(entityList);
    }

    public List<StudentDTO> getSurname(String surname) {
        List<StudentEntity> entityList = repository.getAllBySurname(surname);
       return getByStudentDTOList(entityList);
    }

    public List<StudentDTO> getLevel(Integer level) {
        List<StudentEntity> entityList = repository.getAllByLevel(level);
       return getByStudentDTOList(entityList);

    }

    public List<StudentDTO> getAge(Integer age) {
        List<StudentEntity> entityList = repository.getAllByAge(age);
        return getByStudentDTOList(entityList);
    }

    public List<StudentDTO> getByGender(String gender) {
        List<StudentEntity> entityList = repository.getAllByGender(gender);
        return getByStudentDTOList(entityList);
    }

    public List<StudentDTO> getTimeFrom(LocalDate fromDate) {
        List<StudentEntity> entityList = repository.getByCreatedDateIsAfter(fromDate);
        return getByStudentDTOList(entityList);
    }

    public List<StudentDTO> getTimeFromAndTo(LocalDate fromDate, LocalDate toDate) {
        List<StudentEntity> entityList = repository.getByCreatedDateBetween(fromDate, toDate);
        return getByStudentDTOList(entityList);
    }
    private List<StudentDTO> getByStudentDTOList(List<StudentEntity> entityList){
        List<StudentDTO> list = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setLevel(entity.getLevel());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public Page<StudentDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size ,sort);
        Page<StudentEntity> pajObj = repository.findAll(pageable);
        Long totalCount = pajObj.getTotalElements();

        List<StudentEntity> studentEntities = pajObj.getContent();
        List<StudentDTO> studentDTOS = new LinkedList<>();
        for (StudentEntity entity : studentEntities){
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setLevel(entity.getLevel());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOS.add(dto);
        }

        Page<StudentDTO> response = new PageImpl<>(studentDTOS,pageable,totalCount);

        return response;
    }

    public Page<StudentDTO> paginationLevel(Integer filter, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size ,sort);
        Page<StudentEntity> pajObj = repository.findAllByLevel(filter ,pageable);
        Long totalCount = pajObj.getTotalElements();

        List<StudentEntity> studentEntities = pajObj.getContent();
        List<StudentDTO> studentDTOS = new LinkedList<>();
        for (StudentEntity entity : studentEntities){
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setLevel(entity.getLevel());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOS.add(dto);
        }

        Page<StudentDTO> response = new PageImpl<>(studentDTOS,pageable,totalCount);

        return response;
    }
}
