package ru.lisitsyna.softwaredesign.todolists.dao;

import ru.lisitsyna.softwaredesign.todolists.model.Work;
import ru.lisitsyna.softwaredesign.todolists.model.WorkList;

import java.util.List;

public interface WorkDao {
    int addWorkList(WorkList workList);

    int addWork(Work work, int workListId);

    List<WorkList> getWorkLists();

    int removeWork(int workId, int workListId);

    int removeWorkList(int workListId);
}
