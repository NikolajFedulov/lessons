package lesson18;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.*;
import java.util.stream.Collectors;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LibraryManager{

    private final Map<Integer, Book> books = new HashMap<>();
    private final Deque<Integer> freeID = new LinkedList<>();

    public boolean add(Book book){
        boolean noRepeat = books.entrySet().stream().
                noneMatch((s) -> s.getValue().equals(book));
        if (noRepeat) {
            Integer id = freeID.isEmpty() ? books.size() : freeID.pollLast();
            return null == books.put(id, book);
        }
        return false;
    }

    public Book del(Integer id){
        Book result = books.remove(id);
        if (result != null){
            freeID.add(id);
        }
        return result;
    }

    public Map<Integer, Book> find(String find){
        return books.entrySet().stream().
                filter(s -> s.getValue().getAuthor().toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT)) ||
                        s.getValue().getTitle().toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT))).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Book> view(){
        return books.
                entrySet().
                stream().
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
