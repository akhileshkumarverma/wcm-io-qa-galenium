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
package io.wcm.qa.galenium.example;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.galenframework.reports.model.LayoutReport;

import io.wcm.qa.galenium.device.TestDevice;
import io.wcm.qa.galenium.differences.difference.BrowserDifference;
import io.wcm.qa.galenium.differences.difference.ScreenWidthDifference;
import io.wcm.qa.galenium.example.selectors.common.Page;
import io.wcm.qa.galenium.example.selectors.homepage.Stage;
import io.wcm.qa.galenium.galen.GalenHelperUtil;
import io.wcm.qa.galenium.galen.GalenLayoutChecker;
import io.wcm.qa.galenium.imagecomparison.ImageComparisonSpecFactory;
import io.wcm.qa.galenium.imagecomparison.ImageComparisonValidationListener;
import io.wcm.qa.galenium.providers.TestDeviceProvider;
import io.wcm.qa.galenium.selectors.Selector;

/**
 * Example of how to use the {@link ImageComparisonSpecFactory} to compare individual elements on a page.
 */
public class ImageComparisonIT extends AbstractExampleBase {

  @Factory(dataProviderClass = TestDeviceProvider.class, dataProvider = TestDeviceProvider.GALENIUM_TEST_DEVICES_ALL)
  public ImageComparisonIT(TestDevice testDevice) {
    super(testDevice);
  }

  @Test
  public void compareSomeImages() {
    loadStartUrl();
    checkVisually(Stage.SELF);
    checkVisually(Page.LOGO);
  }

  private void checkVisually(Selector selector) {
    // get factory for comparing element
    ImageComparisonSpecFactory factory = new ImageComparisonSpecFactory(selector);

    // add a no tolerance check at warning level
    factory.setZeroToleranceWarning(true);

    // allow offset
    factory.setAllowedOffset(2);

    // browser and viewport width will make a difference
    factory.addDifference(new BrowserDifference());
    factory.addDifference(new ScreenWidthDifference());

    // compare image using spec
    LayoutReport layoutReport = GalenLayoutChecker.checkLayout(
        "Image comparison stage",
        factory.getPageSpecInstance(),
        getDevice(),
        GalenHelperUtil.getSectionFilter(getDevice()),
        getValidationListener());
    String specName = "image_comparison_" + selector.elementName() + ".gspec";
    String errorMessage = "FAILED: Layoutcheck " + specName + " with device " + getDevice();
    String successMessage = "successfully ran spec: " + specName;
    GalenLayoutChecker.handleLayoutReport(layoutReport, errorMessage, successMessage);
  }

  private ImageComparisonValidationListener getValidationListener() {
    return new ImageComparisonValidationListener();
  }

  @Override
  protected String getRelativePath() {
    return PATH_TO_HOMEPAGE;
  }

}
