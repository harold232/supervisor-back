package com.example.Conectar.controller;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.example.Conectar.service.PlanEstudiosService;

@RestController
@RequestMapping("v1/planestudios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PlanEstudiosController {
    private static final Logger log = LoggerFactory.getLogger(PlanEstudiosController.class);
    private final PlanEstudiosService planEstudiosService;
    @GetMapping("/hola")
    public Mono<String> hola(){
        return Mono.just("Hola");
    }
    @PostMapping(value = "/cargar")
    public Mono<ResponseEntity<String>> cargarPlanEstudios(@RequestPart("file") Mono<FilePart> filePart) {
        return planEstudiosService.cargarPlanEstudiosDesdeExcel(filePart)
                .then(Mono.just(ResponseEntity.ok("Archivo cargado exitosamente.")))
                .onErrorResume(e -> {
                    log.error("Error al cargar el archivo: {}", e.getMessage());
                    return Mono.just(ResponseEntity.badRequest().body("Error al procesar el archivo: " + e.getMessage()));
                });
    }



}
