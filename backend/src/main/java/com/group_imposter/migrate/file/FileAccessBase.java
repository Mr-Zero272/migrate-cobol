package com.group_imposter.migrate.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Base class for file handling
 * 
 * @author QuyetNV2
 *
 */
public class FileAccessBase {
    private String filePath;
    private File file;
    private boolean isEOF = false;
    private String currentLine = null;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    private final String CHAR_SET = "Shift_JIS";

    /**
     * Constructor
     * 
     * @param filePath
     *            file path
     */
    public FileAccessBase(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get file path
     * 
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Open file in specific mode
     * 
     * @param mode
     *            open file mode
     * @return {@link FileStatusConstant}
     */
    public String open(FileOpenMode mode) {
        try {
            file = new File(filePath);
            switch (mode) {
                case IN:
                    file.setReadable(true);
                    file.setWritable(false);
                    bufferedReader = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file), CHAR_SET));
                    break;
                case OUT:
                    file.setReadable(false);
                    file.setWritable(true);
                    bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(new FileOutputStream(file, true), CHAR_SET));
                    break;
                case IN_OUT:
                    file.setReadable(true);
                    file.setWritable(true);
                    bufferedReader = new BufferedReader(
                            new InputStreamReader(new FileInputStream(file), CHAR_SET));
                    bufferedWriter = new BufferedWriter(
                            new OutputStreamWriter(new FileOutputStream(file, true), CHAR_SET));
                default:
                    break;
            }
            return FileStatusConstant.SUCCESS;
        } catch (Exception e) {
            System.out.println("Open fail: " + e.getMessage());
            return FileStatusConstant.FAILED;
        }
    }

    /**
     * Close file
     * 
     * @return {@link FileStatusConstant}
     */
    public String close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            return FileStatusConstant.SUCCESS;
        } catch (IOException e) {
            System.out.println("Close fail: " + e.getMessage());
            return FileStatusConstant.FAILED;
        }
    }

    /**
     * Delete file
     * 
     * @return {@link FileStatusConstant}
     */
    public String delete() {
        new File(filePath).deleteOnExit();
        return FileStatusConstant.SUCCESS;
    }

    /**
     * Read next line
     * 
     * @return {@link FileStatusConstant}
     */
    public String readLine() {
        if (bufferedReader == null) {
            System.out.println("Read line fail. File is not in read mode");
            return FileStatusConstant.FAILED;
        }
        try {
            String tmp = bufferedReader.readLine();
            if (tmp == null) {
                isEOF = true;
            } else {
                isEOF = false;
                currentLine = tmp;
            }
            return FileStatusConstant.SUCCESS;
        } catch (EOFException e) {
            isEOF = true;
            return FileStatusConstant.FAILED;
        } catch (IOException e) {
            System.out.println("Read line fail: " + e.getMessage());
            return FileStatusConstant.FAILED;
        }
    }

    /**
     * Get content of current line
     * 
     * @return content of current line
     */
    public String getCurrentLine() {
        return currentLine;
    }

    /**
     * Is end of file
     * 
     * @return
     */
    public boolean isEOF() {
        return isEOF;
    }

    /**
     * Write content to file
     * 
     * @param content
     * @return {@link FileStatusConstant}
     */
    public String write(String content) {
        if (bufferedWriter == null) {
            System.out.println("Write content fail. File is not in write mode");
            return FileStatusConstant.FAILED;
        }
        if (content == null || content.isEmpty()) {
            System.out.println("Write content fail. Content is null");
            return FileStatusConstant.FAILED;
        }
        try {
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            isEOF = false;
            return FileStatusConstant.SUCCESS;
        } catch (IOException e) {
            System.out.println("Write line fail: " + e.getMessage());
            return FileStatusConstant.FAILED;
        }
    }

    /**
     * Read value of current line to list of object
     * 
     * @param params
     * @return
     */
    public String read(Object... params) {
        String readStatus = readLine();
        if (!FileStatusConstant.SUCCESS.equals(readStatus)) {
            return readStatus;
        }
        if (params == null || params.length == 0) {
            return FileStatusConstant.FAILED;
        }
        for (Object obj : params) {
            readStatus = readToObject(obj);
            if (!FileStatusConstant.SUCCESS.equals(readStatus)) {
                return readStatus;
            }
        }
        return FileStatusConstant.SUCCESS;
    }

    /**
     * Read value of current line to object
     * 
     * @param obj
     * @return
     */
    protected String readToObject(Object obj) {
        return FileStatusConstant.SUCCESS;
    }

    /**
     * Write value of object to file
     * 
     * @param obj
     * @return
     */
    public String writeFromObject(Object obj) {
        return FileStatusConstant.SUCCESS;
    }
}
