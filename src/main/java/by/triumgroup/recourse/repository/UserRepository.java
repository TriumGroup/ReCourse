package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

}
