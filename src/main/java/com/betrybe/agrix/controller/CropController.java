package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
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
  public CropDto getCropById(@PathVariable Long cropId) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(cropId)
    );
  }

}