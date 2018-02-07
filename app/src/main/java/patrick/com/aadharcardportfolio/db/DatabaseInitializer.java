package patrick.com.aadharcardportfolio.db;

import android.os.AsyncTask;

/**
 * Created by rana on 7/2/18.
 */

public class DatabaseInitializer {
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
    public static void populateAsync(final AppDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
}
