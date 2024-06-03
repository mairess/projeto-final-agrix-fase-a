package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   */
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Gets farmId by id.
   *
   * @param farmId the farmId id
   * @return the farmId by id
   * @throws FarmNotFoundException the farmId not found exception
   */
  @GetMapping("/{farmId}")
  public FarmDto getFarmById(@PathVariable Long farmId) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(farmId)
    );
  }

  /**
   * Create farmId dto.
   *
   * @param farmCreationDto the farmId creation dto
   * @return the farmId dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * All farms list.
   *
   * @return the list
   */
  @GetMapping
  public List<FarmDto> allFarms() {
    List<Farm> allFarms = farmService.findAll();
    return allFarms.stream().map(FarmDto::fromEntity).toList();
  }

  /**
   * Create farmId crop crop dto.
   *
   * @param farmId          the farmId id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farmId not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createFarmCrop(@
      PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto)
      throws FarmNotFoundException {
    return CropDto.fromEntity(
        farmService.createFarmCrop(farmId, cropCreationDto.toEntity())
    );
  }

  /**
   * Create farm crop list.
   *
   * @param farmId the farm id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getFarmCrops(@PathVariable Long farmId)
      throws FarmNotFoundException {
    List<Crop> crops = farmService.getFarmCrops(farmId);

    return crops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

}