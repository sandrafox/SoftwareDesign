package ru.lisitsyna.softwaredesign.todolists.model;

import java.util.Objects;

public class Work {
    private int id;
    private String name;

    public Work() {
    }

    public Work(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Work work = (Work) o;
        return id == work.id &&
                name.equals(work.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
