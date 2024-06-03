package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
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
   *
   * @param farmServic the farm servic
   */
  public FarmController(FarmService farmServic) {
    this.farmService = farmServic;
  }

  /**
   * Gets farm by id.
   *
   * @param farmId the farm id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}")
  public FarmDto getFarmById(@PathVariable Long farmId) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(farmId)
    );
  }

  /**
   * Create farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
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

}