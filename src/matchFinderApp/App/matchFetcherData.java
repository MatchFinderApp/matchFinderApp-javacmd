import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
public class FootballDataFetcher {
    private static final Map<String, String> leaguesAndCountries = new HashMap<>();

    static {
        leaguesAndCountries.put("Pro League", "Saudi Arabia");
        leaguesAndCountries.put("Ligue 1", "France");
        leaguesAndCountries.put("Premier League", "England");
        leaguesAndCountries.put("Serie A", "Italy");
        leaguesAndCountries.put("La Liga", "Spain");
        leaguesAndCountries.put("Bundesliga", "Germany");
        leaguesAndCountries.put("Primeira Liga", "Portugal");
        leaguesAndCountries.put("CAF Champions League", "World");
        leaguesAndCountries.put("UEFA Champions League", "World");
        leaguesAndCountries.put("AFC Champions League", "World");

    }
    public static void main(String[] args) {
        String responseBody = fetchMatchesForToday();
        if (responseBody != null) {
            Map<String, List<String>> leagueMatches = parseMatches(responseBody);
            printLeagueMatches(leagueMatches); // This should print your final output
        }
    }


    private static String fetchMatchesForToday() {
        String todayDate = LocalDate.now().toString(); // Format: YYYY-MM-DD
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api-football-v1.p.rapidapi.com/v3/fixtures?date=" + "2023-12-02"))
                    .header("X-RapidAPI-Key", "587dfe77b5mshb2aeef60e294ab7p1c2eaajsn5bccd7af211e") // Replace with your API key
                    .header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response.statusCode());
                return response.body();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static Map<String, List<String>> parseMatches(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray fixtures = jsonObject.getJSONArray("response");
        Map<String, List<String>> leagueMatches = new HashMap<>();



        for (int i = 0; i < fixtures.length(); i++) {
            JSONObject fixture = fixtures.getJSONObject(i);


            JSONObject league = fixture.getJSONObject("league");
            String leagueName = league.getString("name");
            String country = league.getString("country");


            if (leaguesAndCountries.containsKey(leagueName) && leaguesAndCountries.get(leagueName).equals(country)) {
                JSONObject teams = fixture.getJSONObject("teams");
                String homeTeamName = teams.getJSONObject("home").getString("name");
                String awayTeamName = teams.getJSONObject("away").getString("name");

                JSONObject venue = fixture.getJSONObject("fixture").getJSONObject("venue");
                String venueCity = venue.getString("city");
                String venueName = venue.getString("name");

                // If the match is finished, print the scores
                int homeScore = -1;
                int awayScore = -1;
                if (!fixture.isNull("score") && !fixture.getJSONObject("score").isNull("fulltime")) {
                    JSONObject score = fixture.getJSONObject("score").getJSONObject("fulltime");
                    homeScore = score.optInt("home", -1);
                    awayScore = score.optInt("away", -1);
                }


                String leagueKey = "Country: " + country + " League: " + leagueName;

                String matchInfo;
                if (homeScore != -1 && awayScore != -1) {
                    matchInfo = String.format(
                            "Venue: %s, %s, Home Team: %s, Away Team: %s, Score: %s-%s",
                            venueCity, venueName, homeTeamName, awayTeamName, homeScore, awayScore
                    );
                } else {
                    matchInfo = String.format(
                            "Venue: %s, %s,Home Team: %s, Away Team: %s, Score: N/A",
                            venueCity, venueName, homeTeamName, awayTeamName
                    );
                }

                leagueMatches.computeIfAbsent(leagueKey, k -> new ArrayList<>()).add(matchInfo);


            }
        }

        return leagueMatches;
    }

    private static void printLeagueMatches(Map<String, List<String>> leagueMatches) {
        for (String leagueKey : leagueMatches.keySet()) {
            System.out.println(leagueKey);
            for (String match : leagueMatches.get(leagueKey)) {
                System.out.println(match);
            }
            System.out.println();
        }
    }

}

