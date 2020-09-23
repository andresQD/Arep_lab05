/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.eci.arep.sparkdockerweblive.persistencia;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Andrés Quintero
 */
public class Mongo {

    public MongoClient mongoClient;
    public MongoClientURI uri;
    private DBCollection collection;
    private DB db;
    
    /**
     * Inicializa la conexion con mongodb
     */
    public Mongo() {
        uri = new MongoClientURI(
                //"mongodb://db:27017");
                "mongodb://andrew:arep2020@localhost:27017/DataBaseArep?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=admin&authMechanism=SCRAM-SHA-1&3t.uriVersion=3&3t.connection.name=mongo&3t.databases=DataBaseArep");
        mongoClient = new MongoClient(uri);
       /** db = mongoClient.getDB("DataBaseArep");
        collection = db.getCollection("DataBaseArep");
        MongoDatabase database = mongoClient.getDatabase("DataBaseArep");
        MongoCollection<Document> collection = database.getCollection("DataBaseArep");
        Document document1 = new Document("name", "Ram").append("age", 26).append("city", "Hyderabad");
        List<Document> list = new ArrayList<Document>();
        list.add(document1);
        collection.insertMany(list);
        System.out.println("Documents Inserted");
        /**DB db = mongoClient.getDB("DataBaseArep");
        collection = db.getCollection("DataBaseArep");
        MongoDatabase database = mongoClient.getDatabase("DataBaseArep");
        MongoCollection<Document> collection = database.getCollection("DataBaseArep");
        FindIterable<Document> cursor = collection.find();
        String datos = "";
            for(Document document : cursor) {
                datos+="\n"+document;
               System.out.println(document.toJson());
            }**/
    }

    /**
     * este metodo permite añadir un nuevo mensaje a la base de datos 
     * @param mensaje mensaje
     */
    public void añadirMensaje(String mensaje) {
        Date date = new Date();
        db = mongoClient.getDB("DataBaseArep");
        collection = db.getCollection("DataBaseArep");
        MongoDatabase database = mongoClient.getDatabase("DataBaseArep");
        MongoCollection<Document> collection = database.getCollection("DataBaseArep");
        Document document = new Document("fecha", date.toString()).append("mensaje", mensaje);
        List<Document> list = new ArrayList<Document>();
        list.add(document);
        collection.insertMany(list);
    }
    
    /**
     * Este metodo permite listar los ultimos 10 mensajes
     * @return datos mensajes de la bd
     */
    public String selectAll(){
        MongoDatabase db = mongoClient.getDatabase("DataBaseArep");
        MongoCollection<Document> collection = db.getCollection("DataBaseArep");
        FindIterable<Document> cursor = collection.find();
        int indice = 0;
        long full =collection.countDocuments();
        String datos = "";
            for(Document document : cursor) {
                if(indice > full - 11 ){
                    datos+="\n" + "[" + "\n" + "Mensaje #: " + indice + "," + "\n" + "El Mensaje es: " + document.getString("mensaje") + "," + "\n" + "Fecha del Mensaje: " + document.getString("fecha") + "]" + "\n" + ";" + "\n";
                }
                indice++;
               System.out.println(document.toString());
        }
        return datos;
    }
}


