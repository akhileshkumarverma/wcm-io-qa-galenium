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

import java.util.ArrayList;
import java.util.Collection;

import io.wcm.qa.galenium.selectors.NestedSelector;

/**
 * Abstract base class for {@link NestedSelector} implementations.
 */
public class AbstractNestedSelectorBase extends AbstractSelectorBase implements NestedSelector {

  private Collection<NestedSelector> children;
  private NestedSelector parent;

  /**
   * @param child to add
   */
  public void addChild(NestedSelector child) {
    getChildren().add(child);
  }

  @Override
  public Collection<NestedSelector> getChildren() {
    if (children == null) {
      children = new ArrayList<NestedSelector>();
    }
    return children;
  }

  @Override
  public NestedSelector getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return !getChildren().isEmpty();
  }

  @Override
  public boolean hasParent() {
    return getParent() != null;
  }

  /**
   * Set parent and update selector state.
   * @param parent new parent selector
   */
  public void setParent(NestedSelector parent) {
    this.parent = parent;
  }

  protected void setChildren(Collection<NestedSelector> children) {
    this.children = children;
  }

}
