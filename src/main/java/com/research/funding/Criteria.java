package com.research.funding;

import java.util.Map;
import java.util.function.Predicate;

public interface Criteria {

    boolean matches(Map<String, String> opportunity);

    void shouldContainProperty(String property);

    void propertyShouldBeEqual(String property, String value);

    void propertyShouldBeLessThanValue(String property, String value);

    void propertyShouldBeGreaterThanValue(String property, String value);

    void andFulfillCriteria(Criteria criteria);

    void orFulfillCriteria(Criteria criteria);

    void notCriteria();

    Predicate<Map<String, String>> getPredicate();

}
