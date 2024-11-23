package com.lilu.mongo;

import com.lilu.utils.PropertiesReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Properties;

public class MongoBasic {
    MongoClient mongoClient = null;
    MongoCollection<Document> collection = null;

    public static void main(String[] args) {
        MongoBasic mongoBasic = new MongoBasic();
        // mongoBasic.exampleLoopData();
        mongoBasic.exampleGetOneReport();
    }

    public void exampleGetOneReport() {
        this.setConnection();
        try {
            Document query = new Document("_id", "f_3RHIUhdNdxcsXSllLhaAU7");
            Document first = this.collection.find(query).first();
            System.out.println(first);
        } finally {
            this.mongoClient.close();
        }
    }

    public void exampleLoopData() {
        this.setConnection();

        Document query = new Document();
        query.append("x_reported_at_utc_date", new Document()
                        .append("$gte", "2024-01-01")
                        .append("$lte", "2024-01-31"))
                .append("x_spider_name", "stock_nasdaq");
        try (MongoCursor<Document> mongoCursor = this.collection.find(query).iterator()) {
            int cnt = 1;
            while (mongoCursor.hasNext()) {
                Document result = mongoCursor.next();
                Document xInfoData = (Document) result.get("x_info_data");
                System.out.println(cnt++ + "..." + result.get("_id"));
                System.out.println(xInfoData);
            }
        } finally {
            this.mongoClient.close();
        }
    }

    // ------------------------------------------------------------------------------------------

    public void setConnection() {
        Properties propsConfig = PropertiesReader.getProperties("mongodb.properties");
        this.mongoClient = new MongoClient(new MongoClientURI(propsConfig.getProperty("MONGO_UIR")));

        MongoDatabase database = this.mongoClient.getDatabase("filing_reports");
        this.collection = database.getCollection("filing_data");
    }

    public void closeConnection() {
        if (this.mongoClient == null) {
            try {
                this.mongoClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}