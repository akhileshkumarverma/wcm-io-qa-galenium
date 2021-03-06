/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2018 wcm.io
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
package io.wcm.qa.galenium.sampling.element;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.wcm.qa.galenium.reporting.GaleniumReportUtil;
import io.wcm.qa.galenium.sampling.element.base.ElementBasedSampler;
import io.wcm.qa.galenium.selectors.Selector;

/**
 * Samples position of web element.
 */
public class LocationSampler extends ElementBasedSampler<Point> {

  /**
   * @param selector identifies element
   */
  public LocationSampler(Selector selector) {
    super(selector);
  }

  @Override
  protected Point sampleValue(WebElement element) {
    Point location = element.getLocation();
    GaleniumReportUtil.getLogger().trace("Sampled location for '" + getSelector().elementName() + "': " + location);
    return location;
  }


}
