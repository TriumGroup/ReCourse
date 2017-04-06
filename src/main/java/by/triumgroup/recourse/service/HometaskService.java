package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.Hometask;
import org.springframework.data.domain.Pageable;

public interface HometaskService extends CrudService<Hometask, Integer> {

    Hometask findByLessonId(Integer id, Pageable pageable);

}
