stop:
	-pkill java

start:
	nohup java -jar target/sylvester-1.0.0-SNAPSHOT.jar >> nohup.out 2>&1 &

run: stop start

