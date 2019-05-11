package com.research.funding;

import lombok.val;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class FundingCriteriaTest {

    @Test
    public void matches() {
    }

    @Test
    public void shouldContainProperty() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");

        Criteria criteria = new FundingCriteria();

        criteria.shouldContainProperty("name");
        assertTrue(criteria.matches(funding));
        criteria.shouldContainProperty("address");
        assertFalse(criteria.matches(funding));

    }

    @Test
    public void propertyShouldBeEqual() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");

        Criteria criteria = new FundingCriteria();

        criteria.propertyShouldBeEqual("name", "NIH");
        assertTrue(criteria.matches(funding));
        criteria.propertyShouldBeEqual("name", "Gates");
        assertFalse(criteria.matches(funding));
    }

    @Test
    public void andFulfillCriteria() {
        val funding = new HashMap<String, String>();
        funding.put("name", "NIH");
        funding.put("address", "Bethesda");

        Criteria criteria = new FundingCriteria();
        criteria.shouldContainProperty("address");

        Criteria anotherCriteria = new FundingCriteria();
        anotherCriteria.propertyShouldBeEqual("name", "NIH");

        criteria.andFulfillCriteria(anotherCriteria);

        assertTrue(criteria.matches(funding));

        Criteria yetAnotherCriteria = new FundingCriteria();
        yetAnotherCriteria.propertyShouldBeEqual("address", "Bethesda");

        criteria.andFulfillCriteria(yetAnotherCriteria);

        assertTrue(criteria.matches(funding));

        Criteria criteriaToNotBeSatisfied = new FundingCriteria();
        criteriaToNotBeSatisfied.propertyShouldBeEqual("address", "NYC");

        criteria.andFulfillCriteria(criteriaToNotBeSatisfied);

        assertFalse(criteria.matches(funding));



    }

    @Test
    public void orFulfillCriteria() {
    }

    @Test
    public void notCriteria() {
    }
}