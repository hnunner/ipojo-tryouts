package com.adviser.ipojo.tryouts.services.configured;

import java.util.logging.Logger;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Property;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.ServiceProperty;
import org.apache.felix.ipojo.annotations.Updated;
import org.apache.felix.ipojo.annotations.Validate;

import com.adviser.ipojo.tryouts.services.interfaces.PropertyService;

/**
 * @author h.nunner
 */
@Component(managedservice="PropertyService.Singleton", publicFactory=false)
@Instantiate
@Provides
public class SingletonPropertyService implements PropertyService {

    @ServiceProperty(value = "singleton")
    private String type;

    @Property
    private String property;

    /** the logger */
    private static final Logger LOG = Logger.getLogger(SingletonPropertyService.class.getName());


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

    /**
     * Called on property update.
     */
    @Updated
    private void update() {
        LOG.info(getClass().getName() + ":: Updated property:" + property);
    }

    /** {@inheritDoc} */
    @Override
    public String getProperty() {
        return property;
    }

}
