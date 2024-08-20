# to delete old jar file when creating a new one

#!/bin/bash
rm -f ./libs/*.jar
mvn package
