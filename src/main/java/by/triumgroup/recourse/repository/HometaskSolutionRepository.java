package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.HometaskSolution;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HometaskSolutionRepository extends PagingAndSortingRepository<HometaskSolution, Long> {

    List<HometaskSolution> findByHometaskId(long id);

    List<HometaskSolution> findByHometaskId(long id, Pageable pageable);

    List<HometaskSolution> findByStudentId(long id);

    List<HometaskSolution> findByStudentId(long id, Pageable pageable);

}
