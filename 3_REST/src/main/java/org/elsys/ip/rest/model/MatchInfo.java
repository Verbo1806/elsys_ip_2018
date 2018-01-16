package org.elsys.ip.rest.model;

public class MatchInfo {

  private int id;

  private String date;
  private String time;
  private String venue;
  private String competition;

  private String homeTeam;
  private String awayTeam;
  private String homeTeamAbbr;
  private String awayTeamAbbr;

  private int halfLength;
  private int playersNumber;
  private int subsNumber;

  private boolean isActive;

  public MatchInfo(int id, String date, String time, String venue, String competition, String homeTeam, String awayTeam, String homeTeamAbbr, String awayTeamAbbr, int halfLength, int playersNumber, int subsNumber, boolean isActive) {
    this.id = id;
    this.date = date;
    this.time = time;
    this.venue = venue;
    this.competition = competition;
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamAbbr = homeTeamAbbr;
    this.awayTeamAbbr = awayTeamAbbr;
    this.halfLength = halfLength;
    this.playersNumber = playersNumber;
    this.subsNumber = subsNumber;
    this.isActive = isActive;
  }

  public MatchInfo() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getCompetition() {
    return competition;
  }

  public void setCompetition(String competition) {
    this.competition = competition;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public String getHomeTeamAbbr() {
    return homeTeamAbbr;
  }

  public void setHomeTeamAbbr(String homeTeamAbbr) {
    this.homeTeamAbbr = homeTeamAbbr;
  }

  public String getAwayTeamAbbr() {
    return awayTeamAbbr;
  }

  public void setAwayTeamAbbr(String awayTeamAbbr) {
    this.awayTeamAbbr = awayTeamAbbr;
  }

  public int getHalfLength() {
    return halfLength;
  }

  public void setHalfLength(int halfLength) {
    this.halfLength = halfLength;
  }

  public int getPlayersNumber() {
    return playersNumber;
  }

  public void setPlayersNumber(int playersNumber) {
    this.playersNumber = playersNumber;
  }

  public int getSubsNumber() {
    return subsNumber;
  }

  public void setSubsNumber(int subsNumber) {
    this.subsNumber = subsNumber;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
