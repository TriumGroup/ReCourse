package by.triumgroup.recourse.repository;


import by.triumgroup.recourse.entity.StudentReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReportRepository extends PagingAndSortingRepository<StudentReport, Long> {

    List<StudentReport> findByTeacherId(long id);

    List<StudentReport> findByTeacherId(long id, Pageable pageable);

    List<StudentReport> findByStudentId(long id);

    List<StudentReport> findByStudentId(long id, Pageable pageable);

    List<StudentReport> findByCourseId(long id);

    List<StudentReport> findByCourseId(long id, Pageable pageable);

}
