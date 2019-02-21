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
package io.wcm.qa.galenium.selectors.impl.base;

import io.wcm.qa.galenium.selectors.IndexedSelector;

/**
 * Indexed selectors allow identifying which element is meant, when there are multiple matches in page.
 */
public class AbstractIndexedSelectorBase extends AbstractNestedSelectorBase implements IndexedSelector {

  private int index;

  @Override
  public String elementName() {
    if (getIndex() > 0) {
      return new StringBuilder()
          .append(super.elementName())
          .append("[")
          .append(getIndex())
          .append("]")
          .toString();
    }
    return super.elementName();
  }

  @Override
  public int getIndex() {
    return index;
  }

  protected void setIndex(int index) {
    this.index = index;
  }

}
