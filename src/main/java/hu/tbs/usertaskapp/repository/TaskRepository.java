package hu.tbs.usertaskapp.repository;

import hu.tbs.usertaskapp.model.Task;
import hu.tbs.usertaskapp.model.User;
import hu.tbs.usertaskapp.util.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);

    List<Task> findByDateTimeBeforeAndStatus(LocalDateTime dateTime, TaskStatus status);
}
