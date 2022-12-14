package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository @ThreadSafe
public class CandidateStore {
    private static final AtomicInteger ATOMIC = new AtomicInteger(0);
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

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
