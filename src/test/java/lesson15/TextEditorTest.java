package lesson15;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TextEditorTest {

    @Test
    public void statisticsOfUniqueWordsWithDeletion() {
        TextEditor textEditor = new TextEditor();
        String[] toDelete = {"русс", "рассия"};
        boolean[] caseSensitive = {true, false};
        String urlFileToRead = "src/main/resources/tolstoy_voyna-i-mir__xoraa_436421.txt";
        String urlFileToWrite = "src/main/resources/result_of_text_editor.txt";

        for (boolean cS: caseSensitive){
            textEditor.statisticsOfUniqueWordsWithDeletion(urlFileToRead, urlFileToWrite, toDelete, cS);
            try {
                BufferedReader readFileToWrite = new BufferedReader(new FileReader(urlFileToWrite));
                Stream<String> fromResult = readFileToWrite.lines();
                Map<String, Integer> resultMap = fromResult.
                        map(s -> s.split(" ")).
                        collect(Collectors.toMap(p -> p[0], p -> Integer.parseInt(p[1])));
                readFileToWrite.close();

                BufferedReader readFileToRead = new BufferedReader(new FileReader(urlFileToRead));
                Stream<String> fromSource = readFileToRead.lines();
                List<String> sourceList = fromSource.
                        map(s -> s.split(" ")).
                        flatMap(Arrays::stream).
                        filter(s -> !s.equals("")).
                        map(s -> cS ? s : s.toLowerCase(Locale.ROOT)).
                        map(s -> s.split("")).
                        map(Arrays::stream).
                        map(stringStream -> stringStream.
                                filter(s -> Character.isLetter(s.charAt(0))).
                                reduce("", (acc, s) -> acc+s)
                        ).
                        filter(s -> !s.equals("")).
                        collect(Collectors.toList());
                readFileToRead.close();

                String testKey = sourceList.get((int)(Math.random()*sourceList.size()));
                int testValue = (int) sourceList.stream().
                        filter(s -> s.equals(testKey)).
                        count();
                Assert.assertEquals(testValue, resultMap.get(testKey).intValue());

                List<String> forbiddenWords = new ArrayList<>();
                for (String str: toDelete) {
                    Stream<String> stream = sourceList.stream();
                    stream.
                    filter(s -> s.contains(str)).
                    distinct().
                    forEach(forbiddenWords::add);
                }
                Assert.assertTrue( forbiddenWords.
                        stream().
                        noneMatch(resultMap::containsKey)
                        );
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}