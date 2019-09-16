package lilee.hd.jokesprovider;

import java.util.Random;

public class JokesProvider {
    private final String[] mJokes = {
            "Q. What's Junkrat's favorite food?\n" + "A. Spam",
            "Q. What's the difference between a good Pharah and a great one? \n" + "A. Mercy",
            "Q. How did Reinhardt afford his rocket hammer and set of armor? \n" + "A. He charged it",
            "Q. Why doesn't Lucio own a better gun?\n" + "A. He broke it down",
            "Q. Why couldn't the enemy team defend with two Junkrats?\n" +
                    "A. Because they were two tired",
            "Q. Why does Pharah have a crush on Roadhog?\n" +
                    "A. She was hooked at first sight",
            "Q. Why is Mei the best at parties?\n" +
                    "A. She knows how to break the ice",
            "Q. Why does Widowmaker love the outdoors?\n" +
                    "A. She loves camping",
            "Q. Why is Winston the least played Hero in Overwatch?\n" +
                    "A. He lacks appeal",
            "Q. Why couldn't Zenyatta capture the point?\n" +
                    "A. He had no balls",
            "Q. Why does McCree wear a poncho?\n" +
                    "A. He didn't see any arm in it",
            "Q. Why are Zarya and Genji best friends?\n" +
                    "A. He helps her get cut",
            "Q. Why did I die while playing Tracer?\n" +
                    "A. I can't recall",
            "Q. What's the similarity between a Unicorn and a good Torbjorn?\n" +
                    "A.They both don't exist"

    };

    public String getJoke() {
        int index = new Random().nextInt(mJokes.length);
        return mJokes[index];
    }
}
