package ru.vcrop.GameOfLife;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Controller
@RequestMapping("/")
public class StartController {

    final Field field;
    final GameOfLifeService gameOfLifeService;
    final ObjectMapper objectMapper;

    public StartController(Field field, GameOfLifeService gameOfLifeService, ObjectMapper objectMapper) {
        this.field = field;
        this.gameOfLifeService = gameOfLifeService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @GetMapping
    public String get(Model model) {
        model.addAttribute("field",
                objectMapper.writeValueAsString(new FieldDto(field.getSize(), field.getArray())));
        return "index";
    }

    @PostMapping
    @ResponseBody
    public FieldDto next() {
        gameOfLifeService.gameOfLife(field.getArray());
        return new FieldDto(field.getSize(), field.getArray());
    }

}
