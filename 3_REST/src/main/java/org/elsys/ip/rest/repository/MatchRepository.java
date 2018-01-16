package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.MatchInfo;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MatchRepository {
  private static final List<MatchInfo> testList = new ArrayList<>();

  public List<MatchInfo> getTestList() {
    return testList;
  }

  public Optional<MatchInfo> getTestById(Integer id) {
    return testList.stream().filter(test -> test.getId() == id).findFirst();
  }

  public MatchInfo saveTest(MatchInfo test) {
    testList.add(test);
    return test;
  }

  public MatchInfo updateTest(Integer id, MatchInfo test) {
    int realId = id - 1;
    testList.set(realId, test);
    return test;
  }

  public void deleteTest(Integer id) {
    testList.removeIf(it -> it.getId() == id);
  }


  public List<MatchInfo> findByIds(Set<Integer> ids) {
    return testList.stream().filter(
            eachMatchInfo -> ids.contains(eachMatchInfo.getId())
    ).collect(Collectors.<MatchInfo>toList());
  }

    public List<MatchInfo> findBySearchFields(MultivaluedMap<String,String> fieldsToValues) {
        Collection<Predicate<MatchInfo>> listOfPredicates = new ArrayList<Predicate<MatchInfo>>();

        //example fieldName=id,venue,competition etc where as value = 1,2,3(id),Vasil Levski(venue),Vitosha Bistritsa(awayTeam) etc
        for (String fieldName : fieldsToValues.keySet()) {
            //the map returns a list of values
            //check for null
            Object value = fieldsToValues.get(fieldName).get(0);
            if ("venue".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getVenue().equals(value));
            } else if ("competition".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getCompetition().equals(value));
            } else if ("id".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getId() == Integer.valueOf((String) value));
            } else if ("awayTeam".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getAwayTeam().equals(value));
            } else if ("homeTeam".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getHomeTeam().equals(value));
            } else if ("awayTeamAbbr".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getAwayTeamAbbr().equals(value));
            } else if ("homeTeamAbbr".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getDate().equals(value));
            } else if ("date".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getTime().equals(value));
            } else if ("time".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getHomeTeamAbbr().equals(value));
            } else if ("halfLength".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getHalfLength() == Integer.valueOf((String) value));
            } else if ("playersNumber".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getPlayersNumber() == Integer.valueOf((String) value));
            } else if ("subsNumber".equals(fieldName)) {
                listOfPredicates.add(eachMatchInfo -> eachMatchInfo.getSubsNumber() == Integer.valueOf((String) value));
            }
        }

            //vse edno prilagam vsichki predicati ot listOfPredicates kum vseki obekt ot testList
            return testList.stream().filter(
                    matchInfo -> listOfPredicates.stream().allMatch(predicate -> predicate.test(matchInfo))
            ).collect(Collectors.toList());
        }

    public void addList(List<MatchInfo> matches) {

      testList.addAll(matches);
    }

}
