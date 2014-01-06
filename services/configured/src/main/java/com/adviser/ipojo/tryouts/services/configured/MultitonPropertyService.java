package com.adviser.ipojo.tryouts.services.configured;

import java.util.logging.Logger;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Property;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;
import org.apache.felix.ipojo.annotations.Validate;

import com.adviser.ipojo.tryouts.services.interfaces.PropertyService;

/**
 * @author h.nunner
 */
@Component(name="PropertyService.Multiton")  // instantiation via [karaf_home]/etc/PropertyService.Default[suffix].cfg files
@Provides
public class MultitonPropertyService implements PropertyService {
//public class MultitonPropertyService extends AbstractPropertyService {

    @ServiceProperty(value = "multiton")
    private String type;

    @Property(mandatory=true)
    private String property;

    /** the logger */
    private static final Logger LOG = Logger.getLogger(MultitonPropertyService.class.getName());


    /**
     * Called on startup.
     */
    @Validate
    private void start() {
        LOG.info(getClass().getName() + ":: Start #" + toString() + ", with property:" + property);
    }

    /**
     * Called on shutdown.
     */
    @Invalidate
    private void stop() {
        LOG.info(getClass().getName() + ":: Stop #" + toString());
    }

    /** {@inheritDoc} */
    @Override
    public String getProperty() {
        return property;
    }

}
