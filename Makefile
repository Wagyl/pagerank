all :
	mkdir -p bin; cp examples/* bin/
	javac -d bin -sourcepath src -cp lib/opencsv-2.3.jar src/MainTest.java
	cp src/pagerank .
	chmod +x pagerank

clean :
	rm -f bin/*
	rm pagerank
