# Design Patterns Final Project - using chainabuse API

## How to run:
Run the mainPageController class to run the project.  
Use the config.properties to change the configurations, specifically the encrypted API key and the output path for the logs file.

## Design Patterns used:
<details>
<summary>Builder, used in the ChainAbuseRequestBuilder class in the chainAbuseAPIConn package.  </summary>
The class is used to create the API's REST request.  
</details>

<details>
<summary>
Singleton, used both in the HttpController and ConfigurationClass, classes.  </summary>
Making sure there is only one instance of these types in every run of the program.  
</details>

<details>
<summary>Observer, used at the chainAbuseLogManagement package.  </summary>
The observer gets information from the application regarding new logs and passes those off to the UI and the file in which we save our logs.
</details>
