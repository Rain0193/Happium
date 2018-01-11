package io.happium.appium_client_service.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Entity representation of a Desired Capability
 */
@Entity
@Transactional
public class SupportedDesiredCapability {

    /**
     * Name of the desired capability - must be unique
     */
    @Id
    @Column(nullable = false)
    @Getter @Setter private String name;        // Name must be unique

    /**
     * Appium-provided description of the DesiredCapability
     */
    @Column(nullable = false, name = "appium_description")
    @Getter @Setter private String appiumDescription;

    /**
     * Indicates whether Appium requires the presence of this capability
     * in the current test scenario - requirements are decided for you dynamically
     * by HappiumService
     */
    @Column(nullable = false)
    @Getter @Setter private boolean isRequiredCapability;

    /**
     * Either "ios", "android" or "global"
     */
    @Column(nullable = false, name = "platform_name")
    @Getter @Setter private String platformName;

    /**
     * Happium-provided tips that aid in creating DesiredCapabilities
     * objects
     */
    @Column(name = "happium_tips")
    @Getter @Setter private List<String> tips;

    /**
     * Indicates which capabilities this capability can be replaced with (if any)
     */
    @Column(name = "alternative_options")
    @Getter @Setter private List<String> alternativeOptions;

    /**
     * Base Constructor
     *
     * <p>
     *     Please note that "required" capabilities and "global" capabilities ARE NOT
     *     the same. Global capabilities are merely those that CAN be applied to all
     *     Appium sessions, regardless of the device OS. Required capabilities are those
     *     that HAPPIUM requires in order to function properly (if a required capability can
     *     be substituted out, this is also indicated by the Capability object).
     * <p>
     *     In order to keep this class clean, this is the only constructor - alterations to
     *     any attributes of this Capability should be handled via the getter/setter
     *     methods
     *
     * @param name                          Name of the capability (e.g. "app")
     * @param isRequiredCapability          True indicates this Capability is required for all Appium Sessions
     * @param appiumDescription             Description of Capability from Appium documentation
     * @param validOptions                  JsonObject that represents a valid value for this capability (varies)
     * @param tips                          List of capability usage tips
     */
    public SupportedDesiredCapability( String name, boolean isRequiredCapability, String appiumDescription,
                                       JsonObject validOptions, List<String> tips ) {

        this.name = name;
        this.isRequiredCapability = isRequiredCapability;
        this.appiumDescription = appiumDescription;
        this.tips = tips;

    }


    /**
     * Utility method to determine the type of values this object can take,
     * as well as any supplemental information about the values such as the
     * possible values (if the capability only accepts from a list of preset
     * values), or platform-specific usage tips (if applicable)
     *
     * @param validOptions          Object that configures the type of values this capability takes
     */
    private void _interpretValidOptions( JsonObject validOptions ) {



    }

    @Override
    public String toString() {
        return String.format(
                "SupportedDesiredCapability[name='%s', description='%s', required='%s', usage_tips='%s']",
                name, appiumDescription, isRequiredCapability, tips
        );
    }

}
