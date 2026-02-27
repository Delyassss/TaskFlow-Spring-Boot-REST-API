package com.springme.starting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>
{
    List<Task> findByIsDone(boolean isDone);
    List<Task> findByDescriptionContainingIgnoreCase(String search);
    List<Task> findByDescriptionContainingIgnoreCaseAndIsDone(String search, boolean isDone);
}
