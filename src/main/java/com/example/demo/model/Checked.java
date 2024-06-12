package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "checked")
@Table(name = "checked")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Checked {
    @Id
    private Long id;
    @NotNull(message = "Title cannot be empty!!!")
    private String title;
    @NotNull
    private Boolean isClose;

    public Checked(@NotNull(message = "Title cannot be empty!!!") String title, Boolean isClose) {
        this.title = title;
        this.isClose = isClose;
    }

    public Checked(Task task) {
        this.setId(task.getId());
        this.setTitle(task.getTitle());
        this.setIsClose(task.getIsClose());
    }
}
