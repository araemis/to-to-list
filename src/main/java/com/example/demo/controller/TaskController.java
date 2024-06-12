package com.example.demo.controller;

import static com.example.demo.enumeration.TaskController.first;
import static com.example.demo.enumeration.TaskController.next;
import static com.example.demo.enumeration.TaskController.previous;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Task;
import com.example.demo.model.Trash;
import com.example.demo.service.TaskService;
import com.example.demo.service.TrashService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskService taskRepository;

    @Autowired
    private TrashService trashRepository;

    private static int page = 1;

    @GetMapping("")
    public String index(Model model) {
        page = 1;
        List<Task> tasks = taskRepository.getPage(page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);
        model.addAttribute("h1", "HOME");
        return "home";
    }

    @PostMapping("addNewTask/{pathToFragment}/{arquiveName}/{fragmentName}")
    public String addNewTask(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @RequestBody String title) {
        page = 1;
        Task task = new Task();
        task.setTitle(title);
        taskRepository.create(task);
        List<Task> tasks = taskRepository.getPage(page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);
        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("changeIsClose/{pathToFragment}/{arquiveName}/{fragmentName}/{id}")
    public String changeIsClose(Model model, @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("id") Long id) {

        // Checked checked = new Checked(taskRepository.getTask(id));
        // chekedRepository.create(checked);
        // model.addAttribute("checked", checked);

        taskRepository.updateIsClose(id);
        List<Task> tasks = taskRepository.getPage(page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);

        // taskRepository.delete(id);

        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("removeTask/{pathToFragment}/{arquiveName}/{fragmentName}/{id}")
    public String removeTask(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("id") Long id) {
        Trash trash = new Trash(taskRepository.getTask(id));
        trashRepository.create(trash);
        model.addAttribute("trash", trash);

        taskRepository.delete(id);
        List<Task> tasks = taskRepository.getPage(page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);
        model.addAttribute("h1", "TRASH");

        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("removeTrash/{pathToFragment}/{arquiveName}/{fragmentName}/{id}")
    public String removeTrash(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("id") Long id) {
        trashRepository.delete(id);
        List<Trash> trashs = trashRepository.getPage(page);
        model.addAttribute("tasks", trashs);
        model.addAttribute("page", page);
        model.addAttribute("h1", "REMOVE");

        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("restoreTrash/{pathToFragment}/{arquiveName}/{fragmentName}/{id}")
    public String restoreTrash(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("id") Long id) {
        Trash trash = trashRepository.getTrash(id);
        Task task = new Task(trash.getId(), trash.getTitle(), trash.getIsClose());
        taskRepository.create(task);

        trashRepository.delete(id);
        List<Trash> trashs = trashRepository.getPage(page);
        model.addAttribute("tasks", trashs);
        model.addAttribute("page", page);
        model.addAttribute("h1", "REMOVE");

        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("trashPage")
    public String trashPage(Model model) {
        page = 1;
        List<Trash> trashs = trashRepository.getPage(page);
        model.addAttribute("tasks", trashs);
        model.addAttribute("page", page);
        model.addAttribute("h1", "TRASH");

        return "trash";
    }

    @GetMapping("pageTask/{pathToFragment}/{arquiveName}/{fragmentName}/{typePage}")
    public String pageTask(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("typePage") String typePage) {
        if (typePage.equals(next.toString())) {
            page++;
            List<Task> tasks = taskRepository.getPage(page);
            if (tasks.isEmpty()) {
                page--;
                tasks = taskRepository.getPage(page);
                model.addAttribute("tasks", tasks);
                model.addAttribute("page", page);
                return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
            }
        } else if (typePage.equals(previous.toString())) {
            if (page > 1)
                page--;
        } else if (typePage.equals(first.toString())) {
            page = 1;
        }
        List<Task> tasks = taskRepository.getPage(page);
        model.addAttribute("tasks", tasks);
        model.addAttribute("page", page);

        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @GetMapping("pageTrash/{pathToFragment}/{arquiveName}/{fragmentName}/{typePage}")
    public String pageTrash(Model model,
            @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName,
            @PathVariable("typePage") String typePage) {

        List<Trash> trashs = new ArrayList<Trash>();

        if (typePage.equals(next.toString())) {
            page++;
            trashs = trashRepository.getPage(page);
            if (trashs.isEmpty()) {
                page--;
                trashs = trashRepository.getPage(page);
                model.addAttribute("tasks", trashs);
                model.addAttribute("page", page);
                return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
            }
        } else if (typePage.equals(previous.toString())) {
            if (page > 1)
                page--;

        } else if (typePage.equals(first.toString())) {
            page = 1;
        }
        trashs = trashRepository.getPage(page);

        model.addAttribute("tasks", trashs);
        model.addAttribute("page", page);
        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

    @PostMapping("updateTask/{id}")
    public String updateTask(@Valid Task task) {
        taskRepository.update(task);
        return "redirect:/";
    }

    @GetMapping("updateTaskPage/{id}/{isClose}")
    public String updateTaskPage(Model model,
            @PathVariable("isClose") String isClose,
            @PathVariable("id") Long id) {
        model.addAttribute("task", taskRepository.getTask(id));
        model.addAttribute("h1", "UPDATE TASK");
        model.addAttribute("isClose", isClose.equals("true") ? true : false);
        return "updateTask";
    }

    @GetMapping("dropDown/{pathToFragment}/{arquiveName}/{fragmentName}")
    public String dropDown(Model model, @PathVariable("pathToFragment") String pathToFragment,
            @PathVariable("arquiveName") String arquiveName,
            @PathVariable("fragmentName") String fragmentName) {
        model.addAttribute("task", 1);
        return pathToFragment + "/" + arquiveName + " :: " + fragmentName;
    }

}
