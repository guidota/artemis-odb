package com.artemis;

import java.util.*;

public final class ChangeRegistry extends BaseSystem {
    private final Map<Integer, Set<Class<? extends Component>>> toRemove = new HashMap<>();

    private final Map<Integer, Set<Component>> toMark = new HashMap<>();

    private final Map<Class<? extends Component>, Set<Integer>> components = new HashMap<>();

    protected void initialize() {
        E._changeRegistry = this;
    }

    public void processSystem() {
        E._changeRegistry = this;
        toRemove.clear();
        toMark.clear();
        components.clear();
    }

    void remove(int entityId, Class<? extends Component> componentClass) {
        this.toRemove.computeIfAbsent(entityId, id -> new HashSet<>()).add(componentClass);
        this.components.computeIfAbsent(componentClass, clazz -> new HashSet<>()).add(entityId);
    }

    public Map<Integer, Set<Class<? extends Component>>> getRemoved() {
        return this.toRemove;
    }

    void mark(int entityId, Component component) {
        this.toMark.computeIfAbsent(entityId, id -> new HashSet<>()).add(component);
        this.components.computeIfAbsent(component.getClass(), clazz -> new HashSet<>()).add(entityId);
    }

    public Map<Integer, Set<Component>> getMarked() {
        return this.toMark;
    }

    public Optional<Set<Integer>> getWhoChange(Class<? extends Component> componentClass) {
        return Optional.ofNullable(this.components.get(componentClass));
    }
}
