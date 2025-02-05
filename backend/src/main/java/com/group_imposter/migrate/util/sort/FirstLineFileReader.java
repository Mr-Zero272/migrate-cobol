package com.group_imposter.migrate.util.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to get and remove first line of file
 *
 */
class FirstLineFileReader {
    private BufferedReader fileBufferedReader;
    private String firstLine;

    protected FirstLineFileReader(File file) throws IOException {
        this.fileBufferedReader = new BufferedReader(new FileReader(file));
        readLine();
    }

    protected String getFirstLine() {
        return firstLine;
    }

    protected String removeFirstLine() throws IOException {
        String result = getFirstLine();
        readLine();
        return result;
    }

    protected boolean empty() {
        return firstLine == null;
    }

    protected void close() throws IOException {
        fileBufferedReader.close();
    }

    private void readLine() throws IOException {
        firstLine = fileBufferedReader.readLine();
    }
}