package org.example;

import lombok.Data;
import lombok.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@Data
public class ReplacerStreamPipe {

    private static final int READ_BUFFER_BYTE_SIZE = 64;

    private String find = "lang3";
    private String replace = "sinterklaas";

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("pom.xml");
        OutputStream outputStream = new PrintStream(System.out);

        new ReplacerStreamPipe().doPipe(inputStream, outputStream);

        inputStream.close();
        outputStream.close();
    }

    public void doPipe(@NonNull InputStream inputStream, @NonNull OutputStream outputStream) {
        try {
            byte[] read = new byte[READ_BUFFER_BYTE_SIZE];
            int count;

            while ((count = inputStream.read(read)) != -1) {
                // Disadvantage of this approach: every part of the stream, is getting converted into a String
                String part = new String(read, StandardCharsets.UTF_8);
                if (!part.contains(find)) {
                    outputStream.write(read, 0, count);
                    continue;
                }
                byte[] replacement = part.replace(find, replace).getBytes(StandardCharsets.UTF_8);
                outputStream.write(replacement, 0, replacement.length);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
