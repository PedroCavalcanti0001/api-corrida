package me.pedroeugenio.apicorrida.presenter.resources;

import io.swagger.v3.oas.annotations.Operation;
import me.pedroeugenio.apicorrida.data.services.NumberService;
import me.pedroeugenio.apicorrida.presenter.dto.NumberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/numeros")
public class NumberResource {

    private final NumberService numberService;

    @Autowired
    NumberResource(NumberService numberService) {
        this.numberService = numberService;
    }

    @Operation(summary = "Buscar ocorrencias de numeros através por an em vetor")
    @GetMapping("/{vetor}")
    public ResponseEntity<List<NumberDto>> getNumbers(@PathVariable Integer[] vetor) {
        return ResponseEntity.ok(numberService.searchOccurrences(vetor));
    }
}
