package com.helpmeproductions.willus08.bankapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.helpmeproductions.willus08.bankapp.model.*;

@Database(entities = {Customer.class,DataStorage.class}, version = 1, exportSchema = false)
@TypeConverters({CustomerDao.Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract CustomerDao customerModel();
    public abstract DataStorageDao dataModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getPersistantDatabase(Context context){
        if (INSTANCE == null) {
            Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"appDatabase")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

}
