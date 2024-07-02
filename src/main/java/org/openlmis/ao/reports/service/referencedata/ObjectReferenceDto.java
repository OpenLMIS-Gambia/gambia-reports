/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses. For additional information contact info@OpenLMIS.org.
 */

package org.openlmis.ao.reports.service.referencedata;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public final class ObjectReferenceDto {

  public static final String SEPARATOR = "/";
  public static final String BASE_PATH = "/api";

  private UUID id;
  private String href;

  /**
   * Returns new object reference.
   *
   * @param path resource path
   * @param id object id
   */
  public ObjectReferenceDto(String serviceUrl, String path, UUID id) {
    this.id = id;
    this.href = StringUtils.joinWith(SEPARATOR, serviceUrl + BASE_PATH, path, id);
  }
}
