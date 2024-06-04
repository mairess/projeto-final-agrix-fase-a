package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * All crops list.
   *
   * @return the list
   */
  @GetMapping
  @Operation(summary = "Gets all crops", description = "Returns all crops available on system.")
  @ApiResponse(
      responseCode = "200",
      description = "All crops",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = CropDto.class))))
  public List<CropDto> allCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Gets crop by id.
   *
   * @param cropId the crop id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}")
  @Operation(summary = "Gets crop by id", description = "Returns a crop by its id.")
  @ApiResponse(
      responseCode = "200",
      description = "The crop",
      content = @Content(schema = @Schema(implementation = CropDto.class)))
  public CropDto getCropById(@PathVariable Long cropId) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(cropId)
    );
  }

}