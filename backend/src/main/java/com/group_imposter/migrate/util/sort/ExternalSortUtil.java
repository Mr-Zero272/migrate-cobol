package com.group_imposter.migrate.util.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExternalSortUtil {
    /** The Constant MAX_FILE_TEMP. */
    private static long MAX_FILE_TEMP = 1024;
    private static int PRIORITY_QUEUE_DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * External sort
     * 
     * @param inputPath
     *            input file path
     * @param outputPath
     *            output file path
     * @param comparator
     *            object comparator
     * @param chunkSizeInByte
     *            chunk size in byte
     */
    public static void sort(String inputPath, String outputPath, ExternalSortComparator comparator,
            long chunkSizeInByte) {
        try {
            List<File> l = divideToSortedFile(new File(inputPath), comparator, chunkSizeInByte);
            mergeSortedFiles(l, new File(outputPath), comparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * External sort (automatic estimate best size for chunk)
     * 
     * @param inputPath
     *            input file path
     * @param outputPath
     *            output file path
     * @param comparator
     *            object comparator
     */
    public static void sort(String inputPath, String outputPath,
            ExternalSortComparator comparator) {
        try {
            List<File> l = divideToSortedFile(new File(inputPath), comparator);
            mergeSortedFiles(l, new File(outputPath), comparator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Estimate best size of temporary file when divide the original file into
     * smaller files <br/>
     * Base on {@link MAX_FILE_TEMP} and current available memory to calculate
     * the best size of temporary file <br/>
     * Number of block must be not too small and also must be not too big. When
     * it is too small, we will create too many temporary files. Other hand,
     * when it is too big, we will use too much memory.
     * 
     * @param oriFile
     *            original file
     * @return best size of temporary file
     */
    private static long estimateSizeOfTmpFile(File oriFile) {
        // Get size of file
        long sizeOfFile = oriFile.length();
        // Calculate size of temporary file so that number of temporary file
        // don't greater than MAX_FILE_TEMP
        long sizeOfTmpFile = sizeOfFile / MAX_FILE_TEMP + (sizeOfFile % MAX_FILE_TEMP == 0 ? 0 : 1);

        // Calculate current available memory of JVM
        System.gc();
        Runtime r = Runtime.getRuntime();
        // Returns the maximum amount of memory available to the Java Virtual
        // Machine set by the '-mx' or '-Xmx' flags.
        long maxMemory = r.maxMemory();
        // Returns the total memory allocated from the system (which can at most
        // reach the maximum memory value returned by the previous function).
        long totalMemory = r.totalMemory();
        // Returns the free memory within the total memory
        long freeMemoryOfTotal = r.freeMemory();
        // allocated memory
        long allocatedMemory = totalMemory - freeMemoryOfTotal;
        // available memory of JVM
        long availableJVMMemory = maxMemory - allocatedMemory;

        if (sizeOfTmpFile < availableJVMMemory / 2 || sizeOfTmpFile >= availableJVMMemory) {
            // When JVM enough memory to process bigger file
            // => decrease number of temporary file
            // When JVM not enough memory to process big file
            // => increase number of temporary file
            sizeOfTmpFile = availableJVMMemory / 2;
        }

        return sizeOfTmpFile;
    }

    /**
     * Divide original file into smaller files and sort its
     * 
     * @param oriFile
     *            original file
     * @param comparator
     *            object comparator
     * @return list of splitter files (after sort)
     * @throws IOException
     */
    private static List<File> divideToSortedFile(File oriFile, ExternalSortComparator comparator)
            throws IOException {
        return divideToSortedFile(oriFile, comparator, estimateSizeOfTmpFile(oriFile));
    }

    /**
     * Divide original file into smaller files and sort its
     * 
     * @param oriFile
     *            original file
     * @param comparator
     *            object comparator
     * @param chunkSizeInByte
     *            size of chunk in byte
     * @return list of splitter files (after sort)
     * @throws IOException
     */
    private static List<File> divideToSortedFile(File oriFile, ExternalSortComparator comparator,
            long chunkSizeInByte) throws IOException {
        List<File> fileLst = new ArrayList<>();
        BufferedReader bufferReader = new BufferedReader(new FileReader(oriFile));
        long tmpFileSize = chunkSizeInByte;
        try {
            List<String> tmpOutput = new ArrayList<>();
            String line = "";
            try {
                while (line != null) {
                    long currentSize = 0;
                    while ((currentSize < tmpFileSize)
                            && (line = bufferReader.readLine()) != null) {
                        tmpOutput.add(line);
                        // TODO: calculate size of string
                        currentSize += (line.length() * 2 + 40);
                    }
                    fileLst.add(sortTmpFile(tmpOutput, comparator));
                    tmpOutput.clear();
                }
            } catch (EOFException eof) {
                if (!tmpOutput.isEmpty()) {
                    fileLst.add(sortTmpFile(tmpOutput, comparator));
                }
            }
        } finally {
            bufferReader.close();
        }
        return fileLst;
    }

    /**
     * Sort list of string and write to temporary file
     * 
     * @param tempList
     *            list string to sort and write
     * @param comparator
     *            object comparator
     * @return temporary file
     * @throws IOException
     */
    private static File sortTmpFile(List<String> tempList, ExternalSortComparator comparator)
            throws IOException {
        Collections.sort(tempList, comparator);
        File newTempFile = File.createTempFile("ExternalSort", "SortedFile");
        newTempFile.deleteOnExit();
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(newTempFile));
        try {
            for (String objStr : tempList) {
                bufferWriter.write(objStr);
                bufferWriter.newLine();
            }
        } finally {
            bufferWriter.close();
        }
        return newTempFile;
    }

    /**
     * Merge sorted files
     * 
     * @param files
     *            list of sorted files
     * @param outputFile
     *            output file
     * @param comparator
     *            object comparator
     * @return total line number of output file
     * @throws IOException
     */
    private static int mergeSortedFiles(List<File> files, File outputFile,
            final ExternalSortComparator comparator) throws IOException {
        PriorityQueue<FirstLineFileReader> pq = new PriorityQueue<FirstLineFileReader>(
                PRIORITY_QUEUE_DEFAULT_INITIAL_CAPACITY, new Comparator<FirstLineFileReader>() {
                    @Override
                    public int compare(FirstLineFileReader objA, FirstLineFileReader objB) {
                        return comparator.compare(objA.getFirstLine(), objB.getFirstLine());
                    }
                });
        for (File objFile : files) {
            FirstLineFileReader firstLineFileBuffer = new FirstLineFileReader(objFile);
            pq.add(firstLineFileBuffer);
        }
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(outputFile));
        int rowCounter = 0;
        try {
            if (!pq.isEmpty()) {
                FirstLineFileReader binaryFileBuffer = pq.poll();
                String r = binaryFileBuffer.removeFirstLine();
                ++rowCounter;
                bufferWriter.write(r);
                if (binaryFileBuffer.empty()) {
                    binaryFileBuffer.close();
                } else {
                    pq.add(binaryFileBuffer);
                }
            }
            while (!pq.isEmpty()) {
                FirstLineFileReader binaryFileBuffer = pq.poll();
                String r = binaryFileBuffer.removeFirstLine();
                ++rowCounter;
                bufferWriter.newLine();
                bufferWriter.write(r);
                if (binaryFileBuffer.empty()) {
                    binaryFileBuffer.close();
                } else {
                    pq.add(binaryFileBuffer);
                }
            }
        } finally {
            bufferWriter.close();
            for (FirstLineFileReader flfd : pq) {
                flfd.close();
            }
        }
        return rowCounter;
    }
}
