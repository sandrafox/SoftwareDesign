package ru.lisitsyna.softwaredesign.todolists.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WorkList {
    private int id;
    private String name;
    private List<Work> workList;

    public WorkList() {
        workList = new CopyOnWriteArrayList<>();
    }

    public WorkList(int id, String name, List<Work> workList) {
        this.id = id;
        this.name = name;
        this.workList = workList;
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

    public List<Work> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Work> workList) {
        this.workList = workList;
    }

    public void addWork(Work work) {
        work.setId(workList.size());
        workList.add(work);
    }

    public void removeWork(int workId) {
        workList.remove(workId);
    }
}
