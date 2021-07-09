package com.example.demo.util;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnCompress {

    public static boolean uncompress(String inputPath, String outputPath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(inputPath));
        if (inputPath.toUpperCase().endsWith(".Z")) {
            return uncompressForZ(inputStream, outputPath);
        } else if (inputPath.toLowerCase().endsWith(".zip")) {
            return uncompressForZip(inputStream, outputPath);
        } else {
            return uncompressForGZip(inputStream, outputPath);
        }
    }

    public static boolean uncompressForZ(InputStream zipInputStream, String destPath) {
        OutputStream outputStream;
        ZCompressorInputStream inputStream;
        try {
            File file = new File(destPath);
            if (!file.exists()) {
                new File(file.getParent()).mkdir();
            }
            BufferedInputStream in = new BufferedInputStream(zipInputStream);
            outputStream = new FileOutputStream(destPath);
            inputStream = new ZCompressorInputStream(in);
            final byte[] buffer = new byte[2048];
            int n = 0;
            while ( -1 != inputStream.read(buffer)) {
                outputStream.write(buffer, 0, n);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            // error log
            return false;
        }
        return true;
    }
    public static boolean uncompressForZip(InputStream zInputStream, String destPath) {
        ZipInputStream zipInputStream;
        ZipEntry zipEntry;
        File file;
        try {
            zipInputStream = new ZipInputStream(zInputStream);
            while((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (!zipEntry.isDirectory()) {
                    file = new File(destPath, zipEntry.getName());
                    if (!file.exists()) {
                        new File(file.getParent()).mkdir();
                    }
                    OutputStream outputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = zipInputStream.read(buf)) != -1) {
                        bufferedOutputStream.write(buf, 0, len);
                    }
                    bufferedOutputStream.close();
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public static boolean uncompressForGZip(InputStream zipInputStream, String destPath) {
        File file = new File(destPath);
        if (!file.exists()) {
            new File(file.getParent()).mkdir();
        }
        try (GzipCompressorInputStream inputStream = new GzipCompressorInputStream(zipInputStream)){
            FileOutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
