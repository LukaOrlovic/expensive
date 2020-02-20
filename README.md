# expensive

Expensive is an application that should help you to clearly see what is truly expensive in your life. This app is based on financial software used in banks and on their ATMs. You can manage all your receipts (expenses) and see all your monthly statements, and how that reflects onto your monthly budget. 

## Prerequisites

You will need [Leiningen 2.0] [1] or above installed and [Postgres 9.3.5] [2] installed.

[1]: https://github.com/technomancy/leiningen
[2]: https://www.postgresql.org/ftp/source/v9.3.5/

## Setup

##### Here are the steps to run the application: #####

1. Login to Postgres server and create database called expensive
   
        CREATE DATABASE expensive DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
2. To create database schema navigate to project directory and execute migrations files using command

        lein migratus migrate
3. To start a web server for the application, run:

        lein ring server
    
## Libraries

* [__Leiningen__] [1] : dependency management library for building and configure Clojure project
* [__Ring-Server__] [2] : library for starting a web server to serve a Ring handler with sensible default options and environment variable overrides
* [__YeSql__] [3] : library which generates functions out of SQL, pulls sql files and turns queries into a function
* [__Migratus__] [4] : library for database migration and schema management
* [__Crypto-password__] [5] : library for securing user passwords
* [__Selmer__] [6] : Django inspired template system for Clojure
* [__Data.json__] [7] : JSON parser/generator to/from Clojure data structures
* [__Plotly__] [8] :  high-level, declarative charting library used in _Javascript_

[1]: https://github.com/technomancy/leiningen
[2]: https://github.com/weavejester/ring-server
[3]: https://github.com/krisajenkins/yesql
[4]: https://github.com/yogthos/migratus
[5]: https://github.com/weavejester/crypto-password
[6]: https://github.com/yogthos/Selmer
[7]: https://github.com/clojure/data.json
[8]: https://plot.ly/javascript/
    
##References
  * [_Clojure for the brave and true_] [1]
  
  * [_Clojure Web Development Essentials_] [2]
  
###### The project was developed as part of the assignment for the course Software Engineering Tools and Methodology at the Faculty of Organization Sciences, University of Belgrade, Serbia.
  
  [1]: https://www.amazon.com/Clojure-Brave-True-Ultimate-Programmer/dp/1593275919
  [2]: https://www.amazon.com/Clojure-Development-Essentials-Ryan-Baldwin/dp/1784392227

