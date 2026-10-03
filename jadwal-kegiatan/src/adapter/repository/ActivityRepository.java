package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository implements IActivityRepository {
    private final List<Activity> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public int nextId() { return nextId++; }

    @Override
    public void save(Activity activity) { data.add(activity); }

    @Override
    public List<Activity> findAll() { return new ArrayList<>(data); }

    @Override
    public Activity findById(int id) {
        for (Activity activity : data) {
            if (activity.getId() == id) {
                return activity;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) { return data.removeIf(a -> a.getId() == id); }
}
