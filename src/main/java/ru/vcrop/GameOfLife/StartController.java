package ru.vcrop.GameOfLife;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Log4j2
@Controller
@RequestMapping("/")
public class StartController {

    @Autowired
    Field field;
    @Autowired
    GameOfLifeService gameOfLifeService;

    @GetMapping
    public String get(Model model) {
        Gson gson = new GsonBuilder().create();
        if (Stream.of(field.getArray()).anyMatch(row -> IntStream.of(row).anyMatch(v -> (v & 1) == 1)))
            IntStream.range(0, field.getSize())
                    .forEach(row -> IntStream.range(0, field.getSize())
                            .forEach(col -> field.getArray()[row][col] = (field.getArray()[row][col] & 1) << 1));
        model.addAttribute("field", gson.toJson(new FieldDto(field.getSize(), field.getArray())));
        return "index";
    }

    @PostMapping
    @ResponseBody
    public int[][] next() {
        gameOfLifeService.gameOfLife(field.getArray());
        return field.getArray();
    }

}
