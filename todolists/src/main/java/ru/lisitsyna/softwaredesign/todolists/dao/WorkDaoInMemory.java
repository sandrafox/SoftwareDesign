package ru.lisitsyna.softwaredesign.todolists.dao;

import org.springframework.stereotype.Component;
import ru.lisitsyna.softwaredesign.todolists.model.Work;
import ru.lisitsyna.softwaredesign.todolists.model.WorkList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class WorkDaoInMemory implements WorkDao {
    List<WorkList> workLists = new CopyOnWriteArrayList<>();
    @Override
    public int addWorkList(WorkList workList) {
        workList.setId(workLists.size());
        workLists.add(workList);
        return 0;
    }

    @Override
    public int addWork(Work work, int workListId) {
        WorkList w = workLists.get(workListId);
        w.addWork(work);
        //workList.addWork(work);
        return 0;
    }

    @Override
    public List<WorkList> getWorkLists() {
        return List.copyOf(workLists);
    }

    @Override
    public int removeWork(int workId, int workListId) {
        WorkList w = workLists.get(workListId);
        w.removeWork(workId);
        return 0;
    }

    @Override
    public int removeWorkList(int workListId) {
        workLists.remove(workListId);
        return 0;
    }
}
