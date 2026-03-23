package com.springme.starting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends JpaRepository<Task, Long>
{
    Page<Task> findByIsDone(Boolean isDone,  Pageable pageable);
    Page<Task> findByDescriptionContainingIgnoreCase(String search ,  Pageable pageable);
    Page<Task> findByDescriptionContainingIgnoreCaseAndIsDone(String search, Boolean isDone ,  Pageable pageable);
}
