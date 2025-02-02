# Design Patterns Final Project - using chainabuse API

### Run the mainPageController class to run the project
### use the config.properties to change the configurations, specifically the encrypted API key and the output path for the logs file

## Design Patterns used:
### Builder, used in the ChainAbuseRequestBuilder class in the chainAbuseAPIConn package.  
The class is used to create the API's REST request.
### Singleton, used both in the HttpController and ConfigurationClass, classes.  
Making sure there is only one instance of these types in every run of the program.
### Observer, used at the chainAbuseLogManagement package.  
The observer gets information from the application regarding new logs and passes those off to the UI and the file in which we save our logs.
