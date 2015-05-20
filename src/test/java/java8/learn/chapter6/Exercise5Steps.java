package java8.learn.chapter6;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise5Steps {

    private Exercise5 exercise5 = new Exercise5();

    private ConcurrentMap<String, Set<File>> result;

    @When("read files in directory '(.+)'")
    public void readFilesInDirectory(String directory) throws IOException {
        result = exercise5.findWordsInFileByMerge(directory);
    }

    @When("work with CocurrentMap::computeIfAbsent, read files in directory '(.+)'")
    public void readFilesInDirectoryByComputeIfAbsent(String directory) throws IOException {
        result = exercise5.findWordsInFileByComputeIfAbsent(directory);
    }

    @Then("word '(.+)' should be in file '(.+)'")
    public void checkWordsInFile(String word, String file) {
        Set<File> files = result.get(word);
        assertThat(files, notNullValue());
        boolean fileFound = files.parallelStream().anyMatch(f -> f.getName().equals(file));
        assertThat(true, equalTo(fileFound));
    }
}
