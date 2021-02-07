# Scopt 4.0 POC

##Overview
This Scopt 4.0 POC is to demonstrate the way to overcome a situation where a ETL has subprojects.  Each subproject has its own set of parameters.  <br>
An good example that I want to demonstrate here is,
1. the `CrosswalkPath` is required for **place** file, but it is not a valid parameter for **Visit**.
2. The `HouseholdOutputPath` is required for ***visit*** file, but it is not a valid parameter for ***place***

###Test Parameters
You can but the following into Intellij's Program Argument.

place
```
"--File"
"place"
"--DeltaBasePath"
"/src/Delta/"
"--InputBasePath"
"/src/Input"
"--OutputBasePath"
"/src/Output/"
"--CrosswalkPath"
"/src/Input/Crosswalk"
```

visit
```
"--File"
"visit"
"--InputBasePath"
"/src/Input"
"--OutputBasePath"
"/src/Output/"
"--HouseholdOutputPath"
"/src/output/household/"
```