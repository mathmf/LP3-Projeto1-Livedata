package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.persistence.room.Database;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

public class BasicApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public ClienteDatabase getCliDatabase() {
        return ClienteDatabase.getDatabaseInstance(this, mAppExecutors);
    }

    public ClienteRepositorio getCliRepository() {
        return ClienteRepositorio.getInstance(getCliDatabase());
    }
}
