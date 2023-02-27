package com.stefanostakas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class NaiveBayes {


    // Number of the most frequent words
    private static final int MAX_TOP_WORDS = 1000;



    public static TrainingData TrainingMode() {

        TrainingData TD = getTraingData();

        return TD;
    }

    public static void TestingMode(List<WordLikelihood> likelihood, double probPos, double probNeg) {

        performSentimentAnalysis( getUserInput(), likelihood, probPos, probNeg);


    }

    public static String getUserInput(){
        Scanner userEntry = new Scanner(System.in);
        String userRev;

        System.out.println("Enter your movie review:");
        userRev = userEntry.nextLine();
        return userRev;
    }


    public static double getPosProb(TrainingData TD){
        return TD.getTotalPosRev() / ( TD.getTotalPosRev() / TD.getTotalNegRev() + TD.getTotalPosRev());
    }

    public static double getNegProb(TrainingData TD){
        return TD.getTotalNegRev() / ( TD.getTotalNegRev() / TD.getTotalNegRev() + TD.getTotalPosRev());
    }

    public static TrainingData getTraingData() {

        Scanner scanner = new Scanner(System.in);

        // Ask the user for the CSV training file path
        System.out.print("Enter the path of the CSV training file: ");
        String trainingFilePath = scanner.nextLine();


        Map<String, int[]> wordCount = new HashMap<>(); // stores the count of each word in positive and negative reviews
        int totalPositive = 0;
        int totalNegative = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(trainingFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String review = parts[0].trim().toLowerCase();
                String sentiment = parts[1].trim().toLowerCase();

                //Count totals (Total of positive & Total of negative reviews)
                if (sentiment.equals("positive")) {
                    totalPositive += 1;
                } else if (sentiment.equals("negative")) {
                    totalNegative += 1;
                }

                // Count the occurrences of the words in the text
                for (String word : review.split("\\s+")) {
                    word = word.replaceAll("[^a-zA-Z]", ""); // Remove non-alphabetic characters
                    if (!word.isEmpty() && word.length() > 4) {
                        if (!wordCount.containsKey(word)) {
                            wordCount.put(word, new int[2]); // initialize the count to 0 for both positive and negative
                        }
                        if (sentiment.equals("positive")) {
                            wordCount.get(word)[0] += 1;
                        } else if (sentiment.equals("negative")) {
                            wordCount.get(word)[1] += 1;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the top 1000 most frequent words based on the total count of both positive and negative reviews
        List<Map.Entry<String, int[]>> topWords = new ArrayList<>(wordCount.entrySet());
        topWords.sort((e1, e2) -> Integer.compare(e2.getValue()[0] + e2.getValue()[1], e1.getValue()[0] + e1.getValue()[1])); // sort by total count in descending order
        topWords = topWords.subList(0, Math.min(MAX_TOP_WORDS, topWords.size())); // take only the top MAX_TOP_WORDS or less if there are less than that

        TrainingData TD = new TrainingData(topWords, totalPositive, totalNegative);

        return TD;
    }

        // Likelihoods calculation
        public static List<WordLikelihood> likelihood(TrainingData TD){

            List<WordLikelihood> wordLikelihoods = new ArrayList<>();

            for (Map.Entry<String, int[]> entry : TD.getTopWords()) {
                String word = entry.getKey();
                int positiveCount = entry.getValue()[0];
                int negativeCount = entry.getValue()[1];
                double positiveLikelihood = (double) positiveCount / TD.getTotalPosRev();
                double negativeLikelihood = (double) negativeCount / TD.getTotalNegRev();
                wordLikelihoods.add(new WordLikelihood(word, positiveLikelihood, negativeLikelihood));
            }
            return wordLikelihoods;
        }

    public static void performSentimentAnalysis(String userRev, List<WordLikelihood> wordLikelihoods, double PositiveProb, double NegativeProb) {

        String[] userWords = userRev.split("\\s+");

        double positiveProbability = 1.0;
        double negativeProbability = 1.0;


        for (String word : userWords) {
            boolean found = false;
            for (WordLikelihood w : wordLikelihoods) {
                if (w.getWord().equalsIgnoreCase(word)) {
                    found = true;
                    positiveProbability *= w.getPositiveLikelihood();
                    negativeProbability *= w.getNegativeLikelihood();
                    break;
                }
            }
        }
            if (positiveProbability > negativeProbability) {
                System.out.println("Your review is a positive review");
            } else {
                System.out.println("Your review is a negative review");
            }

        System.out.println("The classification came out through these possibilities." +
                "\nPossibility to be a positive review is : " + positiveProbability +
                "\nWhile the possibility to be a negative review is : " + negativeProbability);



    }
}