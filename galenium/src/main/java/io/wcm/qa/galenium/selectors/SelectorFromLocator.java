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
package io.wcm.qa.galenium.selectors;

import com.galenframework.specs.page.Locator;

/**
 * Turns a Galen {@link Locator} object into a Galenium {@link Selector}.
 */
public class SelectorFromLocator extends AbstractSelectorBase {

  /**
   * @param locator to use in Selector construction
   */
  public SelectorFromLocator(Locator locator) {
    setLocator(locator);
  }

  /**
   * @param elementName alternative name for use in reporting
   * @param locator to use in Selector construction
   */
  public SelectorFromLocator(String elementName, Locator locator) {
    this(locator);
    setName(elementName);
  }

  @Override
  public String getString() {
    return getLocator().getLocatorValue();
  }

}