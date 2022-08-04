package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> store = new ConcurrentHashMap<>();

    private CandidateStore() {
        store.put(1, new Candidate(1, "Иван", "Реальный пацан", new Date(1659618258000L)));
        store.put(2, new Candidate(2, "Даниил", "Реальный пацан", new Date(1659618258000L)));
        store.put(3, new Candidate(3, "Никита", "Реальный пацан", new Date(1659618258000L)));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return store.values();
    }
}
