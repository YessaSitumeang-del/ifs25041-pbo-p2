package adapter.presenter;

import domain.entity.Activity;
import java.util.List;

public class ActivityPresenter {

    public String activity(Activity a) {
        return a.getId() + " | " + a.getTitle() + " | " + a.getDay() + " | " + a.getTime();
    }

    public String list(String title, List<Activity> activities) {
        StringBuilder sb = new StringBuilder(title);
        if (activities.isEmpty()) {
            sb.append("\n- Data kegiatan belum tersedia!");
        } else {
            for (Activity a : activities) {
                sb.append("\n").append(activity(a));
            }
        }
        return sb.toString();
    }

    public String searchResult(String keyword, List<Activity> activities) {
        StringBuilder sb = new StringBuilder("Hasil Pencarian: \"" + keyword + "\"");
        if (activities.isEmpty()) {
            sb.append("\n- Kegiatan tidak ditemukan!");
        } else {
            for (Activity a : activities) {
                sb.append("\n").append(activity(a));
            }
        }
        return sb.toString();
    }

    public String added(Activity a) { return "Berhasil menambah kegiatan: " + activity(a); }
}
