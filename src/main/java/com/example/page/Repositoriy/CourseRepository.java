package com.example.page.Repositoriy;

import com.example.page.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity,Integer> , PagingAndSortingRepository<CourseEntity,Integer> {
    List<CourseEntity> getAllByName(String name);

    List<CourseEntity> getAllByPrice(double price);

    List<CourseEntity> getAllByDuration(Integer fromDate);

    List<CourseEntity> getAllByPriceBetween(double fromPrice, double toPrice);

    List<CourseEntity> getAllByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);

    Page<CourseEntity> findAllByPrice(Pageable pageable, Double filter);

    Page<CourseEntity> findAllByPriceBetween(Pageable pageable, Double price, Double price1);
}
