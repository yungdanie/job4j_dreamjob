package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

    private CandidateStore() {
        store.put(1, new Candidate(1, "Иван", "Реальный пацан", LocalDateTime.now()));
        store.put(2, new Candidate(2, "Даниил", "Реальный пацан", LocalDateTime.now()));
        store.put(3, new Candidate(3, "Никита", "Реальный пацан", LocalDateTime.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.values();
    }
}
