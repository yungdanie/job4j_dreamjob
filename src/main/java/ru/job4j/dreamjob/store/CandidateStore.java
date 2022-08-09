package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {

    private static final AtomicInteger ATOMIC = new AtomicInteger(0);
    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

    private CandidateStore() {
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.values();
    }

    public void add(Candidate candidate) {
        int curr;
        int incrCurr;
        do {
            curr = ATOMIC.get();
            incrCurr = curr + 1;
        } while (!ATOMIC.compareAndSet(curr, incrCurr));
        candidate.setId(incrCurr);
        candidate.setCreated(LocalDateTime.now());
        store.putIfAbsent(incrCurr, candidate);
    }

    public void update(Candidate candidate) {
        Candidate old = store.get(candidate.getId());
        old.setName(candidate.getName());
        old.setDescription(candidate.getDescription());
    }

    public Candidate getById(int id) {
        return store.get(id);
    }
}
