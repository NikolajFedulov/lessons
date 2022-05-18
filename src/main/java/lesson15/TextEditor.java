package lesson15;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextEditor {

    private String[] wordsForDelete;

    private boolean skipWord(String str){
        Stream<String> stream = Arrays.stream(wordsForDelete);
        return stream.noneMatch(str::contains);
    }

    public void statisticsOfUniqueWordsWithDeletion(String urlFileToRead, String urlFileToWrite, String[] toDelete, boolean letterCase) {
        wordsForDelete = toDelete;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(urlFileToRead));
            Map<String, Long> uniqueWords = bufferedReader.lines().
                    map(s -> s.split(" ")).
                    flatMap(Arrays::stream).
                    filter(s -> !s.equals("")).
                    map(s -> letterCase ? s : s.toLowerCase(Locale.ROOT)).
                    map(s -> s.split("")).
                    map(Arrays::stream).
                    map(stringStream -> stringStream.
                            filter(s -> Character.isLetter(s.charAt(0))).
                            reduce("", (acc, s) -> acc+s)
                    ).
                    filter(s -> !s.equals("")).
                    filter(this::skipWord).
                    collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            bufferedReader.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(urlFileToWrite));
            Stream<Map.Entry<String, Long>> outStream = uniqueWords.entrySet().stream();
            outStream.forEach(s -> {
                try {
                    bufferedWriter.write(String.format("%s %s", s.getKey(), s.getValue()));
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
