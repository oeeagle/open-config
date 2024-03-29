package org.openconfig.providers;

import org.openconfig.event.*;
import org.openconfig.providers.ast.ComplexNode;
import org.openconfig.providers.ast.NodeManager;
import org.openconfig.providers.ast.SimpleNode;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Richard L. Burton III
 */
public abstract class AbstractDataProvider implements DataProvider {

    private AtomicReference<ComplexNode> root = new AtomicReference<ComplexNode>();

    protected NodeManager nodeFinder = new NodeManager();

    protected EventPublisher eventPublisher = new DefaultEventPublisher();

    public Object getValue(String name) {
        return ((SimpleNode) nodeFinder.find(name, root.get())).getValue();
    }

    public ComplexNode getRoot() {
        return root.get();
    }

    protected void setRoot(ComplexNode root) {
        boolean wasRootNull = this.root.get() == null;
        this.root.set(root);

        if (!wasRootNull) {
            // TODO figure out the differnce between the trees and send it as the change
            publish(new ImmutableChangeStateEvent(root, root, new HashSet<String>()));
        }
    }


    /**
     * Registers the event listeners for the given configurator.
     *
     * @param configurator   the configurator whose events will be fired
     * @param eventListeners the event listeners which will be notified of the events
     */
    public void registerEventListeners(String configurator, EventListener... eventListeners) {
        eventPublisher.addListeners(eventListeners);
    }

    /**
     * A helper method that delegates the publishing of events to the <tt>EventPublisher</tt>.
     *
     * @param changeStateEvent The event to publish to all listeners.
     */
    protected void publish(ChangeStateEvent changeStateEvent) {
        eventPublisher.publishEvent(changeStateEvent);
    }
}
