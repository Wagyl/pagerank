all :
	mkdir -p bin; cp example/*.txt bin/
	javac -d bin -sourcepath src -cp lib/opencsv-2.3.jar src/MainTest.java

clean :
	rm -v -f bin/*.txt
	rm -v -f bin/*.class
