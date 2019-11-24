package com.example.ecommerceappfinalproject.ProductDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel  extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<Pasta>> pastas;
    private LiveData<List<Special>> specials;
    private LiveData<List<Conserve>> conserves;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);

    }

    public void insertPasta(Pasta pasta) {
        productRepository.insertPasta(pasta);
    }

    public void insertConserve (Conserve conserve) {
       productRepository.insertConserve(conserve);

    }

    public void insertSpecial (Special special) {
        productRepository.insertSpecial(special);

    }

    public void deletePasta(Pasta pasta) {
        productRepository.deletePasta(pasta);
    }

    public void deleteConserve (Conserve conserve) {
        productRepository.deleteConserve(conserve);
    }

    public void deleteSpecial (Special special) {
       productRepository.deleteSpecial(special);
    }

    public LiveData<List<Pasta>> getAllPasta() {
        return productRepository.getAllPasta();
    }

    public LiveData<List<Conserve>> getAllConserve() {

        return productRepository.getAllConserve();
    }

    public LiveData<List<Special>> getAllSpecial() {

        return productRepository.getAllSpecial();
    }

    public LiveData<List<Pasta>> selectTypePasta(boolean integrale) {
        return productRepository.selectTypePasta(integrale);
    }

    public LiveData<List<Conserve>> selectTypeConserve(String type) {
        return productRepository.selectTypeConserve(type);
    }

    public LiveData<List<Special>> selectTypeSpecial(String type){
        return productRepository.selectTypeSpecial(type);
    }

    public LiveData<Pasta> selectPastaByName(String name){
        return productRepository.selectPastaByid ( name );
    }

    public LiveData<Conserve> selectConserveByName(String name){
        return productRepository.selectConserveById (  name );
    }


    public LiveData<Special> selectSpecialByName(String name){
        return productRepository.selectSpecialById ( name );
    }


}
