# expensive

Expensive is an application that should help you to clearly see what is truly expensive in your life. This app is based on financial software used in banks and on their ATMs. You can manage all your receipts (expenses) and see all your monthly statements, and how that reflects onto your monthly budget. 

## Prerequisites

You will need [Leiningen][9] 2.0 or above installed and [Postgres][10] 9.3.5 installed.

[1]: https://github.com/technomancy/leiningen
[2]: https://github.com/weavejester/ring-server
[3]: https://github.com/krisajenkins/yesql
[4]: https://github.com/yogthos/migratus
[5]: https://github.com/weavejester/crypto-password
[6]: https://github.com/yogthos/Selmer
[7]: https://github.com/clojure/data.json
[8]: https://plot.ly/javascript/
[9]: https://github.com/technomancy/leiningen
[10]: https://www.postgresql.org/ftp/source/v9.3.5/
[11]: https://www.amazon.com/Clojure-Brave-True-Ultimate-Programmer/dp/1593275919
[12]: https://www.amazon.com/Clojure-Development-Essentials-Ryan-Baldwin/dp/1784392227

## Setup

##### Here are the steps to run the application: #####

1. Login to Postgres server and create database called expensive
   
        CREATE DATABASE expensive DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
2. To create database schema navigate to project directory and execute migrations files using command

        lein migratus migrate
3. To start a web server for the application, run:

        lein ring server
    
## Libraries

* [Leiningen][1] : dependency management library for building and configure Clojure project
* [Ring-Server][2] : library for starting a web server to serve a Ring handler with sensible default options and environment variable overrides
* [YeSql][3] : library which generates functions out of SQL, pulls sql files and turns queries into a function
* [Migratus][4] : library for database migration and schema management
* [Crypto-password][5] : library for securing user passwords
* [Selmer][6] : Django inspired template system for Clojure
* [Data.json][7] : JSON parser/generator to/from Clojure data structures
* [Plotly][8] :  high-level, declarative charting library used in _Javascript_

    
##References
  * [Clojure for the brave and true][11]
  
  * [Clojure Web Development Essentials][12]
  
###### The project was developed as part of the assignment for the course Software Engineering Tools and Methodology at the Faculty of Organization Sciences, University of Belgrade, Serbia.
