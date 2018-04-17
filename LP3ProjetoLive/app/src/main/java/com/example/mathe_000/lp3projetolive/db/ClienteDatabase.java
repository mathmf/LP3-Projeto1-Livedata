package com.example.mathe_000.lp3projetolive.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;


import com.example.mathe_000.lp3projetolive.AppExecutors;
import com.example.mathe_000.lp3projetolive.DataGenerator;
import com.example.mathe_000.lp3projetolive.db.Dao.ClienteDao;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.List;


/**
 * Created by mathe_000 on 15/04/2018.
 */
@Database(entities = {Cliente.class}, version = 1, exportSchema = false)
public abstract class ClienteDatabase extends RoomDatabase {


    public static ClienteDatabase sInstance;

    public abstract ClienteDao clienteDao();

    public static final String DATABASE_NAME = "Clientes";


    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static synchronized ClienteDatabase getDatabaseInstance(Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (ClienteDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    // Create the database
    static ClienteDatabase create(Context context) {
        RoomDatabase.Builder<ClienteDatabase> builder =    Room.databaseBuilder(context.getApplicationContext(),
                ClienteDatabase.class,
                "Clientes");
        return builder.build();
    }

    private static ClienteDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, ClienteDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            ClienteDatabase database = ClienteDatabase.getDatabaseInstance(appContext, executors);
                            List<Cliente> Cliente = DataGenerator.generateProducts();

                            insertData(database, Cliente);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    private static void insertData(final ClienteDatabase database, final List<Cliente> clientes) {
        database.runInTransaction(() -> {
            database.clienteDao().insertAll(clientes);
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }


    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }


}
