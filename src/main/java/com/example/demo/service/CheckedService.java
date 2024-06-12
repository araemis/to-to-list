package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Checked;
import com.example.demo.repository.CheckedRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CheckedService {
    @Autowired
    CheckedRepository checkedRepository;

    public void create(Checked checked) {
        checkedRepository.save(checked);
    }

    public List<Checked> getPage(int page) {
        List<Checked> a = new ArrayList<>();
        List<Checked> l = checkedRepository.findAll();
        int total = 0;

        for (int i = l.size() - 1 - ((page - 1) * 5); i >= 0 && (total < 5); i--) {
            total++;
            a.add(l.get(i));
        }

        return a;

    }

    public void updateIsClose(Long id) {
        Checked checked = getChecked(id);
        checked.setIsClose(!checked.getIsClose());
        update(checked);
    }

    public List<Checked> getAll() {
        return checkedRepository.findAll();
    }

    public void update(Checked checked) {
        if (getChecked(checked.getId()) != null) {
            checkedRepository.save(checked);
        }
    }

    public void delete(Long id) {
        Checked checked = new Checked();
        if (checkedRepository.existsById(id)) {
            checked = getChecked(id);
            checkedRepository.delete(checked);
        }
    }

    public Checked getChecked(Long id) {
        return checkedRepository.getReferenceById(id);
    }

}
