package com.research;


import com.research.funding.Criteria;
import com.research.funding.FundingCriteria;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, String> opportunity = new HashMap<>();
        opportunity.put("name", "Healthcare technology research");
        opportunity.put("amount", "10000");
        opportunity.put("deadline", "2019-10-20");
        opportunity.put("address", "US");

        //should contain property
        Criteria criteria = new FundingCriteria();

        criteria.shouldContainProperty("name");
        assert criteria.matches(opportunity);
        criteria.shouldContainProperty("address");

        assert criteria.matches(opportunity);

        //property should be equal
        criteria = new FundingCriteria();
        criteria.propertyShouldBeEqual("name", "Healthcare technology research");

        assert criteria.matches(opportunity);

        //property should be less than value
        criteria.propertyShouldBeLessThanValue("amount", "500000");
        assert criteria.matches(opportunity);

        //property should be greater than value
        criteria.propertyShouldBeGreaterThanValue("name", "NIH");
        assert criteria.matches(opportunity);

        //and criteria
        Criteria anotherCriteria = new FundingCriteria();
        anotherCriteria.propertyShouldBeEqual("deadline", "2019-10-20");
        criteria.andFulfillCriteria(anotherCriteria);

        assert criteria.matches(opportunity);

        //or criteria
        Criteria yetAnotherCriteria = new FundingCriteria();
        yetAnotherCriteria.propertyShouldBeEqual("address", "NYC");
        criteria.orFulfillCriteria(yetAnotherCriteria);

        assert criteria.matches(opportunity);

        //not
        yetAnotherCriteria.notCriteria();
        assert yetAnotherCriteria.matches(opportunity);



    }
}
