package com.lilu.jsonpath;

import com.jayway.jsonpath.JsonPath;
import io.netty.util.CharsetUtil;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MongoEvent {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = MongoEvent.class.getClassLoader();
        URL uri = classLoader.getResource("example1.json");
        String filePath = uri.getPath();
        System.out.println(filePath);

        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(Paths.get(filePath)), CharsetUtil.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            strBuilder.append(line);
        }
        String jsonStr = strBuilder.toString();
        System.out.println(jsonStr);

        // Reading a Document
        String coll = JsonPath.read(jsonStr, "$.ns.coll");
        System.out.println(coll);

        // Predicates
        Object read = JsonPath.parse(jsonStr).read("$[?(@['operationType'] == 'update' && @['updateDescription']['updatedFields']['x_others.k_ignore'] == 'v1')]");
        System.out.println(read);
    }
}
