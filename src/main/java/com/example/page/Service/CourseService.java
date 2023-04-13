package com.example.page.Service;

import com.example.page.Repositoriy.CourseRepository;
import com.example.page.dto.CourseDTO;
import com.example.page.dto.CourseFilter;
import com.example.page.entity.CourseEntity;
import com.example.page.exp.AppBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;
    public CourseDTO create(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDate.now());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        if (dto.getName().isBlank()) {
            throw new AppBadRequestException("Not found name:");
        }
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;

    }

    public CourseEntity getById(Integer id) {
        Optional<CourseEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> entityIterable = repository.findAll();
        List<CourseDTO> list = new LinkedList<>();
        entityIterable.forEach(studentEntity -> {
            CourseDTO dto = new CourseDTO();
            dto.setName(studentEntity.getName());
            dto.setCreatedDate(studentEntity.getCreatedDate());
            dto.setDuration(studentEntity.getDuration());
            dto.setPrice(studentEntity.getPrice());
            dto.setId(studentEntity.getId());
            list.add(dto);
        });
        return list;
    }

    public Boolean update(Integer id, CourseDTO courseDTO) {
        CourseEntity entity = getById(id);
        entity.setName(courseDTO.getName());
        entity.setPrice(courseDTO.getPrice());
        entity.setDuration(courseDTO.getDuration());
        entity.setCreatedDate(LocalDate.now());
        repository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        CourseEntity entity = getById(id);
        repository.delete(entity);
        return true;
    }

    public List<CourseDTO> getByName(String name) {
        List<CourseEntity> entityList = repository.getAllByName(name);
        return getListCourseDTO(entityList);
    }

    public List<CourseDTO> getPrice(double price) {
        List<CourseEntity> entityList = repository.getAllByPrice(price);
        return getListCourseDTO(entityList);
    }

    public List<CourseDTO> getDuration(Integer duration) {
        List<CourseEntity> entityList = repository.getAllByDuration(duration);
        return getListCourseDTO(entityList);
    }

    public List<CourseDTO> getPriceFromAndTo(double fromPrice, double toPrice) {
        List<CourseEntity> entityList = repository.getAllByPriceBetween(fromPrice, toPrice);
        return getListCourseDTO(entityList);

    }

    public List<CourseDTO> getTimeFromAndTo(LocalDate fromDate, LocalDate toDate) {
        List<CourseEntity> entityList = repository.getAllByCreatedDateBetween(fromDate, toDate);
        return getListCourseDTO(entityList);
    }
    private List<CourseDTO> getListCourseDTO(List<CourseEntity> entityList){
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity : entityList) {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public Page<CourseDTO> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1,size,sort);

        Page<CourseEntity> pageObj = repository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<CourseEntity> courseEntitieList = pageObj.getContent();
        List<CourseDTO> list = new LinkedList<>();

        for(CourseEntity entity : courseEntitieList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
      Page<CourseDTO> response = new PageImpl<>(list,pageable,totalCount);

        return response;
    }

    public Page<CourseDTO> paginationWithPrice(Double filter, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(page - 1, size ,sort);
        Page<CourseEntity> pajObj = repository.findAllByPrice(pageable , filter);

        Long totalCount = pajObj.getTotalElements();
        List<CourseEntity> entityList = pajObj.getContent();
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity:entityList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }

   Page<CourseDTO> courseDTOS = new PageImpl<>(list,pageable,totalCount);

        return courseDTOS;
    }

    public Page<CourseDTO> paginationWithPrice2(Double price, Double price1, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(page - 1, size ,sort);
        Page<CourseEntity> pajObj = repository.findAllByPriceBetween(pageable ,price , price1);

        Long totalCount = pajObj.getTotalElements();

        List<CourseEntity> entityList = pajObj.getContent();
        List<CourseDTO> list = new LinkedList<>();
        for (CourseEntity entity:entityList){
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }

        Page<CourseDTO> courseDTOS = new PageImpl<>(list,pageable,totalCount);

        return courseDTOS;
    }
}
