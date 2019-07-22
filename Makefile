JAVAC=javac
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(classes)

clean :
	@find . -type f -name '*.class' -delete

run: all
	@java Driver

%.class : %.java
	$(JAVAC) $<
