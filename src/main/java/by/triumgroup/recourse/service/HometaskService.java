package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.Hometask;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HometaskService extends CrudService<Hometask, Integer> {

    List<Hometask> findByLessonId(Integer id, Pageable pageable);

}
