/**
 * Created by felipe on 31/01/14.
 */

import twitter4j.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class TBrindes {

    private Integer postagens;

    public static void main(String[] args) {

        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                ContadorDePostagens contador = ContadorDePostagens.getInstance();
                contador.adicionaPostagem();
                System.out.println(contador.getQuantidadePostagens().toString());
                if (contador.getQuantidadePostagens() % 2 == 0) {
                    LiberaBrinde liberaBrinde = LiberaBrinde.getInstance();
                    liberaBrinde.liberar();

                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);

        FilterQuery filtro = new FilterQuery();
        String[] termos = {"#testehash"};
        filtro.track(termos);
        // filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.filter(filtro);
    }
}
