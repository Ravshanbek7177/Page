package com.example.page.Repositoriy;

import com.example.page.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> , PagingAndSortingRepository<StudentEntity ,Integer> {

    List<StudentEntity> getAllByName(String name);

    List<StudentEntity> getAllBySurname(String name);

    List<StudentEntity> getAllByLevel(Integer level);

    List<StudentEntity> getAllByAge(Integer age);

    List<StudentEntity> getAllByGender(String gender);

    List<StudentEntity> getByCreatedDateIsAfter(LocalDate fromDate);

    List<StudentEntity> getByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);

    Page<StudentEntity> findAllByLevel(Integer filter, Pageable pageable);
}
