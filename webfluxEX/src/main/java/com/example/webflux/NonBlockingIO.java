package com.example.webflux;

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;

public class NonBlockingIO {

    public static void main(String[] args) throws IOException, InterruptedException {
        BlockHound.install();

        Flux
                .interval(Duration.ofMillis(1000))
                .subscribe(p-> {
                            System.out.println(p);

                            String time = String.valueOf(LocalTime.now());
                            String[] splitTime = time.split("\\.");
                            String fileName = splitTime[0].substring(0, 5).replaceAll(":", ".");
                            String filePath = "./time/" + fileName + ".txt";

                            try {
                                FileOutputStream fo = new FileOutputStream(filePath, true);
                                FileChannel channel = fo.getChannel();

                                ByteBuffer buffer = ByteBuffer.allocate(30);
                                buffer.put((p+"\n").getBytes(StandardCharsets.UTF_8));

                                buffer.flip();
                                channel.write(buffer);
                                channel.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                    Thread.sleep(1000000000L);

    }
}
