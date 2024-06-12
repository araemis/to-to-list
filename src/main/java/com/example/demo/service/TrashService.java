package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Trash;
import com.example.demo.repository.TrashRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TrashService {
    @Autowired
    TrashRepository trashRepository;

    public void create(Trash trash) {
        trash.setIsClose(false);
        trashRepository.save(trash);
    }

    public List<Trash> getPage(int page) {
        List<Trash> a = new ArrayList<>();
        List<Trash> l = trashRepository.findAll();
        int total = 0;

        for (int i = page - 1; i <= l.size() - 1 - ((page - 1) * 5) && total < 5; i++) {
            total++;
            a.add(l.get(i));
        }

        return a;

    }

    public void updateIsClose(Long id) {
        Trash trash = getTrash(id);
        trash.setIsClose(!trash.getIsClose());
        update(trash);
    }

    public List<Trash> getAll() {
        return trashRepository.findAll();
    }

    public void update(Trash trash) {
        if (getTrash(trash.getId()) != null) {
            trashRepository.save(trash);
        }
    }

    public void delete(Long id) {
        Trash trash = new Trash();
        if (trashRepository.existsById(id)) {
            trash = getTrash(id);
            trashRepository.delete(trash);
        }
    }

    public Trash getTrash(Long id) {
        return trashRepository.getReferenceById(id);
    }

}
