package com.adviser.ipojo.cfg.consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Invalidate;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.ipojo.annotations.Validate;

import com.adviser.ipojo.tryouts.services.interfaces.PropertyService;

/**
 * @author h.nunner
 */
@Component(immediate=true)
@Instantiate
public class Consumer implements Runnable {

    /** logger */
    private static final Logger LOG = Logger.getLogger(Consumer.class.getName());

    /** singleton property service */
    @Requires(filter="(type=singleton)")
    private PropertyService[] singletonServices;
//    private AbstractPropertyService[] singletonServices;

    /** multiton property service */
    @Requires(filter="(type=multiton)")
    private PropertyService[] multitonServices;
//    private AbstractPropertyService[] multitonServices;

    /** thread status */
    private boolean stopped;


    /**
     * Called on bundle startup.
     */
    @Validate
    private void start() {
        stopped = false;
        new Thread(this).start();
        LOG.info(getClass() + "::Start");
    }

    /**
     * Called on bundle shutdown.
     */
    @Invalidate
    private void stop() {
        stopped = true;
        LOG.info(getClass() + "::Stop");
    }


    /** {@inheritDoc} */
    @Override
    public void run() {
        while (!stopped) {
            logAllServices();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, "Caught InterruptedException during Thread.sleep(..)", e);
            }
        }
        LOG.info("Consumer stopped.");
    }

    /**
     * Logs all the injected services.
     */
    private void logAllServices() {
        logServices(singletonServices, "singleton");
        logServices(multitonServices, "multiton");
    }

    /**
     * Logs the given array of PropertyService of the given type.
     *
     * @param services
     *          the array of PropertyService to be logged
     * @param serviceDescription
     *          the description of the given services
     */
    private void logServices(PropertyService[] services, String serviceDescription) {
//    private void logServices(AbstractPropertyService[] services, String serviceDescription) {
        StringBuilder sb = new StringBuilder("\n");
        if (services == null) {
            sb.append("no ").append(serviceDescription).append(" service injections");
        } else if (services.length <= 0) {
            sb.append("empty array of ").append(serviceDescription).append(" service injections");
        } else {
            sb.append(serviceDescription).append(" services #").append(services.length).append(" --> ");
            for (PropertyService service : services) {
//            for (AbstractPropertyService service : services) {
                sb.append(service.toString()).append(" property:").append(service.getProperty()).append(", ");
            }
        }
        LOG.info(sb.toString());
    }

}
