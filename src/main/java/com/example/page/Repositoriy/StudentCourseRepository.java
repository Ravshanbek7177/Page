package com.example.page.Repositoriy;


import com.example.page.dto.StudentCourseFilter;
import com.example.page.entity.StudentCourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> , PagingAndSortingRepository<StudentCourseEntity,Integer> {
//    @Query(value = "select s.name,s.surname from StudentCourseEntity as" +
//            "se inner join StudentEntity as" +
//            " s on s.id = se.student_id where se.mark =:mark ")
//    List<StudentCourseEntity> getAllByMark(@Param("mark") Integer mark);

    List<StudentCourseEntity> getByCreatedDateIsAfter(LocalDate fromDate);

    List<StudentCourseEntity> getAllByStudentId(Integer id);

    List<StudentCourseEntity> getAllByCourseId(Integer id);

    List<StudentCourseEntity> getAllByStudentIdAndCreatedDate(Integer id, LocalDate fromDate);

    List<StudentCourseEntity> getAllByStudentIdAndCreatedDateBetween(Integer integer, LocalDate fromDate, LocalDate dateTo);

    List<StudentCourseEntity> getAllByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer id, Integer courseId);

    List<StudentCourseEntity> findTop1ByStudentIdOrderByMarkAsc(Integer student_id);

    List<StudentCourseEntity> findTop3ByStudentIdOrderByMarkDesc(Integer student_id);

    List<StudentCourseEntity> findTop1ByStudentIdOrderByCreatedDate(Integer student_id);

    List<StudentCourseEntity> findTop1ByStudentIdAndCourseIdOrderByCreatedDate(Integer courseId, Integer student_id);

    List<StudentCourseEntity> findTop1ByStudentIdAndCourseIdOrderByMarkDesc(Integer courseId, Integer student_id);

    @Query(value = "select avg(s.mark) from studentcourse as s where student_id=:sid ", nativeQuery = true)
    Double orderAvgByMark(@Param("sid") Integer id);

    @Query(value = "select avg(s.mark) from studentcourse as s where student_id = :sid and course_id=:cid ", nativeQuery = true)
    Double avgByMarkStudentAndCourseId(@Param("sid") Integer id, @Param("cid") Integer cid);

    @Query(value = "select s.mark from studentcourse as s where course_id=:sid and mark > :mark", nativeQuery = true)
    List<Integer> orderMaxByMarkStudentId(@Param("sid") Integer id, @Param("mark") Integer mark);

    @Query(value = "select max(s.mark) from studentcourse as s where course_id=:sid ", nativeQuery = true)
    Integer orderMaxByMarkCourseId(@Param("sid") Integer id);

    @Query(value = "select avg(s.mark) from studentcourse as s where course_id=:sid ", nativeQuery = true)
    Integer avgByMarkStudentAndCourseId(@Param("sid") Integer id);

    @Query(value = "select count(s.mark) from studentcourse as s where course_id=:sid ", nativeQuery = true)
    Integer countByMarkStudentAndCourseId(@Param("sid") Integer id);

    List<StudentCourseEntity> getAllByMark(Integer mark);

    Page<StudentCourseEntity> findAllByStudentId(Integer studentId, Pageable pageable);

    Page<StudentCourseEntity> findAllByCourseId(Integer courseId, Pageable pageable);


//    ------------------------------------------------------------------------------------------------------------------
//    @Transactional
//    @Modifying
//    @Query("update StudentCourseEntity set visible = :vis where id = :sid")
//    Integer changeVisibility(@Param("sid") Integer id,@Param("visible") Boolean vis);
//    -------mapper va Constructor yaratish kerak:----------------------------------------------------------------------
//    @Query("SELECT new StudentCourseEntity (id,name,surname) from StudentCourseEntity ")
//    List<String> findName();
//    --------------------------------------------mapper yaratish kere--------------------------------------------------
//    @Query("SELECT new com.example.mapper.StudentMapper(id,name, phone) FROM StudentEntity ")
//    List<StudentMapper> findByName5();
//    ------------------------------------------------------------------------------------------------------------------
//  123 yoki 345 yoki 333 yoki shular
//    List<StudentCourseEntity> findAllByPhoneIn(List<String> phoneList);
//    ------------------------------------------------------------------------------------------------------------------
}
