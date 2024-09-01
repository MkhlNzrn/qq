package com.example.demo.controller;

import com.example.demo.entities.HeaderModel;
import com.example.demo.services.HeaderModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/header-models")
@Tag(name = "HeaderModel", description = "API для управления моделями заголовков")
public class HeaderModelController {

    private final HeaderModelService headerModelService;

    @Autowired
    public HeaderModelController(HeaderModelService headerModelService) {
        this.headerModelService = headerModelService;
    }

    @Operation(summary = "Создание модели заголовка", description = "Создает новую модель заголовка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Модель заголовка успешно создана", content = @Content(schema = @Schema(implementation = HeaderModel.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content)
    })
    @PostMapping
    public ResponseEntity<HeaderModel> createHeaderModel(@RequestBody HeaderModel headerModel) {
        HeaderModel createdHeaderModel = headerModelService.saveHeaderModel(headerModel);
        return ResponseEntity.status(201).body(createdHeaderModel);
    }

    @Operation(summary = "Получение всех моделей заголовка", description = "Возвращает список всех моделей заголовка")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(schema = @Schema(implementation = HeaderModel.class)))
    })
    @GetMapping
    public ResponseEntity<List<HeaderModel>> getAllHeaderModels() {
        List<HeaderModel> headerModels = headerModelService.getAllHeaderModels();
        return ResponseEntity.ok(headerModels);
    }

    @Operation(summary = "Получение модели заголовка по ID", description = "Возвращает модель заголовка по ее ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Модель заголовка найдена", content = @Content(schema = @Schema(implementation = HeaderModel.class))),
            @ApiResponse(responseCode = "404", description = "Модель заголовка не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HeaderModel> getHeaderModelById(@PathVariable Long id) {
        Optional<HeaderModel> headerModel = headerModelService.getHeaderModelById(id);
        return headerModel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @Operation(summary = "Обновление модели заголовка", description = "Обновляет существующую модель заголовка по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Модель заголовка успешно обновлена", content = @Content(schema = @Schema(implementation = HeaderModel.class))),
            @ApiResponse(responseCode = "404", description = "Модель заголовка не найдена", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HeaderModel> updateHeaderModel(@PathVariable Long id, @RequestBody HeaderModel headerModel) {
        HeaderModel updatedHeaderModel = headerModelService.updateHeaderModel(id, headerModel);
        return updatedHeaderModel != null ? ResponseEntity.ok(updatedHeaderModel)
                : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Удаление модели заголовка", description = "Удаляет модель заголовка по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Модель заголовка успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Модель заголовка не найдена", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHeaderModel(@PathVariable Long id) {
        headerModelService.deleteHeaderModel(id);
        return ResponseEntity.noContent().build();
    }
}
