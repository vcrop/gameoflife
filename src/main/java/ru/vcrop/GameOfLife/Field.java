package ru.vcrop.GameOfLife;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
@Data
@Component
@SessionScope
public class Field{

    private final int size;
    private final int[][] array;

    Field(@Value("${field.size}") int size, @Value("${game.ratio}") int ratio) {
        this.size = size;
        //
        this.array = IntStream.range(0, size)
                .mapToObj(row ->
                        ThreadLocalRandom.current().ints(size, 0, 100)
                                .map(val -> val < ratio ? 1 : 0)
                                .toArray()
                ).toArray(int[][]::new);
    }


}
