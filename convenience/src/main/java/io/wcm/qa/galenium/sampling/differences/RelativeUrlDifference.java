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
package io.wcm.qa.galenium.sampling.differences;

import java.net.MalformedURLException;
import java.net.URL;

import io.wcm.qa.galenium.exceptions.GaleniumException;

public class RelativeUrlDifference extends UrlDifference {

  @Override
  public String getTag() {
    String fullUrl = super.getTag();
    try {
      URL url = new URL(fullUrl);
      return url.getPath();
    }
    catch (MalformedURLException ex) {
      throw new GaleniumException("could not parse URL: '" + fullUrl + "'", ex);
    }
  }

}
