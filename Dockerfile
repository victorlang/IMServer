#
# Oracle Java 8 Dockerfile
#
# https://github.com/dockerfile/java
# https://github.com/dockerfile/java/tree/master/oracle-java8
#

# Pull base image.
FROM java:8 
# Install Java.

COPY IMServer-0.1.0.jar  /home/IMServer.jar

# Define working directory.
WORKDIR /data

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64


# Define default command.
#CMD ["bash"]
CMD  ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java","-jar","/home/IMServer.jar"]
