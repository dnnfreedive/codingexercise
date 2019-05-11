package com.research.funding;

import java.util.Map;
import java.util.function.Predicate;

public interface Criteria {

    boolean matches(Map<String, String> opportunity);

    void shouldContainProperty(String property);

    void propertyShouldBeEqual(String property, String value);

    void andFulfillCriteria(Criteria criteria);

    void orFulfillCriteria(Criteria criteria);

    void notCriteria(Criteria criteria);

    Predicate<Map<String, String>> getPredicate();

}
