package com.example.ecommerceappfinalproject.ProductDatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {
    private ProductDAO productDAO;
    private LiveData<List<Pasta>> pastas, allPasta;
    private LiveData<List<Conserve>> conserves, allConserve;
    private LiveData<List<Special>> specials, allSpecial;
    private LiveData<Pasta> pastaById;
    private LiveData<Conserve> conserveById;
    private LiveData<Special> specialById;


    public ProductRepository(Application application){
        ProductDatabase database =  ProductDatabase.getInstance(application);
        productDAO = database.productDao();

        allPasta = productDAO.allPasta();
        allConserve = productDAO.allConserve();
        allSpecial = productDAO.allSpecial();
    }

    public void insertPasta(Pasta pasta) {
        new InsertPastaAsyncTask(productDAO).execute(pasta);

    }

    public void insertConserve (Conserve conserve) {
        new InsertConserveAsyncTask(productDAO).execute(conserve);

    }

    public void insertSpecial (Special special) {
        new InsertSpecialAsyncTask(productDAO).execute(special);

    }

    public void deletePasta(Pasta pasta) {
        new DeletePastaAsyncTask(productDAO).execute((pasta));
    }

    public void deleteConserve (Conserve conserve) {
        new DeleteConserveAsyncTask(productDAO).execute(conserve);
    }

    public void deleteSpecial (Special special) {
        new DeleteSpecialAsyncTask(productDAO).execute(special);
    }

    public LiveData<List<Pasta>> getAllPasta() {
        return allPasta;
    }

    public LiveData<List<Conserve>> getAllConserve() {
        return allConserve;
    }

    public LiveData<List<Special>> getAllSpecial() {
        return allSpecial;
    }

    public LiveData<List<Pasta>> selectTypePasta(boolean integrale) {
        pastas = productDAO.selectTypePasta(integrale);
        return pastas;
    }

    public LiveData<List<Conserve>> selectTypeConserve(String type) {
        conserves = productDAO.selectTypeConserve(type);
        return conserves;
    }

    public LiveData<List<Special>> selectTypeSpecial(String type){
        specials = productDAO.selectSpecialByType (type);
        return specials;
    }

    public LiveData<Pasta> selectPastaByid(String name){
        pastaById = productDAO.selectPastaByName ( name );
        return pastaById;
    }

    public LiveData<Conserve> selectConserveById(String name){
        conserveById = productDAO.selectConserveByName ( name);
        return conserveById;
    }

   public LiveData<Special> selectSpecialById(String name){
        specialById = productDAO.selectSpecialByName ( name );
        return specialById;
   }

    private static class InsertPastaAsyncTask extends AsyncTask<Pasta, Void, Void>{

        private ProductDAO dao;

        private InsertPastaAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Pasta... pastas) {
            dao.insertPasta(pastas[0]);
            return null;
        }
    }

    private static class InsertConserveAsyncTask extends AsyncTask<Conserve, Void, Void>{

        private ProductDAO dao;

        private InsertConserveAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Conserve... conserves) {
            dao.insertConserve(conserves[0]);
            return null;
        }
    }

    private static class InsertSpecialAsyncTask extends AsyncTask<Special, Void, Void>{

        private ProductDAO dao;

        private InsertSpecialAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Special... specials) {
            dao.insertSpecial(specials[0]);
            return null;
        }
    }

    private static class DeletePastaAsyncTask extends AsyncTask<Pasta, Void, Void>{

        private ProductDAO dao;

        private DeletePastaAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Pasta... pastas) {
            dao.deletePasta(pastas[0]);
            return null;
        }
    }

    private static class DeleteConserveAsyncTask extends AsyncTask<Conserve, Void, Void>{

        private ProductDAO dao;

        private DeleteConserveAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Conserve... conserves) {
            dao.deleteConserve(conserves[0]);
            return null;
        }
    }

    private static class DeleteSpecialAsyncTask extends AsyncTask<Special, Void, Void>{

        private ProductDAO dao;

        private DeleteSpecialAsyncTask(ProductDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Special... specials) {
            dao.deleteSpecial(specials[0]);
            return null;
        }
    }



}
