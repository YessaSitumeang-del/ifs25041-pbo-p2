package usecase;

import domain.entity.Activity;
import domain.entity.SortOption;
import domain.repository.IActivityRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ActivityUseCase {
    private static final List<String> DAY_ORDER =
            Arrays.asList("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");

    private final IActivityRepository repository;

    public ActivityUseCase(IActivityRepository repository) {
        this.repository = repository;
    }

    public Activity add(String title, String day, String time) {
        Activity activity = new Activity(repository.nextId(), title, day, time);
        repository.save(activity);
        return activity;
    }

    public List<Activity> getAll() {
        return repository.findAll();
    }

    /**
     * Ubah parsial: field yang kosong (atau null) tidak diubah.
     * Mengembalikan false jika kegiatan dengan ID tersebut tidak ada.
     */
    public boolean update(int id, String title, String day, String time) {
        Activity activity = repository.findById(id);
        if (activity == null) {
            return false;
        }
        if (title != null && !title.isEmpty()) {
            activity.setTitle(title);
        }
        if (day != null && !day.isEmpty()) {
            activity.setDay(day);
        }
        if (time != null && !time.isEmpty()) {
            activity.setTime(time);
        }
        return true;
    }

    public List<Activity> search(String keyword) {
        List<Activity> result = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Activity activity : repository.findAll()) {
            if (activity.getTitle().toLowerCase().contains(key)) {
                result.add(activity);
            }
        }
        return result;
    }

    public List<Activity> sort(SortOption option) {
        List<Activity> sorted = new ArrayList<>(repository.findAll());
        Comparator<Activity> byTime = Comparator.comparing(Activity::getTime);
        switch (option) {
            case DAY_ASC:
                // Urut hari (Senin -> Minggu); hari yang sama diurutkan berdasarkan waktu.
                sorted.sort(Comparator.comparingInt((Activity a) -> dayIndex(a.getDay())).thenComparing(byTime));
                break;
            case TIME_ASC:
                sorted.sort(byTime);
                break;
            case TITLE_ASC:
                sorted.sort(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER));
                break;
            case TITLE_DESC:
                sorted.sort(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());
                break;
        }
        return sorted;
    }

    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    // Hari yang tidak dikenal ditaruh paling akhir.
    private int dayIndex(String day) {
        int index = DAY_ORDER.indexOf(day.trim().toLowerCase());
        return index < 0 ? DAY_ORDER.size() : index;
    }
}
