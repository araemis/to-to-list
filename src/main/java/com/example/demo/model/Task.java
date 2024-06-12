package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Title cannot be empty!!!")
    private String title;
    @NotNull
    private Boolean isClose;

    public Task(@NotNull(message = "Title cannot be empty!!!") String title, Boolean isClose) {
        this.title = title;
        this.isClose = isClose;
    }

    public Task(Task task) {
        this.setId(task.getId());
        this.setTitle(task.getTitle());
        this.setIsClose(task.getIsClose());
    }
}
