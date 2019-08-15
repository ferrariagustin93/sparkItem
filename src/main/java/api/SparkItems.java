package api;

import Conection.Conection;
import Conection.Header;
import clase.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import response.StandarResponse;
import response.StatusResponse;
import service.IItemService;
import serviceImpl.ItemServiceMapImpl;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static spark.Spark.*;

public class SparkItems {
    public static void main(String[] args) {
        final IItemService service = new ItemServiceMapImpl();

        // obtener todos los items - DEBE VALIDAR
        get("/items",(req,res) -> {
            res.type("application/json");
            Collection <Item> items = service.getItems();
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(items)));
        });

        // obtener items por usuario - DEBE VALIDAR
        get("/item",(req,res) -> {
            res.type("application/json");
            Item item = service.getItem(req.headers("id"));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });

        // agregar elementos - busca el elemento en el req parseado con Gson - La respuesta la envía en formato Json
        post("/items", (req,res) -> {
            res.type("application/json");
            Item item = new Gson().fromJson(req.body(),Item.class);
            String id = service.addItem(item.getId(),item);
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });

        // modificar elementos
        put("/items/:id", (req,res) -> {
            res.type("application/json");
            Item item = service.updateItem(req.params(":id"),new Gson().fromJson(req.body(),Item.class));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });

        delete("sites/:id",(req,res) ->{
            res.type("application/json");
            Item item = service.deleteItem(req.params(":id"));
            return new Gson().toJson(new StandarResponse( StatusResponse.SUCCESS, new Gson().toJsonTree(item)));
        });

        // existe un elemento
        options("sites/:id",(req,res) -> {
            res.type("application/json");
            if(service.itemExists(req.params(":id"))) {
                return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS));
            }
            else {
                return new Gson().toJson(new StandarResponse(StatusResponse.ERROR));
            }
        });

        /* Ejemplo de como comectarse a localhost: */
        // Site[] sites= new Gson().fromJson(getJSON.get("https://api.mercadolibre.com/sites"),Site[].class);
        /* ----------------------------------------- */
        //Conection.post("localhost:8081/users", Arrays.asList(new Header[]{new Header("username","marcos"),new Header("password","1234")}));
        //Conection.get("localhost:8081/sites", Arrays.asList(new Header[]{new Header("token","esuntoken"),new Header("id","123456")}));
        /*Site[] sites= new Gson().fromJson(Conection.get("http://localhost:8081/sites", Arrays.asList(new Header[]{new Header("token","esuntoken"),new Header("id","123456")})),Site[].class);
        for(Site site: sites)
        {
            System.out.println(site.toString());
        }*/

        // obtener todos los sitios a partir de SparkUser
        /*get("/sites",(req,res) -> {
            res.type("application/json");
            Site[] sites= new Gson().fromJson(Conection.get("http://localhost:8081/sites", Arrays.asList(new Header[]{new Header("token","esuntoken"),new Header("id","123456")})),Site[].class);
            //Item item = service.getItem(req.headers("id"));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(sites)));
        });*/

        // obtener todos los sitios a partir de SparkUser
        get("/sites",(req,res) -> {
            res.type("application/json");
            Site[] sites= new Gson().fromJson(Conection.get("http://localhost:8081/sites", Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))})),Site[].class);
            //Item item = service.getItem(req.headers("id"));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(sites)));
        });


        // Solicita un user y un pass. Devuelve un token
        post("/users", (req,res) -> {
            res.type("application/json");
            BufferedReader br = Conection.post("http://localhost:8081/users", Arrays.asList(new Header[]{new Header("username",req.headers("username")),new Header("password",req.headers("password"))}));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().fromJson(br.readLine(),JsonElement.class)));
        });

        // Solicita un idSite y devuelve todas las categorias
        get("/sites/:id/categories",(req,res) -> {
            res.type("application/json");
            Category[] categories= new Gson().fromJson(Conection.get("http://localhost:8081/sites/"+req.params(":id")+"/categories", Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))})),Category[].class);
            System.out.println("Entro al cateogires");
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(categories)));
        });

        // Solicita un idSite y devuelve todas las categorias
        get("/sites/:idSite/categories/:idCategories/:item",(req,res) -> {
            res.type("application/json");
            Category[] categories= new Gson().fromJson(Conection.get("http://localhost:8081/sites/"+req.params(":id")+"/categories", Arrays.asList(new Header[]{new Header("token",req.headers("token")),new Header("id",req.headers("id"))})),Category[].class);
            System.out.println("Entro al cateogires");
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(categories)));
        });



        /* Deberia poder realizar consultas al mockUser de Marcos, para esto debo realizar un Conection como aparece en el proyecto
         * "MavenTest". Estas consultas son:
          * POST localhost:8082/login y en el header mandar user y poss ---> Recibir idUser, user, token
          * GET localhost:8082/sites y en el header token user ---> recibir todos los sites ordenados (idSite, nameSite)
          * GET localhost:8082/sites/idSite y en el header token user ---> recibir las categorias de ese site
          * POST localhost:8082/sites/idSite/idCategory/nameItem y en el header user token ---> y debe guardar el item con toda la info*/
    }
}
