package com.example.mathe_000.lp3projetolive.db.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

import java.util.List;

public interface PagamentosDao {

    @Query("SELECT * FROM Pagamentos ORDER By Cliente"  )
    LiveData<List<Pagamentos>> getAllPagamentos();

    @Query("SELECT * FROM Pagamentos WHERE id=:id")
    LiveData<Pagamentos> getPagamentobyId(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pagamentos> pagamentos);

    @Delete
    void delete(Pagamentos pagamento);
}
