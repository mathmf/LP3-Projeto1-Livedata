package com.example.mathe_000.lp3projetolive.db.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.List;

/**
 * Created by mathe_000 on 15/04/2018.
 */
@Dao
public interface ClienteDao {

    @Query("SELECT * FROM Clientes ORDER By Nome"  )
    LiveData<List<Cliente>> getAllClientes();
    // Select one task from Task table by id
    @Query("SELECT * FROM Clientes WHERE id=:id")
    LiveData<Cliente> getClienteById(int id);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Cliente> clientes);

    @Delete
    void delete(Cliente client);

    @Update
    void update(Cliente client);


}
