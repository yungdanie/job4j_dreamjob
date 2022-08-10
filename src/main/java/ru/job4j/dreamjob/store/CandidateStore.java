package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@ThreadSafe
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
        candidate.setId(ATOMIC.incrementAndGet());
        candidate.setCreated(LocalDateTime.now());
        store.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        store.replace(candidate.getId(), candidate);
    }

    public Candidate getById(int id) {
        return store.get(id);
    }
}
