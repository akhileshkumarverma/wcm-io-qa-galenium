/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.qa.galenium.sampling.differences;

import java.util.List;

import io.wcm.qa.galenium.reporting.GaleniumReportUtil;

public class DifferentiatedDifferences extends MutableDifferences {

  private int cutoff = 2;

  @Override
  public String asFilePath() {
    List<Difference> differences = getDifferences();
    int differencesTotalCount = differences.size();
    int pivotIndex = differencesTotalCount - getCutoff();
    if (pivotIndex < 0 || pivotIndex > differencesTotalCount) {
      GaleniumReportUtil.getLogger().debug("could not differentiate because of illegal cutoff: " + this);
      return super.asFilePath();
    }
    String folderPart = joinTagsWith(differences.subList(0, pivotIndex), "/");
    String filePart = joinTagsWith(differences.subList(pivotIndex, differencesTotalCount), "/");

    return folderPart + "/" + filePart;
  }

  public int getCutoff() {
    return cutoff;
  }

  public void setCutoff(int cutoff) {
    this.cutoff = cutoff;
  }

  @Override
  public String toString() {
    return super.toString() + " cutoff: " + getCutoff();
  }

}