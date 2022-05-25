package lesson16;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    private static class Card implements Serializable {
        private static final long serialVersionUID = 2L;
        private String author;
        private String title;
    }

    private final Map<Integer, Card> books = new HashMap<>();
    private final Deque<Integer> freeID = new LinkedList<>();
    public List<String> booksToDisplay = new ArrayList<>();

    public boolean add(String author, String title){
        boolean noRepeat = books.entrySet().stream().
                noneMatch((s) ->
                        s.getValue().title.equalsIgnoreCase(title) && s.getValue().author.equalsIgnoreCase(author)
                );
        if (noRepeat) {
            Card card = new Card();
            card.author = author;
            card.title = title;
            Integer id = freeID.isEmpty() ? books.size() : freeID.poll();
            books.put(id,card);
        }
        return noRepeat;
    }

    public boolean del(Integer id){
        boolean result = books.remove(id) != null;
        if (result){
            freeID.add(id);
        }
        return result;
    }

    public boolean find(String find){
        booksToDisplay.clear();
        booksToDisplay = books.entrySet().stream().
                filter(s -> s.getValue().author.toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT)) ||
                        s.getValue().title.toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT))).
                map(s -> String.format("id:%s, author: %s, title: %s", s.getKey(), s.getValue().author, s.getValue().title)).
                collect(Collectors.toList());
        return !booksToDisplay.isEmpty();
    }

    public String findID(Integer id){
        String result = null;
        if (books.get(id)!=null){
            result = String.format("id:%s, author: %s, title: %s", id, books.get(id).author, books.get(id).title);
        }
        return result;
    }

    public boolean view(){
        boolean result = !books.isEmpty();
        booksToDisplay.clear();
        if (result) {
            booksToDisplay = books.entrySet().stream().
                    map(s -> String.format("id:%s, author: %s, title: %s", s.getKey(), s.getValue().author, s.getValue().title)).
                    collect(Collectors.toList());
        }
        return result;
    }
}
