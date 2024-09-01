package com.example.demo.controller;

import com.example.demo.entities.Machine;
import com.example.demo.services.MachineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/machines")
@Tag(name = "Machine", description = "API для управления станками")
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @Operation(summary = "Создание станка", description = "Создает новый станок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Станок успешно создан", content = @Content(schema = @Schema(implementation = Machine.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Machine> createMachine(@RequestBody Machine machine) {
        Machine createdMachine = machineService.createMachine(machine);
        return ResponseEntity.status(201).body(createdMachine);
    }

    @Operation(summary = "Получение всех станков", description = "Возвращает список всех станков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(schema = @Schema(implementation = Machine.class)))
    })
    @GetMapping
    public ResponseEntity<List<Machine>> getAllMachines() throws AccessDeniedException {
        List<Machine> machines = machineService.getAllMachines();
        return ResponseEntity.ok(machines);
    }

    @Operation(summary = "Получение станка по ID", description = "Возвращает станок по его ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Станок найден", content = @Content(schema = @Schema(implementation = Machine.class))),
            @ApiResponse(responseCode = "404", description = "Станок не найден", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        Optional<Machine> machine = machineService.getMachineById(id);
        return machine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @Operation(summary = "Обновление станка", description = "Обновляет существующий станок по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Станок успешно обновлен", content = @Content(schema = @Schema(implementation = Machine.class))),
            @ApiResponse(responseCode = "404", description = "Станок не найден", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody Machine machine) {
        Machine updatedMachine = machineService.updateMachine(id, machine);
        return updatedMachine != null ? ResponseEntity.ok(updatedMachine)
                : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Удаление станка", description = "Удаляет станок по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Станок успешно удален"),
            @ApiResponse(responseCode = "404", description = "Станок не найден", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
        return ResponseEntity.noContent().build();
    }
}
