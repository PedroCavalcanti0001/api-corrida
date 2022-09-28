package me.pedroeugenio.apicorrida.presenter.resources;

import io.swagger.v3.oas.annotations.Operation;
import me.pedroeugenio.apicorrida.data.services.RunningService;
import me.pedroeugenio.apicorrida.presenter.dto.AverageSpeedDto;
import me.pedroeugenio.apicorrida.presenter.dto.RunningLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/corrida")
public class RunningResource {

    private final RunningService runningService;

    @Autowired
    RunningResource(RunningService runningService) {
        this.runningService = runningService;
    }

    @Operation(summary = "Buscar todas as voltas de todos os participantes")
    @GetMapping("/participantes")
    public ResponseEntity<List<RunningLogDto>> getAllParticipants() {
        return ResponseEntity.ok(runningService.getAllLaps());
    }

    @Operation(summary = "Buscar ultima volta de cada participante")
    @GetMapping("/ultimas-voltas")
    public ResponseEntity<List<RunningLogDto>> getLastLaps() {
        return ResponseEntity.ok(runningService.getLastLaps());
    }

    @Operation(summary = "Busca a melhor volta da corrida")
    @GetMapping("/melhor-volta")
    public ResponseEntity<RunningLogDto> getBestLapOfTheRace() {
        return ResponseEntity.ok(runningService.getBestLapOfTheRace());
    }

    @Operation(summary = "Busca o vencedor da corrida")
    @GetMapping("/vencedor")
    public ResponseEntity<RunningLogDto> getWinner() {
        return ResponseEntity.ok(runningService.getWinner());
    }

    @Operation(summary = "Busca a velocidade media de cada participante da corrida")
    @GetMapping("/velocidade-media")
    public ResponseEntity<List<AverageSpeedDto>> getAverageSpeed() {
        return ResponseEntity.ok(runningService.getAverageSpeed());
    }
}
