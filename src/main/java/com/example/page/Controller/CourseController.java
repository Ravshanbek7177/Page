package com.example.page.Controller;


import com.example.page.Service.CourseService;
import com.example.page.dto.CourseDTO;
import com.example.page.dto.CourseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService service;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        CourseDTO courseDTO = service.create(dto);
        return ResponseEntity.ok(courseDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(service.update(id, courseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
    @GetMapping("/getName/{name}")
    public ResponseEntity<List<CourseDTO>> getName(@PathVariable("name") String name) {
        return ResponseEntity.ok(service.getByName(name));
    }
    @GetMapping("/getPrice/{price}")
    public ResponseEntity<List<CourseDTO>> getAge(@PathVariable("price") double price) {
        return ResponseEntity.ok(service.getPrice(price));
    }
    @GetMapping("/durationDate")
    public ResponseEntity<List<CourseDTO>> getByFromDate(@RequestParam("duration") Integer duration) {
        List<CourseDTO> list = service.getDuration(duration);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/price")
    public ResponseEntity<List<CourseDTO>> getByListFromAndTo(@RequestParam("priceFrom") double fromPrice,
                                                               @RequestParam("priceTo") double toPrice) {
        List<CourseDTO> list = service.getPriceFromAndTo(fromPrice, toPrice);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/date")
    public ResponseEntity<List<CourseDTO>> getByListFromAndTo(@RequestParam("dateFrom") LocalDate fromDate,
                                                               @RequestParam("dateTo") LocalDate toDate) {
        List<CourseDTO> list = service.getTimeFromAndTo(fromDate, toDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/pageneshin")
    public  ResponseEntity<?> getPage(@RequestParam(value = "page", defaultValue = "1") int page ,
                                      @RequestParam(value = "size", defaultValue = "30") int size){
        Page<CourseDTO> response = service.pagination(page,size);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/getList")
    public ResponseEntity<Page<CourseDTO>> getPrice(@RequestParam(value = "page") int page ,
                                       @RequestParam(value = "size") int size ,
                                       @RequestBody CourseFilter filter){
        Page<CourseDTO> courseDTOS = service.paginationWithPrice(filter.getFromPrice(),page,size);
        return ResponseEntity.ok(courseDTOS);

    }
    @PostMapping("/getList2")
    public ResponseEntity<Page<CourseDTO>> getPrice2(@RequestParam(value = "page") int page ,
                                                    @RequestParam(value = "size") int size ,
                                                     @RequestBody CourseFilter filter2){
        Page<CourseDTO> courseDTOS = service.paginationWithPrice2(filter2.getFromPrice(), filter2.getToPrice(), page,size);
        return ResponseEntity.ok(courseDTOS);

    }




}
