package com.qbutton.algorithms.implementations;

import com.qbutton.algorithms.common.AbstractSortAlgorithm;
import com.qbutton.algorithms.common.CommonSortAlgorithmTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

/**
 * SelectionSortAlgorithm test.
 *
 * @author German Urikh
 * @since version 1.0 on 04.12.15.
 */
@ContextConfiguration(locations = "/spring.xml")
public final class SelectionSortAlgorithmTest extends CommonSortAlgorithmTest {
    /**
     * A AbstractSortAlgorithm instance.
     */
    @Autowired
    @Qualifier(value = "selectionSortAlgorithm")
    private AbstractSortAlgorithm abstractSortAlgorithm;

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractSortAlgorithm getAlgorithm() {
        return abstractSortAlgorithm;
    }
}