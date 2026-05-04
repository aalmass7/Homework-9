package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.HeroMemento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Stores hero snapshots for the Chronomancer's Vault rewind mechanic.
 *
 * This class intentionally sits in a different package from {@link HeroMemento}
 * so it can only treat mementos as opaque values.
 */
public class Caretaker {

    private final Deque<HeroMemento> history = new ArrayDeque<>();

    /**
     * Saves a snapshot to the caretaker history.
     *
     * @param memento the snapshot to store
     */
    public void save(HeroMemento memento) {
        history.push(Objects.requireNonNull(memento, "memento must not be null"));
    }

    /**
     * Removes and returns the most recent snapshot.
     *
     * @return the latest stored snapshot, or null in the scaffold
     */
    public HeroMemento undo() {
        return history.pollFirst();
    }

    /**
     * Returns the most recent snapshot without removing it.
     *
     * @return the latest stored snapshot, or null in the scaffold
     */
    public HeroMemento peek() {
        return history.peekFirst();
    }

    /**
     * Reports how many snapshots are stored.
     *
     * @return the number of saved snapshots
     */
    public int size() {
        return history.size();
    }
}
