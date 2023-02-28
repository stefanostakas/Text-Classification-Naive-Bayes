# Naive Bayes Text Classification

This Java Machine Learning app uses Naive Bayes for text classification with two modes: training (CSV import) and testing (real-time text). 
Trains a model by importing labeled CSV files, then classifies text input into predefined categories. Ideal for spam detection and sentiment analysis.

## Training

The Naive Bayes algorithm needs to be trained first before it can classify text. 
To train the algorithm, import a CSV file with two columns, where the first column contains the text, and the second column contains the sentiment. 
The sentiment values should be either "positive" or "negative."
The Naive Bayes algorithm will read the records and extract the 1000 most frequently occurring words. 
It will then calculate the likelihood of each of these words appearing in a positive or negative text.
For best results, it is recommended to import at least 50,000 records into the CSV file.

## Testing
After the algorithm has been trained, the user can use the test operation to classify their own text. 
Simply enter the text into the console, and the application will print the classification along with the probability that led to the decision.

