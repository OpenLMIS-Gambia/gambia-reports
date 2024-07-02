package org.openlmis.ao.reports.dto.external;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProgramDto {
  private UUID id;
  private String code;
  private String name;
  private String description;
  private Boolean active;
  private Boolean periodsSkippable;
  private Boolean showNonFullSupplyTab;
}
