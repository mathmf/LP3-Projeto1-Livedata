package com.example.mathe_000.lp3projetolive;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by mathe_000 on 15/04/2018.
 */
@Database(entities = {Cliente.class}, version = 1)
public abstract class ClienteDatabase extends RoomDatabase {

    public abstract ClienteDao clienteDao();

    public static ClienteDatabase sInstance;

    public static synchronized ClienteDatabase getDatabaseInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
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
}
