# Web_Scraping
Web scraping is the automatic extraction of data from a web page. It can be used to save money
and labor, and can be ideal for: 
    ->  Conducting research on any topic
    ->  Discovering new categories and topics
    ->  Keeping track of different events
You can use a web scraper to select the specific data you'd like to scrape from an article. 
You can use Python to scrape Wikipedia articles. For example, you can use the wikipedia-scraper
package on GitHub to: 
    ->  Search for a Wikipedia article
    ->  Scrape it
    ->  Conduct basic text analytics
# Wikipedia Downloader
The Wikipedia Downloader is a Java program that allows you to fetch and extract information from Wikipedia articles. 
It performs the following steps:

1) Clean Keyword: Takes a user-provided keyword, trims it, and replaces spaces with underscores 
to make it suitable for a Wikipedia URL.
2) Get Wikipedia URL: Constructs a Wikipedia URL using the cleaned keyword.
3) Make HTTP Request: Sends an HTTP GET request to Wikipedia to fetch the content of the
specified article.
4) Parse Wikipedia Page: Parses the HTML content of the Wikipedia page using JSoup,
a Java library for working with HTML documents.
5) Extract Information: Searches for the first table and the first paragraph 
(introduction) in the article and extracts text information from them.
6) Display Result: Prints the extracted information in a pretty JSON format.

# Prerequisites
Before using this program, make sure you have the following prerequisites installed:

Java Development Kit (JDK)
Gson library (used for JSON serialization)
JSoup library (used for HTML parsing)

# Dependencies
Gson: Download Gson
JSoup: Download JSoup

Result 
The program is pre-configured to download information for a list of keywords, 
including "India," "Ratan Tata," "United States," and "Sundar Pichai." You can modify the
arr array in the main method to specify your own keywords.
