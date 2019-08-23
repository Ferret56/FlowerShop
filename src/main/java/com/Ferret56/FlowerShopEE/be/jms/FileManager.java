package com.Ferret56.FlowerShopEE.be.jms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileManager {
    public byte[] readFileAsBytes(File file) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")) {
            byte[] bytes = new byte[(int) accessFile.length()];
            accessFile.readFully(bytes);
            return bytes;
        }
    }
    public void writeFile(byte[] bytes, String fileName) throws IOException {
        File file = new File(fileName);
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
            accessFile.write(bytes);
        }
    }
    public void writeStringToXml(final String message, final String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write( message);
        fileWriter.close();
    }
}
