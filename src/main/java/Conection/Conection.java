package Conection;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import response.StandarResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class Conection {


    // El metodo get de la clase getJson retorna un Buffered Reader de la url que se le envíe
    public static BufferedReader get(String urlGet, List<Header> headers){
        try {
            URL url = new URL(urlGet);

            try {
                URLConnection urlconnection = url.openConnection();
                urlconnection.setRequestProperty("Accept","application/json");
                urlconnection.setRequestProperty("User-Agent","Mozilla/5.0");
                for(Header header : headers)
                {
                    urlconnection.setRequestProperty(header.getKey(),header.getValue());
                }

                if(urlconnection instanceof HttpURLConnection) {
                    HttpURLConnection connection = (HttpURLConnection) urlconnection;
                    return  (new BufferedReader(new InputStreamReader(connection.getInputStream())));

                } else {
                    System.out.println("URL inválida");
                    return  (null);
                }


            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return  (null);
    }


    public static BufferedReader post(String urlGet, List<Header> headers){
        try {
            URL url = new URL(urlGet);

            try {
                URLConnection urlconnection = url.openConnection();
                urlconnection.setRequestProperty("Accept","application/json");
                urlconnection.setRequestProperty("User-Agent","Mozilla/5.0");
                for(Header header : headers)
                {
                    urlconnection.setRequestProperty(header.getKey(),header.getValue());
                }

                if(urlconnection instanceof HttpURLConnection) {
                    HttpURLConnection connection = (HttpURLConnection) urlconnection;
                    connection.setRequestMethod("POST");
                    System.out.println("Code of MOCK --->"+connection.getResponseCode());
                    return  (new BufferedReader(new InputStreamReader(connection.getInputStream())));

                } else {
                    System.out.println("URL inválida");
                    return  (null);
                }


            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return  (null);
    }

    public static StandarResponse postTestCode(String urlGet, List<Header> headers){
        StandarResponse standarResponse = new StandarResponse(400,null);
        try {
            URL url = new URL(urlGet);

            try {
                URLConnection urlconnection = url.openConnection();
                urlconnection.setRequestProperty("Accept","application/json");
                urlconnection.setRequestProperty("User-Agent","Mozilla/5.0");
                for(Header header : headers)
                {
                    urlconnection.setRequestProperty(header.getKey(),header.getValue());
                }

                if(urlconnection instanceof HttpURLConnection) {
                    HttpURLConnection connection = (HttpURLConnection) urlconnection;
                    connection.setRequestMethod("POST");
                    standarResponse.setStatusResponse(connection.getResponseCode());
                    System.out.println("Code of MOCK --->"+connection.getResponseCode());
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    standarResponse.setData(new Gson().fromJson(br.readLine(),JsonElement.class));
                    standarResponse.setStatusResponse(connection.getResponseCode());
                    return standarResponse;

                } else {
                    System.out.println("URL inválida");
                    return  standarResponse;
                }


            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                return  standarResponse;
            }

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
            return  standarResponse;
        }
    }

    // El metodo get de la clase getJson retorna un Buffered Reader de la url que se le envíe
    public static StandarResponse getTestCode(String urlGet, List<Header> headers){
        StandarResponse standarResponse = new StandarResponse(400,null);
        try {
            URL url = new URL(urlGet);

            try {
                URLConnection urlconnection = url.openConnection();
                urlconnection.setRequestProperty("Accept","application/json");
                urlconnection.setRequestProperty("User-Agent","Mozilla/5.0");
                for(Header header : headers)
                {
                    urlconnection.setRequestProperty(header.getKey(),header.getValue());
                }

                if(urlconnection instanceof HttpURLConnection) {
                    HttpURLConnection connection = (HttpURLConnection) urlconnection;
                    standarResponse.setStatusResponse(connection.getResponseCode());

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    standarResponse.setData(new Gson().fromJson(br.readLine(),JsonElement.class));
                    return  standarResponse;

                } else {
                    System.out.println("URL inválida");
                    return  standarResponse;
                }


            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return  standarResponse;
    }


}
