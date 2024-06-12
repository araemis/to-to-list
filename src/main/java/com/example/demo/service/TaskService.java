package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public void create(Task task) {
        task.setIsClose(false);
        taskRepository.save(task);
    }

    public List<Task> getPage(int page) {
        List<Task> a = new ArrayList<>();
        List<Task> l = taskRepository.findAll();

        int total = 0;


        for (int i = l.size() - 1 - ((page - 1) * 5); i >= 0 && (total < 5); i--) {
            total++;
            a.add(l.get(i));
        }
        return a;

    }

    public void updateIsClose(Long id) {
        Task task = getTask(id);
        task.setIsClose(!task.getIsClose());
        update(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public void update(Task task) {
        if (getTask(task.getId()) != null) {
            taskRepository.save(task);
        }
    }

    public void delete(Long id) {
        Task task = new Task();
        if (taskRepository.existsById(id)) {
            task = getTask(id);
            taskRepository.delete(task);
        }
    }

    public Task getTask(Long id) {
        return taskRepository.getReferenceById(id);
    }

}
