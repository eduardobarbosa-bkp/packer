# Package Challenge - Eduardo Barbosa da Costa

The solution is based on the combination of the list of things that need to be choose
using "power set" theory https://en.wikipedia.org/wiki/Power_set.
For each sub-list is perform the sum to maximizing the cost and minimizing the weight of things inside the package.


***Setup***
* Java 8 and apache-maven 3.3.x;

***Run***
* On the command line on the project root:

1. *mvn package*

2. *mvn exec:java -Dexec.mainClass=com.mobiquityinc.packer.PackerStandalone -Dexec.args="<path of file>"*