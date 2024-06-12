package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "trash")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Trash {
    @Id
    private Long id;
    @NotNull(message = "Title cannot be empty!!!")
    private String title;
    @NotNull
    private Boolean isClose;

    public Trash(@NotNull(message = "Title cannot be empty!!!") String title, Boolean isClose) {
        this.title = title;
        this.isClose = isClose;
    }

    public Trash(Trash trash) {
        this.setId(trash.getId());
        this.setTitle(trash.getTitle());
        this.setIsClose(trash.getIsClose());
    }
    public Trash(Task task) {
        this.setId(task.getId());
        this.setTitle(task.getTitle());
        this.setIsClose(task.getIsClose());
    }
}
