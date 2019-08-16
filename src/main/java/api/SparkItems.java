package api;

import Conection.Conection;
import Conection.Header;
import clase.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import response.StandarResponse;
import response.StatusResponse;
import service.IItemService;
import service.IItemTokenService;
import serviceImpl.IItemTokenServiceMapImpl;
import serviceImpl.ItemServiceMapImpl;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static spark.Spark.*;

public class SparkItems {
    public static void main(String[] args) {
        //final IItemService service = new ItemServiceMapImpl();
        final IItemTokenService service = new IItemTokenServiceMapImpl();
        final String urlUser = "http://localhost:8086";

        /**
         * Devuelve todos los items cargados
         */
        get("/items",(req,res) -> {
            res.type("application/json");
            Collection <ItemToken> items = service.getItems();
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(items)));
        });


        /**
         * Modifica un items. Requiere un id de item.
         */
        put("/items/:id", (req,res) -> {
            res.type("application/json");
            ItemToken item = service.updateItem(req.params(":id"),new Gson().fromJson(req.body(),ItemToken.class));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });


        /**
         * Elimina un item. Requiere el id de item por parametro.
         */
        delete("items/:id",(req,res) ->{
            res.type("application/json");
            ItemToken item = service.deleteItem(req.params(":id"));
            return new Gson().toJson(new StandarResponse( StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });

        /**
         * Devuelve si existe un item.
         * Solicita el id del item.
         */
        options("items/:id",(req,res) -> {
            res.type("application/json");
            if(service.itemExists(req.params(":id"))) {
                return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS));
            }
            else {
                return new Gson().toJson(new StandarResponse(StatusResponse.ERROR));
            }
        });



        /**
         * Valida el username y el password.
         * Devuelve un token y un id de user.
         */
        post("/users", (req,res) -> {
            res.type("application/json");
            StandarResponse sr = Conection.postTestCode(urlUser+"/users", Arrays.asList(new Header[]{new Header("username",req.headers("username")),new Header("password",req.headers("password"))}));
            res.status(sr.getStatusResponse());
            System.out.println("el statis antes de salir es:"+sr.getStatusResponse());
            return new Gson().toJson(sr.getData());
        });

        /**
         * Devuelve todos los sitios.
         * Requiere id de user y token para realizar la operación.
         */
        get("/sites",(req,res) -> {
            res.type("application/json");
            StandarResponse standarResponse = Conection.getTestCode(urlUser+"/sites", Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))}));
            Site[] sites= new Gson().fromJson(standarResponse.getData(),Site[].class);
            res.status(standarResponse.getStatusResponse());
            return new Gson().toJson(new Gson().toJsonTree(sites));
        });

        /**
         * Devuelve todas las categorias por un id de Site.
         * Requiere en los hedears el id de user y el token para realizar la operación.
         */
        get("/sites/:id/categories",(req,res) -> {
            res.type("application/json");
            StandarResponse standarResponse = Conection.getTestCode(urlUser+"/sites/"+req.params(":id")+"/categories", Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))}));
            Category[] categories= new Gson().fromJson(standarResponse.getData(),Category[].class);
            res.status(standarResponse.getStatusResponse());
            return new Gson().toJson( new Gson().toJsonTree(categories));
        });

        /**
         * Agrega un item. Dentro del item guarda el id del user, id del site, id de categiría y el nombre del item.
         * Genera internamente un id de item.
         * Requiere por header token y id de user.
         */
        post("/sites/:idSite/categories/:idCategories/:item",(req,res) -> {
            res.type("application/json");
            StandarResponse standarResponse = Conection.getTestCode(urlUser+"/sites/"+req.params(":idSite")+"/categories/"+req.params(":idCategores")+"/"+req.params(":item"), Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))}));
            res.status(standarResponse.getStatusResponse());
            if(standarResponse.getStatusResponse()==200) {
                System.out.println("Entro al post");
                ItemToken itemToken = new ItemToken(req.params("item"), req.headers("id"), req.params(":idSite"), req.params("idCategories"));
                String item = service.addItem(itemToken);
                return new Gson().toJson(new Gson().toJsonTree(itemToken));
            }
            return new Gson().toJson( standarResponse.getStatusResponse());
        });

        /**
         * Devuelve todos los itemes por usuario.
         * <code>:idUser</code> es el id de usuario que se desea buscar.
         */
        get("/items/:idUser",(req,res) -> {
            res.type("application/json");
            System.out.println("Entro al item");
            Collection <ItemToken>itemsUser = service.getItemsUser(req.params(":idUser"));
            return new Gson().toJson(new Gson().toJsonTree(itemsUser));

        });

    }
}
