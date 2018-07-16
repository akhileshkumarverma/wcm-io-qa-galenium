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
package io.wcm.qa.galenium.device;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeviceProfile {

  private String browser;
  private String emulator;
  private String name;
  private int screenHeight;
  private int screenWidth;

  public String getBrowser() {
    return browser;
  }

  public BrowserType getBrowserType() {
    return BrowserType.valueOf(getBrowser());
  }

  public String getEmulator() {
    return emulator;
  }

  public int getHeight() {
    return screenHeight;
  }

  public String getName() {
    return name;
  }

  public int getWidth() {
    return screenWidth;
  }

  public void setBrowser(String browser) {
    this.browser = browser;
  }

  public void setEmulator(String emulator) {
    this.emulator = emulator;
  }

  public void setHeight(int height) {
    this.screenHeight = height;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWidth(int width) {
    this.screenWidth = width;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

}
