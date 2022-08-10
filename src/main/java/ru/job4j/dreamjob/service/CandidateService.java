package ru.job4j.dreamjob.service;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateStore;

import java.util.Collection;

@Service @ThreadSafe
public class CandidateService {
    private final CandidateStore store;

    public CandidateService(CandidateStore candidateStore) {
        this.store = candidateStore;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public Candidate findById(int id) {
        return store.getById(id);
    }
}
