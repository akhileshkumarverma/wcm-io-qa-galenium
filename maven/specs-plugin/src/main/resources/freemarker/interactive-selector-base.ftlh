package ${interactiveSelectorPackage};

import com.galenframework.specs.page.Locator;

import io.wcm.qa.galenium.interaction.Element;
import io.wcm.qa.galenium.interaction.FormElement;
import io.wcm.qa.galenium.selectors.impl.SelectorFromLocator;
import io.wcm.qa.galenium.selectors.Selector;

/**
 * Interface for interactive selectors.
 *
 * This is GENERATED CODE and should not be modified manually.  
 */
public abstract class ${interactiveSelectorBaseClassName} extends SelectorFromLocator implements ${interactiveSelectorInterfaceClassName} {
  
  protected ${interactiveSelectorBaseClassName}(String elementName, Locator locator) {
    super(elementName, locator);
  }

  <#list elementInteraction.methods as method>
     ${method.head} {
       ${method.body}
     }
  </#list>

  <#list formElementInteraction.methods as method>
     ${method.head} {
       ${method.body}
     }
  </#list>

}