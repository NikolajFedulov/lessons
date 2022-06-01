package lesson18;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;

public class LibrarySerializer {

    private final File path;


    public LibrarySerializer() {
        path = new File("src/main/resources/library_save.json");
    }

    public LibrarySerializer(File path) {
        this.path = path;
    }


    public void serialize(LibraryManager libraryManager){
        ObjectMapper jsonMapper = new JsonMapper();
        try {
            jsonMapper.writeValue(path, libraryManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LibraryManager deserialize(LibraryManager libraryManager){
        ObjectMapper jsonMapper = new JsonMapper();
        try {
            libraryManager = jsonMapper.readValue(path, LibraryManager.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraryManager;
    }

}
