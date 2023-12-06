package src.matchFinderApp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Map;

public class SmsSender {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC9accf727b4d0c1c8846c96e20d6ba467";
    public static final String AUTH_TOKEN = "90f97c8fbed44c9cd739e5412d4ed464";
    public static final PhoneNumber FROM_NUMBER = new PhoneNumber("+18452432474"); // The Twilio number you own and will send messages from
    public static final PhoneNumber TO_NUMBER = new PhoneNumber("+966503995592"); // The number you want to send messages to

    public static void Send(Map<String, String> leaguesMatches) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        StringBuilder matchesBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : leaguesMatches.entrySet()) {
            matchesBuilder.append(entry.getKey()).append("\n").append(entry.getValue()).append("\n\n");
        }

        String matchInfo = matchesBuilder.toString();
        System.out.println(matchInfo);
        Message message = Message.creator(
                TO_NUMBER, // To number
                FROM_NUMBER, // From number
                matchInfo // Body of the SMS
        ).create();

        System.out.println("Sent message with SID: " + message.getSid());


    }
}
