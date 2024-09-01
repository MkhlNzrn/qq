package com.example.demo.controller;

import com.example.demo.entities.FooterModel;
import com.example.demo.services.FooterModelService;
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
@RequestMapping("/api/footer-models")
@Tag(name = "FooterModel", description = "API для управления моделями футера")
public class FooterModelController {

    private final FooterModelService footerModelService;

    @Autowired
    public FooterModelController(FooterModelService footerModelService) {
        this.footerModelService = footerModelService;
    }

    @Operation(summary = "Создание модели футера", description = "Создает новую модель футера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Модель футера успешно создана", content = @Content(schema = @Schema(implementation = FooterModel.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос", content = @Content)
    })
    @PostMapping
    public ResponseEntity<FooterModel> createFooterModel(@RequestBody FooterModel footerModel) {
        FooterModel createdFooterModel = footerModelService.saveFooterModel(footerModel);
        return ResponseEntity.status(201).body(createdFooterModel);
    }

    @Operation(summary = "Получение всех моделей футера", description = "Возвращает список всех моделей футера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(schema = @Schema(implementation = FooterModel.class)))
    })
    @GetMapping
    public ResponseEntity<List<FooterModel>> getAllFooterModels() {
        List<FooterModel> footerModels = footerModelService.getAllFooterModels();
        return ResponseEntity.ok(footerModels);
    }

    @Operation(summary = "Получение модели футера по ID", description = "Возвращает модель футера по ее ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Модель футера найдена", content = @Content(schema = @Schema(implementation = FooterModel.class))),
            @ApiResponse(responseCode = "404", description = "Модель футера не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FooterModel> getFooterModelById(@PathVariable Long id) {
        Optional<FooterModel> footerModel = footerModelService.getFooterModelById(id);
        return footerModel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @Operation(summary = "Обновление модели футера", description = "Обновляет существующую модель футера по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Модель футера успешно обновлена", content = @Content(schema = @Schema(implementation = FooterModel.class))),
            @ApiResponse(responseCode = "404", description = "Модель футера не найдена", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<FooterModel> updateFooterModel(@PathVariable Long id, @RequestBody FooterModel footerModel) {
        FooterModel updatedFooterModel = footerModelService.updateFooterModel(id, footerModel);
        return updatedFooterModel != null ? ResponseEntity.ok(updatedFooterModel)
                : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Удаление модели футера", description = "Удаляет модель футера по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Модель футера успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Модель футера не найдена", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFooterModel(@PathVariable Long id) {
        footerModelService.deleteFooterModel(id);
        return ResponseEntity.noContent().build();
    }
}
