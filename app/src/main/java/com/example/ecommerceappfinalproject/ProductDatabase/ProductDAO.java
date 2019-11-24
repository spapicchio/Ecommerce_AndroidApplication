package com.example.ecommerceappfinalproject.ProductDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert
    void insertPasta(Pasta pasta);

    @Insert
    void insertConserve(Conserve conserve);

    @Insert
    void insertSpecial(Special special);

    @Delete
    void deletePasta(Pasta pasta);

    @Delete
    void deleteConserve(Conserve conserve);

    @Delete
    void deleteSpecial(Special special);

    @Query("SELECT * FROM pasta_table WHERE integrale LIKE :integrale")
    LiveData<List<Pasta>> selectTypePasta(boolean integrale);

    @Query("SELECT * FROM conserve_table WHERE type LIKE :type")
    LiveData<List<Conserve>> selectTypeConserve(String type);

    @Query("SELECT * FROM special_table WHERE type LIKE :type")
    LiveData<List<Special>> selectSpecialByType(String type);


    @Query("SELECT * FROM pasta_table")
    LiveData<List<Pasta>> allPasta();

    @Query("SELECT * FROM conserve_table")
    LiveData<List<Conserve>> allConserve();

    @Query("SELECT * FROM special_table")
    LiveData<List<Special>> allSpecial();

    @Query("SELECT * FROM pasta_table WHERE name LIKE :name")
    LiveData<Pasta> selectPastaByName(String name);

    @Query("SELECT * FROM conserve_table WHERE name LIKE :name")
    LiveData<Conserve> selectConserveByName(String name);

    @Query("SELECT * FROM special_table WHERE name LIKE :name")
    LiveData<Special> selectSpecialByName(String name);
}
