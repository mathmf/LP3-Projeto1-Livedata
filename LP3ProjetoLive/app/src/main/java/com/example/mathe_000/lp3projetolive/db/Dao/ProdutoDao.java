package com.example.mathe_000.lp3projetolive.db.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Query("SELECT * FROM Produtos ORDER By Nome"  )
    LiveData<List<Produto>> getAllProdutos();
    // Select one task from Task table by id
    @Query("SELECT * FROM Produtos WHERE id=:id")
    LiveData<Produto> getProdutoById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Produto> produto);

    @Delete
    void delete(Produto produto);

}
