package domain.repository;

import domain.entity.Activity;
import java.util.List;

public interface IActivityRepository {
    int nextId();
    void save(Activity activity);
    List<Activity> findAll();
    Activity findById(int id);
    boolean deleteById(int id);
}
