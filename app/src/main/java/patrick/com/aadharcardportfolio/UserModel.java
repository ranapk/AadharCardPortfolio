package patrick.com.aadharcardportfolio;

/**
 * Created by rana on 7/2/18.
 */
/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import patrick.com.aadharcardportfolio.db.AppDatabase;
import patrick.com.aadharcardportfolio.db.DatabaseInitializer;
import patrick.com.aadharcardportfolio.db.User;


public class UserModel extends AndroidViewModel {

    public final LiveData<List<User >> users;

    private AppDatabase mDb;

    public UserModel(Application application, LiveData<List<User>> users) {
        super(application);
        this.users = users;
        createDb();

        // TODO: Assign books to the 'findBooksBorrowedByName' query.
        users = null;
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());

        // Populate it with initial data
        DatabaseInitializer.populateAsync(mDb);
    }
}